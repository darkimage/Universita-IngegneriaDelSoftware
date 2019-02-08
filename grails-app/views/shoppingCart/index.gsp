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
            <g:displayFlashMsg flash="${flash}"/>
            
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
            <g:displayPagination count="${itemsCount}"/>
        </g:if>
        <g:else>
            <%-- SHOPPING CART EMPTY --%>
            <div id="info_message">
                <div class="alert alert-info" role="alert"><g:message code="com.lucafaggion.ShoppingCart.empty"/></div>
            </div>
        </g:else>
        </div>
    </body>
</html>
