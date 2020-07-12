<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Petshop basket</title>
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
<section id="section-basket" class="section-basket">
    <c:set var="total" value="${0}"/>
    <c:forEach var="tempGoods" items="${listGoods}">
        <c:url var="deleteGoods" value="/deleteGoodsInBasket">
            <c:param name="goodsId" value="${tempGoods.goodsId}"/>
        </c:url>
        <div class="container">

            <div class="basket">
                <div class="product-title">
                    <img src="<c:url value="/resources/img/goods/${tempGoods.goodsPicture}"/>" alt="cat_perfectfit">
                    <h3>${tempGoods.goodsManufacturer}</h3>
                </div>
                <div class="product-description">
                    <h3>${tempGoods.goodsTitle}</h3>
                    <p>${tempGoods.goodsDescription}</p>
                </div>
                <div class="product-price">
                    <h3>Цена: ${tempGoods.goodsPrice} &#8381;</h3>
                    <c:set var="total" value="${total + tempGoods.goodsPrice}"/>
                    <a href="${deleteGoods}">
                        <div class="btn"><spring:message code="btn.delete.goods"/></div>
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="basket-total"><spring:message code="total.sum"/>: <c:out value="${total}"/> &#8381;</div>
</section>
<script src="<c:url value="/resources/script.js"/>"></script>
</body>
</html>
