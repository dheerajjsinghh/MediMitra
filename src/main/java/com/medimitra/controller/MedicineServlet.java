package com.medimitra.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.medimitra.dao.MedicineDAO;
import com.medimitra.model.Medicine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/medicines")
public class MedicineServlet extends HttpServlet {
    private MedicineDAO medicineDAO = new MedicineDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String category = request.getParameter("category");
        String search = request.getParameter("search");

        try {
            List<Medicine> medicines;
            if (search != null && !search.trim().isEmpty()) {
                medicines = medicineDAO.search(search);
                request.setAttribute("searchKeyword", search);
            } else if (category != null && !category.isEmpty()) {
                medicines = medicineDAO.findByCategory(category);
                request.setAttribute("selectedCategory", category);
            } else {
                medicines = medicineDAO.findAll();
            }
            
            request.setAttribute("medicines", medicines);
            request.getRequestDispatcher("/WEB-INF/views/medicines.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading medicines");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
