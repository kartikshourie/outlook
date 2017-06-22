<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<c:if test="${error ne null}">
  <div class="alert alert-danger">${error}</div>
</c:if>
<div class="jumbotron">
  <h1>Java Web App Tutorial</h1>
  <p>This sample uses the Mail API to read messages in your inbox.</p>
  <p><a class="btn btn-lg btn-primary" href="<spring:url value="${loginUrl}" />">Click here to login</a></p>
</div>
</body>
</html>