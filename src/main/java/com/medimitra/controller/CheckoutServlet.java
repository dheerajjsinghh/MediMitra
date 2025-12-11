package com.medimitra.controller;
https://xhamster.desi/videos/horny-devar-fucks-gorgeous-newly-married-indian-bhabhi-hindi-audio-xhtSBzr#
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.medimitra.dao.*;
import com.medimitra.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private CartDAO cartDAO = new CartDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private MedicineDAO medicineDAO = new MedicineDAO();
    private AddressDAO addressDAO = new AddressDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            List<CartItem> cartItems = cartDAO.findByUserId(userId);
            if (cartItems.isEmpty()) {
                response.sendRedirect("cart");
                return;
            }

            List<Address> addresses = addressDAO.findByUserId(userId);
            BigDecimal totalAmount = cartItems.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("addresses", addresses);
            request.setAttribute("totalAmount", totalAmount);
            request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading checkout");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            // Get cart items
            List<CartItem> cartItems = cartDAO.findByUserId(userId);
            if (cartItems.isEmpty()) {
                response.sendRedirect("cart");
                return;
            }

            // Calculate total
            BigDecimal totalAmount = cartItems.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Create order
            String orderNumber = "ORD" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            Order order = new Order(userId, orderNumber, totalAmount);
            order.setPaymentMethod(request.getParameter("paymentMethod"));
            order.setShippingAddressId(Long.parseLong(request.getParameter("addressId")));
            
            // Check if prescription required
            boolean requiresPrescription = cartItems.stream()
                .anyMatch(CartItem::isRequiresPrescription);
            order.setPrescriptionVerified(!requiresPrescription);
            
            order = orderDAO.create(order);

            // Add order items and update stock
            for (CartItem item : cartItems) {
                OrderItem orderItem = new OrderItem(order.getId(), item.getMedicineId(), 
                    item.getQuantity(), item.getMedicinePrice());
                orderDAO.addOrderItem(orderItem);
                medicineDAO.updateStock(item.getMedicineId(), item.getQuantity());
            }

            // Clear cart
            cartDAO.clearCart(userId);

            // Update payment status
            orderDAO.updatePaymentStatus(order.getId(), "COMPLETED");
            orderDAO.updateStatus(order.getId(), "CONFIRMED");

            // Redirect to success page with order number
            request.setAttribute("orderNumber", orderNumber);
            request.getRequestDispatcher("/WEB-INF/views/order-success.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("checkout?error=true");
        }
    }
}
