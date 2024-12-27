<%@ page import="com.jsp.entity.Employee" %>
<%@ page import="com.jsp.service.EmployeeDatabaseConnection" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>

<%@ include file="BootstrapLink.jsp"%> <!-- Bootstrap CSS link -->
<style type="text/css">
    .paint-card {
        box-shadow: 0px 0px 10px 0px gray;
    }
</style>
</head>
<body>
<% 
    
        
        EmployeeDatabaseConnection empd = new EmployeeDatabaseConnection();
        List<Employee> employeeList = empd.fetchAllEmployees(); 
%>

    
        <!-- Display all employees with Update and Delete buttons -->
        <div class="row">
            <div class="col-md-12">
                <div class="card paint-card">
                    <div class="card-body">
                        <p class="fs-3 text-center">Employee Details</p>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Age</th>
                                    <th>Email</th>
                                    <th>Mobile</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                   
                                    for (Employee emp1 : employeeList) {
                                %>
                                <tr>
                                    <td><%= emp1.getName() %></td>
                                    <td><%= emp1.getAge() %></td>
                                    <td><%= emp1.getEmail() %></td>
                                    <td><%= emp1.getMobile() %></td>
                                    <td>
                                        <a class="btn btn-md btn-primary" href="Profile.jsp?id=<%= emp1.getId() %>">Update</a>
                                        <a class="btn btn-md btn-danger" href="delete_profile?id=<%= emp1.getId() %>">Delete</a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    

 
</body>
</html>
