package com.jsp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp.entity.Employee;

public class EmployeeDatabaseConnection {

    // Static block to load the PostgreSQL driver and establish a connection
    private static Connection con = null;

    static {
        try {
            // Load and register PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            // Establish the database connection
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MyProject", "postgres", "123");
            System.out.println("PostgreSQL Driver Registered and Connection Established");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to save an Employee (Insert operation)
    public void saveEmployee(Employee emp) {
        PreparedStatement pstmt = null ;
        try {
        	System.out.println("Executing insert statement...");

            // Prepare the SQL statement
            pstmt = con.prepareStatement("INSERT INTO employee(name, age, email, password, mobile) VALUES(?,?,?,?,?)");
//            pstmt.setInt(1, emp.getId());
            pstmt.setString(1, emp.getName());
            pstmt.setInt(2, emp.getAge());
            pstmt.setString(3, emp.getEmail());
            pstmt.setString(4, emp.getPassword());
            pstmt.setLong(5, emp.getMobile());
            int row = pstmt.executeUpdate();
            // System.out.println(row + " row(s) inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to fetch Employee by ID
    public Employee fetchEmployeeById(int id) {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        Employee emp = null;
        try {
            pstmt = con.prepareStatement("SELECT * FROM employee WHERE id=?");
            pstmt.setInt(1, id);
            rst = pstmt.executeQuery();
            if (rst.next()) {
                emp = new Employee();
                emp.setId(rst.getInt("id"));
                emp.setName(rst.getString("name"));
                emp.setAge(rst.getInt("age"));
                emp.setEmail(rst.getString("email"));
                emp.setPassword(rst.getString("password"));
                emp.setMobile(rst.getLong("mobile"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rst != null) rst.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }

    // Method to fetch all Employees
    public List<Employee> fetchAllEmployees() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        List<Employee> allEmployees = new ArrayList<>();
        try {
            pstmt = con.prepareStatement("SELECT * FROM employee");
            rst = pstmt.executeQuery();
            while (rst.next()) {
                Employee emp = new Employee();
                emp.setId(rst.getInt("id"));
                emp.setName(rst.getString("name"));
                emp.setAge(rst.getInt("age"));
                emp.setEmail(rst.getString("email"));
                emp.setPassword(rst.getString("password"));
                emp.setMobile(rst.getLong("mobile"));
                allEmployees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rst != null) rst.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allEmployees;
    }

    // Method to update Employee by ID
    public void updateEmployeeById(int id, Employee emp) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement("UPDATE employee SET name=?, age=?, email=?, password=?, mobile=? WHERE id=?");
            pstmt.setString(1, emp.getName());
            pstmt.setInt(2, emp.getAge());
            pstmt.setString(3, emp.getEmail());
            pstmt.setString(4, emp.getPassword());
            pstmt.setLong(5, emp.getMobile());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to delete Employee by ID
    public void deleteEmployeeById(int id) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement("DELETE FROM employee WHERE id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to fetch Employee by Email and Password (for login)
    public Employee fetchEmployeeByEmailAndPassword(String email, String password) {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        Employee emp = null;
        try {
            pstmt = con.prepareStatement("SELECT * FROM employee WHERE email=? AND password=?");
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rst = pstmt.executeQuery();
            if (rst.next()) {
                emp = new Employee();
                emp.setId(rst.getInt("id"));
                emp.setName(rst.getString("name"));
                emp.setAge(rst.getInt("age"));
                emp.setEmail(email);
                emp.setPassword(password);
                emp.setMobile(rst.getLong("mobile"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rst != null) rst.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }

    // Static block ensures driver registration and connection establishment happens once at the start
    public static Connection getConnection() {
        return con;
    }


	
}
