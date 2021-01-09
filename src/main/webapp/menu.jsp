<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<c:set var="baseURL"
       value="${pageContext.request.requestURL.substring(0, pageContext.request.requestURL.length() - pageContext.request.requestURI.length())}${pageContext.request.contextPath}/"/>
<head>
    <base href="${baseURL}"/>
    <title>Menu</title>
</head>
<body>
<section>
    <jsp:useBean id="restaurant" type="ru.veryprosto.restVote.model.Restaurant" scope="request"/>
    <h2>Menu of ${restaurant.name}</h2>
    <c:if test="${role == 'OWNER'}">
        <hr/>
        <form action="restaurants/${restaurant.id}/menu/create" method="get">
            <input type="submit" value="Add new dish"/>
        </form>
    </c:if>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Description</th>
            <th>Price</th>
            <c:if test="${role == 'OWNER'}">
                <th></th>
                <th></th>
            </c:if>
        </tr>
        </thead>
        <c:forEach items="${menuList}" var="dish">
            <jsp:useBean id="dish" type="ru.veryprosto.restVote.model.Dish"/>
            <tr>
                <td>${dish.name}</td>
                <td>${dish.price}</td>
                <c:if test="${role == 'OWNER'}">
                    <td>
                        <form action="restaurants/${restaurant.id}/menu/${dish.id}" method="get">
                            <input type="submit" value="Update"/>
                        </form>
                    </td>
                    <td>
                        <form action="restaurants/${restaurant.id}/menu/${dish.id}/delete" method="post">
                            <input type="submit" value="Delete"/>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br><br>
    <c:if test="${role == 'USER'}">
        Вы бы пошли вечером в этот ресторан?
        <table>
            <tr>
                <td>
                    <form action="restaurants/${restaurant.id}/like" method="post">
                        <input type="submit" value="Да!"/>
                    </form>
                </td>
                <td>                                            </td>
                <td>
                    <form action="restaurants/${restaurant.id}/unlike" method="post">
                        <input type="submit" value="Нет!"/>
                    </form>
                </td>
            </tr>
        </table>
    </c:if>
</section>
</body>
</html>