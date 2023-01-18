<%--
  Created by IntelliJ IDEA.
  User: alanlytton
  Date: 1/17/23
  Time: 1:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<html>
<head>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
  <title>Project Manager | <c:out value="${project.title}"/></title>
</head>
<body>
<div class="container d-flex flex-column">
  <div class="container d-flex justify-content-between mt-3 mb-3">
    <h2>Project Details</h2>
    <a href="/home">Back to dashboard</a>
  </div>
  <div class="container">

    <h4 class="mb-4">Project: </h4> <p><c:out value="${project.title}"/></p>
    <h4 class="mb-4">Description: </h4> <p><c:out value="${project.description}"/></p>
    <h4 class="mb-4">Due Date: </h4> <p><fmt:formatDate value="${project.dueDate}"/></p>
  </div>
  <span class="d-flex"><c:if test="${id == project.user.id}">
    <form action="/projects/${project.id}/delete" method="post">
            <input type="hidden" name="_method" value="delete">
            <input type="submit" value="Delete" class="btn btn-danger ms-1">
        </form></c:if></span>
</div>

<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
