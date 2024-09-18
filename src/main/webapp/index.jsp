<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <h2>Register User</h2>

    <!-- Display success or error messages dynamically from Servlet -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<h3>" + message + "</h3>");
        }
    %>

    <form action="register" method="post">
        Username: <input type="text" name="uname" required><br>
        Email: <input type="email" name="uemail" required><br>
        Password: <input type="password" name="upwd" required><br>
        Mobile: <input type="text" name="umobile" required><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>
