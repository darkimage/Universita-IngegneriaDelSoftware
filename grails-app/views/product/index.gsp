<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.Product.DomainName', default: 'Product')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.Product.DomainNamePlural', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <g:set var="current_cat" value="${(params.cat!= null) ? params.cat : 1}"/>
        <g:set var="maxPerPage" value="${(params.max!= null) ? params.max : 5}"/>
    </head>
    <body>
        <a href="#list-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="home" controller="ControlPanel" action="index"><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>

        <g:form controller="${controllerName}" action="${actionName}" method="GET">
        <div class="d-flex justify-content-end m-2 align-items-center" id="list_options">
            <div class="p-2 flex-nowrap">
                <label ><g:message code="com.lucafaggion.Product.SelectCategory"/>:</label>
                <g:select name="cat" from="${categories}" value="${current_cat}" optionKey="id" optionValue="name" default = "1"/>
            </div>
            <div class="p-2 flex-nowrap">
                <label ><g:message code="com.lucafaggion.Product.maxPerPage"/>:</label>
                <g:select name="max" from="${[5,10,50,100]}" value="${maxPerPage}" /></div>
            <div class="p-2 pr-3 flex-nowrap">
                <input type="submit" value="${message(code:'com.lucafaggion.Submit')}" class="btn btn-primary"/>
            </div>
        </div>
        </g:form>
    
        <div id="list-product" class="content" role="main">
            <h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <g:if test="${productList.size()>0}">
                <f:table collection="${productList}" template="table_product_back" except="['photo','description','id']" />
            </g:if>
            <g:else>
                <div id="info_message">
                    <div class="alert alert-info" role="alert"><g:message code="com.lucafaggion.Product.empty"/></div>
                </div>
            </g:else>
            
            <g:displayPagination count="${productCount}"/>
        </div>
    </body>
</html>