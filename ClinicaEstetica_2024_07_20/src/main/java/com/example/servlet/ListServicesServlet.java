// ListServicesServlet.java
package com.example.servlet;

import java.io.IOException;
import java.util.List;

import com.example.dao.ServiceDao;
import com.example.model.Service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListServicesServlet
 * Handles listing of services.
 */
@WebServlet("/listServices")
public class ListServicesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServiceDao serviceDao = new ServiceDao();
            List<Service> services = serviceDao.getList();
            request.setAttribute("services", services);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listServices.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while listing services.");
        }
    }
}