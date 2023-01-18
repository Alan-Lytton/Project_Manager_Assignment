<%--
  Created by IntelliJ IDEA.
  User: alanlytton
  Date: 1/11/23
  Time: 8:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <title>Project Manager | Login/Registration</title>
</head>
<body>
<div class="container d-flex flex-column">
    <div class="container ms-3">
        <h1 class="text-primary mb-3">Product Manager</h1>
        <p class="fw-bold mb-5">A place for teams to manage projects.</p>
    </div>
    <div class="container d-flex">
    <div class="container d-flex flex-column">
        <h2 class="ms-2">Registration</h2>
        <form:form action="/register" modelAttribute="newUser" method="post">
            <div class="container d-flex flex-column">
                <p class="text-danger"><form:errors path="firstName"/></p>
                <p>
                    <form:label path="firstName">First Name: </form:label>
                    <form:input cssClass="ms-2" path="firstName"/>
                </p>
            </div>
            <div class="container d-flex flex-column">
                <p class="text-danger"><form:errors path="lastName"/></p>
                <p>
                    <form:label path="lastName">Last Name: </form:label>
                    <form:input cssClass="ms-2" path="lastName"/>
                </p>
            </div>
            <div class="container d-flex flex-column">
                <p class="text-danger"><form:errors path="email"/></p>
                <p>
                    <form:label cssClass="me-4" path="email">Email: </form:label>
                    <form:input cssClass="ms-4" path="email"/>
                </p>
            </div>
            <div class="container d-flex flex-column">
                <p class="text-danger"><form:errors path="password"/></p>
                <p>
                    <form:label path="password">Password: </form:label>
                    <form:input type="password" path="password" cssClass="ms-3"/>
                </p>
            </div>
            <div class="container d-flex flex-column">
                <p class="text-danger"><form:errors path="confirm"/></p>
                <p>
                    <form:label path="confirm">Confirm PW: </form:label>
                    <form:input type="password" path="confirm"/>
                </p>
            </div>
            <input type="submit" value="Submit" class="btn btn-primary align-self-start ms-2">
        </form:form>

    </div>
    <div class="container d-flex flex-column">
        <h2 class="ms-2">Login</h2>
        <form:form action="/login" modelAttribute="newLogin" method="post">
            <div class="container d-flex flex-column">
                <p class="text-danger"><form:errors path="email"/></p>
                <p>
                    <form:label cssClass="me-3" path="email">Email: </form:label>
                    <form:input cssClass="ms-3" path="email"/>
                </p>
            </div>
            <div class="container d-flex flex-column">
                <p class="text-danger"><form:errors path="password"/></p>
                <p>
                    <form:label path="password">Password: </form:label>
                    <form:input type="password" path="password"/>
                </p>
            </div>
            <input type="submit" value="Submit" class="btn btn-primary align-self-start ms-2">
        </form:form>
    </div>
    </div>

</div>

<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
