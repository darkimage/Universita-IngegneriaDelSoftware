<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.ShoppingCart.shoppingcart')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <style>
        .container{
            background-color: #efefef;
            overflow: hidden;
            -moz-box-shadow: 0 0 3px 1px #aaaaaa;
            -webkit-box-shadow: 0 0 3px 1px #aaaaaa;
            box-shadow: 0 0 3px 1px #aaaaaa;
            border-radius:1em;
            padding: 2em 6em;
            font-size: large;
            font-weight: bold;
            color: #a7a7a7
        }

        .responsive-row
        {
            display: table-row;
        }
        .responsive-row > *
        {
            display: table-cell;
        }
        @media screen and (max-width: 500px) {
            .responsive-row
            {
                display: block;
                float: left;
            }
            .responsive-row > *
            {
                display: block;
            }
}
        </style>
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
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:if test="${itemsCount != 0}">
            <div class="responsive-row">
                <div style="width:100%;">
                    <f:table collection="${cartItems}" template="table_shoppingcart" except="['orderid','id']"/>
                </div>
                <div style="padding-left: 1em;">
                    <g:form controller="${params.controller}" action="updateCart" method="POST">
                    <fieldset class="buttons" style="padding: 0.3em 1.8em 1.25em; margin-right: 1em;">
                        <div style="padding-right:2em;margin-top:1em"><b><g:message code="com.lucafaggion.ShoppingCart.total"/>: <g:formatPrice value="${totalprice}"/></b><div><br>
                        <div style="margin-top: 2em;">
                        <g:submitButton name="placeorder" value="${message(code:'com.lucafaggion.ShoppingCart.placeorder')}"/>
                        <g:submitButton name="cancelorder" value="${message(code:'com.lucafaggion.ShoppingCart.cancelorder')}"/>
                        </div>
                    </fieldset>
                    </g:form>
                </div>
            </div>
            <g:if test="${itemsCount > params.max.toInteger()}">
                <div class="pagination">
                    <g:paginate controller="ShoppingCart" action="index" total="${itemsCount}" params="${params}" />
                </div>
            </g:if>
        </g:if>
        <g:else>
            <div style="width:100%;padding:2em;text-align:center">
                <div class="container"><g:message code="com.lucafaggiob.ShoppingCart.empty"/></div>
            </div>
        </g:else>
        </div>
    </body>
</html>
