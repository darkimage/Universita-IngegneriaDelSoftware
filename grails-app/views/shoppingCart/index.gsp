<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.ShoppingCart.shoppingcart')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-cart" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="show-cart" class="content scaffold-show" role="main">
            <h1><g:message code="com.lucafaggion.ShoppingCart.shoppingcart" /></h1>
            
             <%-- DISPLAY FLASH MESSAGE --%>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <%-- SHOPPING CART DISPLAY --%>
            <g:if test="${itemsCount != 0}">

            <div class="container-fluid">
                <div class="row pt-2">
                    <div class="col-sm-6"> 
                        <ul class="list-group">
                            <li class="list-group-item active"><g:message code="com.lucafaggion.ShoppingCart.summary"/></li>
                            <li class="list-group-item"><b><g:message code="com.lucafaggion.ShoppingCart.total"/>: <g:formatPrice value="${totalprice}"/></b></li>
                            <li class="list-group-item"><b><g:message code="com.lucafaggion.ShoppingCart.articlecount" args="${[itemsCount]}"/></b></li>
                            <li class="list-group-item">
                                <g:form controller="${params.controller}" action="updateCart" method="POST">
                                <div class="row ">
                                    <div class="col-sm text-center shopping_cart_action  pt-2">
                                        <input class="btn btn-primary place_order" type="submit" name="placeorder" id="placeorder" value="${message(code:'com.lucafaggion.ShoppingCart.placeorder')}">
                                    </div>
                                    <div class="col-sm text-center shopping_cart_action  pt-2">
                                        <input class="btn btn-primary clean_order" type="submit" name="cancelorder" id="cancelorder" value="${message(code:'com.lucafaggion.ShoppingCart.cancelorder')}">
                                    </div>
                                </div>
                                </g:form>
                            </li>
                        </ul>
                    </div>
                    <div class="col-sm-6 pt-2 product_show_nopadding"> 
                        <f:table collection="${cartItems}" headers="com.lucafaggion.LineItem.Fields." template="table_shoppingcart" except="['orderid','id']"/>
                    </div>
                </div>
            </div>
        
            <%-- PAGINATION --%>
            <g:if test="${itemsCount > params.max.toInteger()}">
                <div class="pagination">
                    <g:paginate controller="ShoppingCart" action="index" total="${itemsCount}" params="${params}" />
                </div>
            </g:if>
        </g:if>
        <g:else>
            <%-- SHOPPING CART EMPTY --%>
            <div style="width:100%;padding:2em;text-align:center">
                <div class="shoppingcart_container"><g:message code="com.lucafaggiob.ShoppingCart.empty"/></div>
            </div>
        </g:else>
        </div>
    </body>
</html>
