<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.Product.DomainName', default: 'Product')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.Product.DomainNamePlural', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-product" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${productList}" template="table_product_back" except="['photo','description','id']" /> <%--template="table_product_back"--%>

            <div class="pagination">
                <g:paginate total="${productCount ?: 0}" />
            </div>
        </div>
    </body>
</html>