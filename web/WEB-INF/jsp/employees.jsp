<%--
  Created by IntelliJ IDEA.
  User: Fadeev
  Date: 4/17/2016
  Time: 2:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ page import="pojo.EMPLOYEES" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>


<form action="search">
    <input type="text" name="sVal">
    <input type="submit" value="search">
    <br>
    <br>
</form>
<h1>
    Employee:
</h1>
<table border="solid" bgcolor="#fffafa"  cellpadding="7">
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
        <td>

        </td>
    </tr>
        <tr>
            <td>
                 <a href="employees?empno= ${employee.empNo}">${employee.firstName}</a>
            </td>
            <td>
                    ${employee.secondName}
            </td>
            <td>
                    ${employee.hireDate}
            </td>
            <td>
                    ${employee.job}
            </td>
            <td>
                    ${employee.mgr}
            </td>
            <td>
                    ${employee.sal}
            </td>
            <td>
                <a href="remove?empno=${employee.empNo}">remove</a>
            </td>
            <td>
                <a href="update?empno=${employee.empNo}">edit</a>
            </td>
        </tr>

</table>



<h1>
    Managers:
</h1>
<table border="solid" bgcolor="#fffafa"  cellpadding="7">
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
        <td>

        </td>
    </tr>
<c:forEach var="listValue" items="${mgrs}">
  <tr>
 <td>
  <a href="employees?empno= ${listValue.empNo}">${listValue.firstName}</a>
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
          <a href="remove?empno=${listValue.empNo}">remove</a>
      </td>
      <td>
      <a href="update?empno=${listValue.empNo}">edit</a>
      </td>
  </tr>
</c:forEach>

</table>

<h1>
    Sub employees:
</h1>

<table border="solid" bgcolor="#fffafa"  cellpadding="7">

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
        <td>

        </td>
    </tr>
    <c:forEach var="listValue" items="${subEmp}">
        <tr>
            <td>
                 <a href="employees?empno= ${listValue.empNo}">${listValue.firstName}</a>
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
                <a href="remove?empno=${listValue.empNo}">remove</a>
            </td>
            <td>
                <a href="update?empno=${listValue.empNo}">edit</a>
            </td>
        </tr>
    </c:forEach>

</table>

<br>
<br>
<br>

<form action="addemp">
    <input type="submit" value="add">
    <br>
    <br>
</form>
</body>
</html>
