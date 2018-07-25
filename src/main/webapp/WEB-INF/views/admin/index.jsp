<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<html>
<head>

    <meta charset="utf-8">
    <title>CRUD</title>


    <%--<style> <%@include file="css/main.css" %> </style>--%>

</head>
<body>
<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("userName")) userName = cookie.getValue();
        }
    }
%>

<h2>Hi <%=userName %></h2>
<form action="/logout"  class="editor">
    </br><input class="b2" type="submit" value="Выйти">
</form>
<form action="/user" class="editor">
    </br><input class="b2" type="submit" value="user">
</form>

<div id="centr">
    <div class="reg">
        <form:form method="POST" modelAttribute="users" action="/admin/reg">
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
    </div>
    <table border="1" cellpadding="5">
        <caption><h2>Список пользователей</h2></caption>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Логин</th>
            <th>Пароль</th>
            <th>Привелегии</th>
            <th>Изменить пользователя</th>
            <th>Удалить пользователя</th>
        </tr>
        <c:forEach var="usersDataSet" items="${clients}">
            <tr>

                <td><c:out value="${usersDataSet.getId()}" /></td>
                <td><c:out value="${usersDataSet.getName()}" /></td>
                <td><c:out value="${usersDataSet.getLogin()}" /></td>
                <td><c:out value="${usersDataSet.getPassword()}" /></td>
                <td><c:out value="${usersDataSet.getRole()}" /></td>
                <td>
                    <form action="/admin/user/edit${usersDataSet.getId()}-" method="post">
                        <input class="b2" type="submit" value="Изменить">
                            <input name="value1" type="hidden" value="${usersDataSet.getId()}">
                        </form>
                    </td>
                    <td>
                        <form action="/admin/user/delete${usersDataSet.getId()}-" method="post">
                            <input class="b3" type="submit" value="Удалить" >
                           <%-- <input name="idDelete" type="hidden" value="${usersDataSet.getId()}"> --%>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
