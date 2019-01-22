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
            <table style="border-top: 0px">
            <tr>
            <td>
            <f:table collection="${cartItems}" template="table_shoppingcart" except="['orderid','id']"/>
            <td>
            <td>
                <g:form controller="${params.controller}" action="updateCart" method="POST">
                <fieldset class="buttons" style="padding: 0.3em 1.8em 1.25em;">
                   <b><g:message code="com.lucafaggion.ShoppingCart.total"/>: <b><g:formatPrice value="${totalprice}"/></b></b><br>
                    <div style="margin-top: 2em;">
                    <g:submitButton name="placeorder" value="${message(code:'com.lucafaggion.ShoppingCart.placeorder')}"/>
                    <g:submitButton name="cancelorder" value="${message(code:'com.lucafaggion.ShoppingCart.cancelorder')}"/>
                    </div>
                </fieldset>
                </g:form>
            </td>
            </tr>
            </table>
            </span>
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
