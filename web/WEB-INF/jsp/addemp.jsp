<%--
  Created by IntelliJ IDEA.
  User: Fadeev
  Date: 4/17/2016
  Time: 2:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add dept</title>
    <style type="text/css">
        <%@ include file="styles.css" %>
    </style>
    <h1>
        <a href="mainPage">Main page</a>
    </h1>

</head>
<body>


<form action="save" method="post">
    <input type="text" name="empno" value="${employee.empNo}" READONLY hidden>
    <label>First name:</label>
    <input type="text" name="first_name">
    <br>
    <br>
    <label>Last name:</label>
    <input type="text" name="last_name">
    <br>
    <br>
    <label>Job:</label>
    <select name="job">
        <c:forEach var="listValue" items="${jobs}">
            <option>
                    ${listValue.jname}
            </option>
        </c:forEach>

    </select>
    <br>
    <br>
    <label>Manager:</label>
    <select name="mgr">
        <c:forEach var="listValue" items="${empls}">
            <option>
                    ${listValue.firstName}
            </option>
        </c:forEach>

    </select>
    <br>
    <br>
    <label>Hire date:</label>
    <input type="date" name="hiredate">
    <br>
    <br>
    <label>Salary:</label>
    <input type="text" name="salary">
    <br>
    <br>
    <label>Start time off date:</label>
    <input type="date" name="start">
    <br>
    <br>
    <label>End time off date:</label>
    <input type="date" name="end">
    <br>
    <br>
    <label>Type of time off:</label>
    <select name="type">
        <c:forEach var="listValue" items="${types}">
            <option>
                    ${listValue.type}
            </option>
        </c:forEach>

    </select>
    <br>
    <br>
    <input type="submit" value="save">
</form>

</body>
</html>
