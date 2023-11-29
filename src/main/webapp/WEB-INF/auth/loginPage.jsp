<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login Page</title>
	</head>
	<body>
	
			<c:if test="${logoutMessage != null}">
				<div><c:out value="${logoutMessage}"></c:out></div>
	    	</c:if>
    
	    <h1>Login</h1>
	    <form method="POST" action="/login">
	
			<c:if test="${errorMessage != null}">
				<div><c:out value="${errorMessage}"></c:out></div>
	    	</c:if>
    
	        <p>
	            <label for="username">Username</label>
	            <input type="text" id="username" name="username"/>
	        </p>
	        <p>
	            <label for="password">Password</label>
	            <input type="password" id="password" name="password"/>
	        </p>
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input type="submit" value="Login!"/>
	    </form>
	    
	    <a href="/register">Create account</a>
	</body>
</html>