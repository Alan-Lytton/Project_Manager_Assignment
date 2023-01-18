<%--
  Created by IntelliJ IDEA.
  User: alanlytton
  Date: 1/17/23
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <title>Project Manager | New Project</title>
</head>
<body>
<div class="container d-flex flex-column align-items-center">
    <h1 class="mb-3">Create a Project</h1>
    <form:form action="/projects/new" method="post" modelAttribute="newProject">
        <div class="container d-flex flex-column">
            <p class="text-danger"><form:errors path="title"/></p>
            <p>
                <form:label path="title" cssClass="me-5">Project Title: </form:label>
                <form:input path="title" cssClass="ms-4"/>
            </p>
        </div >
        <div class="container d-flex flex-column">
            <p class="text-danger"><form:errors path="description"/></p>
            <p>
            <div>
                <form:label path="description" cssClass="me-2">Project Description: </form:label>
                <form:textarea path="description"  cssClass="ms-3"></form:textarea>
            </div>
            </p>
        </div>
        <div class="container d-flex flex-column">
            <p class="text-danger"><form:errors path="dueDate"/></p>
            <p class="d-flex align-items-center">
                <form:label  path="dueDate" cssClass="me-5">Due Date: </form:label>
                <form:input path="dueDate" type="date" cssClass="ms-5"/>
            </p>
        </div >
        <a href="/home" class="btn btn-danger mt-3 me-3">Cancel</a>
        <input type="submit" value="Submit" class="mt-3 align-self-start btn btn-primary">
    </form:form>
</div>

<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
