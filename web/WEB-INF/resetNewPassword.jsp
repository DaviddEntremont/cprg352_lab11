<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Enter a new password</h1>
        
        <form action="reset" method="post">
            <input type="text" name="newpassword" value="${password}"><br>
            <input type="hidden" name="thisuuid" value="${UUID}">
            <input type="hidden" name="action" value="resetpassword">
            <input type="submit" value="Submit">
        </form>
            
        <c:if test="${passwordreset}">
        <p>Your password has been successfully reset!</p>
        <a href="login">Go To Login</a>
        </c:if>
        
    </body>
</html>
