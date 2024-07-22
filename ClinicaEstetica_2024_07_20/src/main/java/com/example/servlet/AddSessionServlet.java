// AddSessionServlet.java
package com.example.servlet;

import com.example.model.Session;
import com.example.dao.SessionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class AddSessionServlet
 * Handles adding of a new session.
 */
@WebServlet("/addSession")
public class AddSessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int serviceId = Integer.parseInt(request.getParameter("serviceId"));
            String dateString = request.getParameter("date");
            String time = request.getParameter("time");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);

            Session session = new Session();
            session.setServiceId(serviceId);
            session.setDate(date);
            session.setTime(time);

            SessionDao sessionDao = new SessionDao();
            sessionDao.save(session);

            response.sendRedirect("viewService?id=" + serviceId);
        } catch (NumberFormatException | java.text.ParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the session.");
        }
    }
}