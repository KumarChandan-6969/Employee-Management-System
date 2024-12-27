package com.jsp.controller;




import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.service.EmployeeDatabaseConnection;

@WebServlet("/delete_profile")
public class DeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		EmployeeDatabaseConnection empd = new EmployeeDatabaseConnection();
		empd.deleteEmployeeById(id);
		resp.sendRedirect("Home.jsp");
	}
}
