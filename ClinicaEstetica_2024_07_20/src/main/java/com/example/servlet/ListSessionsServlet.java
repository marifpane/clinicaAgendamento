// ListSessionsServlet.java
package com.example.servlet;

import com.example.model.Session;
import com.example.dao.SessionDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ListSessionsServlet
 * Handles listing of sessions.
 */
@WebServlet("/listSessions")
public class ListSessionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SessionDao sessionDao = new SessionDao();
            List<Session> sessions = sessionDao.getList();
            request.setAttribute("sessions", sessions);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listSessions.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,

 "Invalid service ID.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while listing sessions.");
        }
    }
}