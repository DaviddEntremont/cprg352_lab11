<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        
        Please enter your email address to reset your password.
        
         <form action="reset" method="post">
            Email Address <input type="text" name="email" value="${email}"><br>
            <input type="hidden" name="action" value="sendemail">
            <input type="submit" value="Submit">
        </form>
            
        <c:if test="${messagesent}">
        <p>A verification email has been sent!</p>
        </c:if>
    </body>
</html>
