<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Users</h2>
    <hr/>
    <form action="users" method="put">
        <input type="submit" value="Add new user" />
    </form>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="ru.veryprosto.restVote.model.User"/>
            <tr>
                <td>${user.name}</td>
                <td>
                    <form action="restaurants/${restaurant.id}" method="post">
                        <input type="submit" value="Update" />
                    </form>
                </td>
                <td>
                <form action="restaurants/${restaurant.id}" method="delete">
                    <input type="submit"  value="Delete" />
                </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>