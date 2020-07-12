<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main page</title>
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
<section id="section-goods" class="section-goods">
    <c:forEach var="tempGoods" items="${listGoods}">
        <c:url var="addGoodsToBasket" value="/addGoodsToBasket">
            <c:param name="goodsId" value="${tempGoods.goodsId}"/>
        </c:url>
        <div class="container">
            <div class="product">
                <div class="product-title">
                    <img src="<c:url value="/resources/img/goods/${tempGoods.goodsPicture}"/>" alt="cat_perfectfit">
                    <h3>${tempGoods.goodsManufacturer}</h3>
                </div>
                <div class="product-description">
                    <h3>${tempGoods.goodsTitle}</h3>
                    <p>${tempGoods.goodsDescription}</p>
                    <span>Скидка: ${tempGoods.specialOffer.typeSale}</span>
                    <span>Цена со скидкой: ${tempGoods.priceSpecialOffer} &#8381;</span>
                    <span>${tempGoods.goodsType.animalType.animalType}</span>
                    <span>${tempGoods.goodsType.goodsType}</span>
                </div>
                <div class="product-price">
                    <h3>Цена: ${tempGoods.goodsPrice} &#8381;</h3>
                    <c:if test="${user ne null}">
                        <a href="${addGoodsToBasket}">
                            <div class="btn"><spring:message code="btn.to.basket"/></div>
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</section>
<script src="<c:url value="/resources/script.js"/>"></script>
</body>
</html>
