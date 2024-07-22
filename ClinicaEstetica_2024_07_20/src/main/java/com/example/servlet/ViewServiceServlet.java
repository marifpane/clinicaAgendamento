// ViewServiceServlet.java
package com.example.servlet;

import com.example.model.Service;
import com.example.dao.ServiceDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ViewServiceServlet
 * Handles viewing of a specific service.
 */
@WebServlet("/viewService")
public class ViewServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ServiceDao serviceDao = new ServiceDao();
            Service service = serviceDao.getById(id);
            request.setAttribute("service", service);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewService.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid service ID.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving service details.");
        }
    }
}