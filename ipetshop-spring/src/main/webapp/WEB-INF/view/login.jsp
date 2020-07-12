<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Petshop login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>"/>
</head>
<body>
<header id="header" class="header">
    <div class="container">
        <div class="nav">
            <a href="<spring:url value="/main"/>"><img id='logo-img' src="<c:url value="/resources/img/logo.svg"/>"
                                                       alt="home"
                                                       title="HOME"></a>
            <div class="menu">
                <div class="user">

                    <c:if test="${user ne null}">
                        <c:out value="${user.nameUser}"/>
                    </c:if>
                </div>
                <div class="rus-angl">
                    <a href="?language=en"><spring:message code="btn.engl"/></a>
                    <span>|</span>
                    <a href="?language=ru"><spring:message code="btn.rus"/></a>
                </div>
                <img id='form-img' src="<c:url value="/resources/img/form.svg"/>" alt="get-form">
                <c:if test="${user ne null}">
                    <a href="<spring:url value="/showBasket"/>"><img src="<c:url value="/resources/img/basket.svg"/>"
                                                                     alt="basket"></a>
                </c:if>
            </div>
        </div>
        <div class="popup hiden">
            <c:if test="${user eq null}">
                <a href="<spring:url value="/users/showFormForAddUser"/>"><spring:message code="btn.registration"/></a>
                <a href="<spring:url value="/users/authorization"/>"><spring:message code="btn.authorization"/></a>
            </c:if>
            <c:if test="${user ne null}">
                <a href="<spring:url value="/users/deleteUser"/>"><spring:message code="btn.delete.user"/></a>
            </c:if>
        </div>
    </div>
</header>
<section id="section-form" class="section-form">
    <div class="container">
        <div class="forms">
            <div class="login-form">
                <h2>Вход</h2>
                <form:form action="login" modelAttribute="userAuthority" method="post">
                    <table>
                        <tr>
                            <td><label for="login">Логин:</label></td>
                            <td><form:input path="login" name="login" type="text" placeholder="login"/></td>
                        </tr>
                        <tr>
                            <td><label for="password">Пароль:</label></td>
                            <td><form:input path="password" type="password" name="password" placeholder="password"/></td>
                        </tr>
                    </table>
                    <input type="submit" value="войти">
                </form:form>
            </div>
        </div>
    </div>
</section>
<script src="<c:url value="/resources/script.js"/>"></script>
</body>
</html>
