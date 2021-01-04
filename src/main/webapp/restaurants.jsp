<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Restaurants</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Restaurants</h2>
    <hr/>
    <form action="restaurants" method="put">
        <input type="submit" value="Add restaurant" />
    </form>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Rating</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${restaurantList}" var="restaurant">
            <jsp:useBean id="restaurant" type="ru.veryprosto.restVote.model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td>${restaurant.rating}</td>
                <td>
                    <form action="restaurants" method="get">
                        <input type="submit" value="Update" />
                    </form>
                </td>
                <td>
                <form action="restaurants" method="delete">
                    <input type="submit"  value="Delete" />
                </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>