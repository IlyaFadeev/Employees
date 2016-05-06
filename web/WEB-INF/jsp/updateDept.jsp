<%--
  Created by IntelliJ IDEA.
  User: Ilia Komarov
  Date: 02.05.2016
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update</title>
    <style type="text/css">
        <%@ include file="styles.css" %>
    </style>
    <h1>
        <a href="mainPage">Main page</a>
    </h1>
</head>
<body>
<form action="savedept">
    <label>Dept no:</label>
    <input type="text" name="deptno" value="${dept.deptno}" READONLY>
    <br>
    <br>
    <label>Dept name:</label>
    <input type="text" name="dname" value="${dept.dname}">
    <br>
    <br>
    <label>Location:</label>
    <select name="location">
        <c:forEach var="listValue" items="${locations}">
            <option <c:if test="${listValue.lname.equals(dept.location)}">selected</c:if>>
                    ${listValue.lname}
            </option>
        </c:forEach>
    </select>
    <br>
    <br>
    <label>Manager:</label>
    <select name="mgr">
        <c:forEach var="listValue" items="${emps}">
            <option <c:if test="${listValue.empNo.equals(dept.manager)}">selected</c:if>>
                ${listValue.firstName} ${listValue.secondName}
            </option>
        </c:forEach>
    </select>
    <br>
    <br>
    <input type="submit" value="Update dept">
</form>
</body>
</html>
