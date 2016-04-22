<%--
  Created by IntelliJ IDEA.
  User: Fadeev
  Date: 4/23/2016
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>
  Search result:
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
  <c:forEach var="listValue" items="${employees}">
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
</body>
</html>
