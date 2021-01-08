<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<c:set var="baseURL"
       value="${pageContext.request.requestURL.substring(0, pageContext.request.requestURL.length() - pageContext.request.requestURI.length())}${pageContext.request.contextPath}/"/>
<head>
    <base href="${baseURL}"/>
    <title>Users</title>
</head>
<body>
<section>
    <h3><a href="start.jsp">Home</a></h3>
    <hr/>
    <h2>Users</h2>
    <hr/>
    <form action="users/create" method="get">
        <input type="submit" value="Add new user"/>
    </form>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Registration date</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${usersList}" var="user">
            <jsp:useBean id="user" type="ru.veryprosto.restVote.model.User"/>
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td> ${fn:formatDateTime(user.registered)} </td>
                <td>
                    <form action="users/${user.id}" method="post">
                        <input type="submit" value="Update"/>
                    </form>
                </td>
                <td>
                    <form action="users/${user.id}" method="delete">
                        <input type="submit" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>