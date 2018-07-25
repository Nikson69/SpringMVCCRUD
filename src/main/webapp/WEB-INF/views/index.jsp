<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 5/12/18
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CRUD</title>
</head>
<body>
<form action="login" method="post">
    login:
    <input name="login" value="">
    </br>password:
    <input type="password" name="password" value="">
    </br><input class="b2" type="submit"  value="sing in">
</form>
<form action="/user" class="editor">
    </br><input class="b2" type="submit" value="user">
</form>
<form action="/admin" class="editor">
    </br><input class="b2" type="submit" value="admin">
</form>


<%--
<form:form method="POST" action="/login">
    <table>
        <tr>
            <td><label for="name">Login: </label> </td>
            <td><form:input name="login" path="name" id="name"/></td>
            <td><form:errors path="login" cssClass="login"/></td>
        </tr>

        <tr>
            <td><label for="password">password: </label> </td>
            <td><form:input name="password" path="password" id="password"/></td>
            <td><form:errors path="login" cssClass="login"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Login"/>
            </td>
        </tr>
    </table>
</form:form>
<form action="/user" method="post" class="editor">
    </br><input class="b2" type="submit" value="user">
</form>
<form action="/admin" method="post" class="editor">
    </br><input class="b2" type="submit" value="admin">
</form>--%>
</body>
</html>
