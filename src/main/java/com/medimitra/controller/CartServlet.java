package com.medimitra.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.medimitra.dao.CartDAO;
import com.medimitra.model.CartItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private CartDAO cartDAO = new CartDAO();

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
            BigDecimal totalAmount = cartItems.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("totalAmount", totalAmount);
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading cart");
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

        String action = request.getParameter("action");
        
        try {
            if ("add".equals(action)) {
                Long medicineId = Long.parseLong(request.getParameter("medicineId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cartDAO.addItem(userId, medicineId, quantity);
                response.sendRedirect("cart");
            } else if ("update".equals(action)) {
                Long cartId = Long.parseLong(request.getParameter("cartId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cartDAO.updateQuantity(cartId, quantity);
                response.sendRedirect("cart");
            } else if ("remove".equals(action)) {
                Long cartId = Long.parseLong(request.getParameter("cartId"));
                cartDAO.removeItem(cartId);
                response.sendRedirect("cart");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("cart?error=true");
        }
    }
}
