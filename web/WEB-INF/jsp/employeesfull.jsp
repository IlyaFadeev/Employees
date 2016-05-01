<%--
  Created by IntelliJ IDEA.
  User: Ilia Komarov
  Date: 01.05.2016
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ page import="pojo.EMPLOYEES" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<h1>Departments</h1>
<table>
    <tr>
        <td>
            Name
        </td>
        <td>
            Surname
        </td>
        <td>
            Hiredate
        </td>
        <td>
            Job
        </td>
        <td>
            Manager
        </td>
        <td>
            Salary
        </td>
        <td>

        </td>
    </tr>
    <c:forEach var="listValue" items="${emps}">
        <tr>
            <td>
                    ${listValue.firstName}
            </td>
            <td>
                    ${listValue.secondName}
            </td>
            <td>
                    ${listValue.hireDate}
            </td>
            <td>
                    ${listValue.job}
            </td>
            <td>
                    ${listValue.mgr}
            </td>
            <td>
                    ${listValue.sal}
            </td>
            <td>
                <a href="employees?empno=${listValue.empNo}">View full info</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>