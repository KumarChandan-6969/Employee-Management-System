package com.jsp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.jsp.service.EmployeeDatabaseConnection;
import com.jsp.entity.Employee;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Authenticate the user
        EmployeeDatabaseConnection empsd = new EmployeeDatabaseConnection();
       Employee emp = empsd.fetchEmployeeByEmailAndPassword(email, password);
       HttpSession session = req.getSession();
       
       System.out.println(emp);

        if (emp != null) {
            // User found: successful login
            session.setAttribute("userObj", emp); // Set user in session
            session.removeAttribute("failed"); // Clear any previous failure message
            resp.sendRedirect("Home.jsp"); // Redirect to home page
        } else {
            // Invalid credentials: failed login
            session.setAttribute("failed", "Invalid Credentials");
            resp.sendRedirect("Login.jsp"); // Redirect back to login page with error
        }
        System.out.println("hii.....");
    }
    
   
}
