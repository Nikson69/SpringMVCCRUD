<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form:form method="POST" modelAttribute="users" action="/admin/user/update">
    <form:input type="hidden" path="id" id="id"/>
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
            <td><label for="role">Привелегии: </label> </td>
            <td>
                <form:select path="role">
                    <form:options items="${roleList}" />
                </form:select>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Update"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
