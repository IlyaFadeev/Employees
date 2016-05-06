<%--
  Created by IntelliJ IDEA.
  User: Ilia Komarov
  Date: 02.05.2016
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Directories</title>
    <style type="text/css">
        <%@ include file="styles.css" %>
    </style>
    <h1>
        <a href="mainPage">Main page</a>
    </h1>
</head>
<body>
    <h1>Jobs:</h1>
    <table>
        <c:forEach var="list" items="${jobs}">
            <tr>
                <td>${list.jname}</td>
                <td><a href="updatedir?type=job&dir=${list.jname}">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="updatedir">
        <input type="hidden" name="type" value="job">
        <input type="submit" value="Add job">
    </form>
    <h1>Locations:</h1>
    <table>
        <c:forEach var="list" items="${locates}">
            <tr>
                <td>${list.lname}</td>
                <td><a href="updatedir?type=loc&dir=${list.lname}">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="updatedir">
        <input type="hidden" name="type" value="loc">
        <input type="submit" value="Add location">
    </form>
    <h1>Types of time off:</h1>
    <table>
        <c:forEach var="list" items="${timeofftypes}">
            <tr>
                <td>${list.type}</td>
                <td><a href="updatedir?type=timeoff&dir=${list.type}">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="updatedir">
        <input type="hidden" name="type" value="timeoff">
        <input type="submit" value="Add type of time off">
    </form>
</body>
</html>
