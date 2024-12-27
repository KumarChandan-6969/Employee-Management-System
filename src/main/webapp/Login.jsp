<%@ page import="com.jsp.entity.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login Page</title>
    <%@ include file="BootstrapLink.jsp" %>
    <style type="text/css">
    .paint-card{
    	box-shadow: 0px 0px 10px 0px gray;
    }
    </style>
    
</head>
<body>
   
	<%@ include file="IndexNavBar.jsp" %>
    <!-- Login Form -->
    <div class="container p-5">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card paint-card">
                    <div class="card-body">
                        <p class="fs-4 text-center">User Login</p>
                        <% 
                            String msg = (String) session.getAttribute("failed");
                            if (msg != null) {
                        %>
                            <p class="fs-4 text-center text-danger"><%= msg %></p>
                            <% 
                                session.removeAttribute("failed"); // Clear after displaying
                            %>
                        <% } %>

                        <form action="LoginServlet" method="post">
                            <div class="mb-3">
                                <label class="form-label">Email Address</label>
                                <input name="email" type="email" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Password</label>
                                <input name="password" type="password" class="form-control" required>
                            </div>
                            <button type="submit" class="btn bg-secondary text-white col-md-12">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
