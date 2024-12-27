package com.jsp.controller;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.service.EmployeeDatabaseConnection;
import com.jsp.entity.Employee;

@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		
//		System.out.println("Name: " + name);
//		System.out.println("Age: " + age);
//		System.out.println("Email: " + email);
//		System.out.println("Password: " + password);
//		System.out.println("Mobile: " + mobile);


		Employee emp = new Employee();
		emp.setName(name);
		emp.setAge(age);
		emp.setEmail(email);
		emp.setPassword(password);
		emp.setMobile(mobile);

		EmployeeDatabaseConnection empsd = new EmployeeDatabaseConnection();
		empsd.saveEmployee(emp);

		HttpSession session = req.getSession();
		session.setAttribute("msg", "Registered Successfully");
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("Register.jsp");
		dispatcher.forward(req, resp);

		

		
	}
}

