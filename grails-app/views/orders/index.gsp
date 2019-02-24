<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code:'com.lucafaggion.Orders.DomainName')}" />
        <g:set var="entityNamePlural" value="${message(code:'com.lucafaggion.Orders.DomainNamePlural')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <g:set var="maxPerPage" value="${(params.max!= null) ? params.max : 10}"/>
    </head>
    <body>
        <a href="#list-orders" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav shop-nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="home" controller="ControlPanel" action="index"><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
            </ul>
        </div>
        <g:form controller="${controllerName}" action="${actionName}" method="GET">
        <div class="d-flex justify-content-end m-2 align-items-center" id="list_options">
            <div class="p-2 flex-nowrap">
                <label ><g:message code="com.lucafaggion.Orders.maxPerPage"/>:</label>
                <g:select name="max" from="${[5,10,50,100]}" value="${maxPerPage}" /></div>
            <div class="p-2 pr-3 flex-nowrap">
                <input type="submit" value="${message(code:'com.lucafaggion.Submit')}" class="btn btn-primary"/>
            </div>
        </div>
        </g:form>
        <div id="list-orders" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <g:if test="${ordersCount>0}">
                <f:table collection="${ordersList}" template="table_orders_user"  code="com.lucafaggion.Orders." properties="id,user, state, price,lineItem,submittedDate"/>
                <g:displayPagination count="${ordersCount}"/>
            </g:if>
            <g:else>
                <div id="info_message">
                    <div class="alert alert-info" role="alert"><g:message code="com.lucafaggion.Orders.empty"/></div>
                </div>
            </g:else>
        </div>
    </body>
</html>