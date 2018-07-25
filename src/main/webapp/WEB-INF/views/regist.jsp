<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 6/8/18
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>reg</title>
</head>

<form:form method="POST" modelAttribute="users" action="/reg">
    <form:input type="hidden" path="role" id="role" value="user"/>
    <table>
        <tr>
            <td><label for="name">Name: </label> </td>
            <td><form:input path="name" id="name"/></td>
        </tr>

        <tr>
            <td><label for="login">Логин: </label> </td>
            <td><form:input path="login" id="login"/></td>
        </tr>

        <tr>
            <td><label for="password">password: </label> </td>
            <td><form:input path="password" id="password"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Register"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
