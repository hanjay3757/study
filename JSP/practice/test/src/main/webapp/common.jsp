<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<a href='q06.jsp'>페이지1</a>
<a href='q06_2.jsp'>페이지2</a>

<%
    // Retrieve parameters as Strings
    String xParam = request.getParameter("x");
    String yParam = request.getParameter("y");

    // Initialize variables
    int x = 0;
    int y = 0;

    // Process x parameter
    if (xParam != null && !xParam.isEmpty()) {
        try {
            x = Integer.parseInt(xParam); // Parse x parameter to integer
        } catch (NumberFormatException e) {
            // Handle error or invalid number, e.g., log error or use default value
            x = 0; // Default value in case of error
        }
    }

    // Process y parameter
    if (yParam != null && !yParam.isEmpty()) {
        try {
            y = Integer.parseInt(yParam); // Parse y parameter to integer
        } catch (NumberFormatException e) {
            // Handle error or invalid number, e.g., log error or use default value
            y = 0; // Default value in case of error
        }
    }
%>

<!-- Output the values -->
<p>x: <%= x %></p>
<p>y: <%= y %></p>
