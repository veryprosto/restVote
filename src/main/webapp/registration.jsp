<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<c:set var="baseURL"
       value="${pageContext.request.requestURL.substring(0, pageContext.request.requestURL.length() - pageContext.request.requestURI.length())}${pageContext.request.contextPath}/"/>
<head>
    <base href="${baseURL}"/>
    <title>Restaurant login</title>
</head>
<body>
<form action="registration" method="post">
    <fieldset>
        <h2>Введите данные авторизации</h2>
        <%-- <div if="${param.error}">
             <div class="alert alert-danger">
                 Вы ввели неправильные данные
             </div>
         </div>--%>
        <select name="role">
            <option value="USER">User</option>
            <option value="OWNER">Owner</option>
        </select>

        <div class="input-group">
            <input id="username" type="text" class="form-control" name="username"
                   placeholder="логин" required="required"/>
        </div>
        <br/>
        <div class="input-group">
            <input id="password" type="password" class="form-control" name="password"
                   placeholder="пароль" required="required"/>
        </div>

        <br/>
        <div>
            <button type="submit" class="btn btn-success">Войти</button>
        </div>
    </fieldset>
</form>
<hr/>
</body>
</html>