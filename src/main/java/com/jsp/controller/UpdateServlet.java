package com.jsp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.service.EmployeeDatabaseConnection;
import com.jsp.entity.Employee;

@WebServlet("/update_profile")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieve parameters from the request
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            long mobile = Long.parseLong(req.getParameter("mobile")); // Fixed issue here
            String email = req.getParameter("email");
            String password = req.getParameter("password"); // Added password retrieval
            int id = Integer.parseInt(req.getParameter("id"));

            // Set employee details
            Employee emp = new Employee();
            emp.setName(name);
            emp.setAge(age);
            emp.setMobile(mobile);
            emp.setEmail(email);
            emp.setPassword(password); // Include password in the update
            emp.setId(id);

            // Perform update operation
            EmployeeDatabaseConnection empsd = new EmployeeDatabaseConnection();
            empsd.updateEmployeeById(id, emp);

            // Redirect after successful update
            resp.sendRedirect("Home.jsp?id=" + id);
        } catch (NumberFormatException e) {
            resp.getWriter().write("Error: Invalid input for age, mobile, or ID.");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error: An unexpected error occurred.");
        }
    }
}
