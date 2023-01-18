<%--
  Created by IntelliJ IDEA.
  User: alanlytton
  Date: 1/11/23
  Time: 8:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
  <title>Project Manager | Dashboard</title>
</head>
<body>
  <div class="container d-flex flex-column">
    <div class="container d-flex justify-content-between mt-4">
      <h1>Welcome <c:out value="${firstName}"/></h1>
      <p><a href="/logout" class="btn btn-primary">Logout</a></p>
    </div>
    <div class="container d-flex justify-content-between mt-3 mb-3">
      <h4>All Projects: </h4>
      <a href="/projects/new">New Project</a>
    </div>
    <div class="container">
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Project</th>
              <th>Team Lead</th>
              <th>Due Date</th>
              <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="project" items="${missingProjects}">
            <tr>
              <td><a href="/projects/${project.id}"><c:out value="${project.title}"/></a></td>
              <td><c:out value="${project.user.firstName}"/></td>
              <td><fmt:formatDate value="${project.dueDate}"/></td>
              <td><a href="/projects/${project.id}/join">Join Team</a></td>
            </tr>
            </c:forEach>
            </tbody>
          </table>
    </div>
    <div class="container d-flex justify-content-between mt-3 mb-3">
      <h4>Your Projects: </h4>
    </div>
    <div class="container">
      <table class="table table-striped">
        <thead>
        <tr>
          <th>Project</th>
          <th>Team Lead</th>
          <th>Due Date</th>
          <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="project" items="${teamProjects}">
          <tr>
            <td><a href="/projects/${project.id}"><c:out value="${project.title}"/></a></td>
            <td><c:out value="${project.user.firstName}"/></td>
            <td><fmt:formatDate value="${project.dueDate}"/></td>
            <c:choose>
              <c:when test="${project.user.id == id}">
                <a href="/projects/edit/${project.id}">Edit</a>
              </c:when>
              <c:otherwise>
                <td><a href="/projects/${project.id}/leave">Leave Team</a></td>
              </c:otherwise>
            </c:choose>
          </tr>
        </c:forEach>
        <c:forEach var="project" items="${leadProjects}">
          <tr>
            <td><a href="/projects/${project.id}"><c:out value="${project.title}"/></a></td>
            <td><c:out value="${project.user.firstName}"/></td>
            <td><fmt:formatDate value="${project.dueDate}"/></td>
            <c:choose>

              <c:when test="${project.user.id == id}">
                <td><a href="/projects/edit/${project.id}">Edit</a></td>
              </c:when>
              <c:otherwise>
                <td><a href="/projects/${project.id}/leave">Leave Team</a></td>
              </c:otherwise>
            </c:choose>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <script src="/webjars/jquery/jquery.min.js"></script>
  <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
