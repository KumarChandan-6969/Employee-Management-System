<%@page import="com.jsp.entity.Employee"%>
<%@page import="com.jsp.service.EmployeeDatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Update</title>
<%@ include file="BootstrapLink.jsp"%>
<%@ include file="UserNavBar.jsp" %>
<style type="text/css">
    .paint-card {
        box-shadow: 0px 0px 10px 0px gray;
    }
</style>
</head>
<body>

<% 
    // Check session and redirect if not logged in
    Employee emp = (Employee) session.getAttribute("userObj"); 
    if (emp == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Fetch employee details from the database
    int id = Integer.parseInt(request.getParameter("id"));
    EmployeeDatabaseConnection empd = new EmployeeDatabaseConnection();
    Employee emp1 = empd.fetchEmployeeById(id);
%>

<div class="container p-5">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card paint-card">
                <div class="card-body">
                    <p class="fs-4 text-center"><%= emp1.getName() %>'s Profile</p>
                    <form action="update_profile" method="post">
                        <!-- Hidden field to pass employee ID -->
                        <input type="hidden" name="id" value="<%= emp1.getId() %>" />

                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input name="name" type="text" class="form-control" value="<%= emp1.getName() %>" />                    
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Age</label>
                            <input name="age" type="text" class="form-control" value="<%= emp1.getAge() != 0 ? emp1.getAge() : "" %>" />                    
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email Address</label>
                            <input name="email" type="email" class="form-control" value="<%= emp1.getEmail() %>" />                    
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input name="password" type="password" class="form-control" value="<%= emp1.getPassword() %>" />                    
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mobile</label>
                            <input name="mobile" type="text" class="form-control" value="<%= emp1.getMobile() != 0 ? emp1.getMobile() : "" %>" />                    
                        </div>
                        <button type="submit" class="btn bg-secondary text-white col-md-12">Update</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
