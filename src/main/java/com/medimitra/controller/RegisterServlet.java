package com.medimitra.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.medimitra.dao.UserDAO;
import com.medimitra.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");

        try {
            // Check if email already exists
            User existing = userDAO.findByEmail(email);
            if (existing != null) {
                request.setAttribute("error", "Email already registered");
                request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
                return;
            }

            // Hash password and create user
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(username, email, passwordHash, "CUSTOMER");
            user.setFullName(fullName);
            user.setPhone(phone);
            
            userDAO.create(user);
            
            // Auto-login after registration
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            
            response.sendRedirect("medicines");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Registration failed");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }
}
