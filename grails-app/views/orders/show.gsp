<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code:'com.lucafaggion.Orders.DomainName')}" />
        <g:set var="entityNamePlural" value="${message(code:'com.lucafaggion.Orders.DomainNamePlural')}" />

        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-orders" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav shop-nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:access expression="hasAnyRole('ROLE_ADMIN','ROLE_DIPENDENTE')">
                <li><g:link class="home" controller="ControlPanel" action="index"><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
                <li><g:link class="home" controller="Profile" action="index"><g:message code="com.lucafaggion.Profile.DomainName"/></g:link></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
                </sec:access>
            </ul>
        </div>
        <div id="show-orders" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>

            <div class="container-fluid py-2">
                <div class="row">
                    <div class="col-md">
                        <ul class="list-group">
                            <li class="list-group-item active"><g:message code="com.lucafaggion.Orders.detailheader"/></li>
                            <g:displayRow code="com.lucafaggion.Orders.price"><g:formatPrice value="${this.orders.price}"/></g:displayRow>
                            <g:displayRow code="com.lucafaggion.Orders.state">${this.orders.state}</g:displayRow>
                            <g:displayRow code="com.lucafaggion.Orders.submitteddate"><g:formatDate format="dd-MM-yyyy" date="${this.orders.submittedDate}"/></g:displayRow>
                        </ul>
                    </div>
                    <div class="col-md">
                        <h1><g:message code="com.lucafaggion.Orders.listitems"/></h1>
                        <f:table template="table_lineItems" collection="${this.lineItems}" properties="subProduct,price,quantity" code="com.lucafaggion.LineItem.Fields."/>
                    </div>
                </div>
            </div>
            <g:form resource="${this.orders}" method="DELETE">
                <fieldset class="buttons">
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
