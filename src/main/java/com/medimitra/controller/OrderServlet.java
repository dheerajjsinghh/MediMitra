package com.medimitra.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.medimitra.dao.OrderDAO;
import com.medimitra.model.Order;
import com.medimitra.model.OrderItem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            List<Order> orders = orderDAO.findByUserId(userId);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading orders");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}

@WebServlet("/order-details")
class OrderDetailsServlet extends HttpServlet {
    private OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        Long orderId = Long.parseLong(request.getParameter("id"));

        try {
            Order order = orderDAO.findById(orderId);
            if (order == null || !order.getUserId().equals(userId)) {
                response.sendRedirect("orders");
                return;
            }

            List<OrderItem> orderItems = orderDAO.findOrderItems(orderId);
            request.setAttribute("order", order);
            request.setAttribute("orderItems", orderItems);
            request.getRequestDispatcher("/WEB-INF/views/order-details.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("orders?error=true");
        }
    }
}
