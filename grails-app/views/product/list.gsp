<html>
    <head> 
        <title><g:message code="com.lucafaggion.Product.list" /></title>
        <meta name="layout" content="main" />
        <asset:javascript src="jquery-3.3.1.min.js"/>
        <g:set var="current_cat" value="${(params.cat!= null) ? params.cat : 1}"/>
        <g:set var="maxPerPage" value="${(params.max!= null) ? params.max : 5}"/>
    </head>
    <body>
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

        <div id="list-Product" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="${[(categories) ? categories.get(current_cat.toInteger()-1).name : message(code:'com.lucafaggion.Product.nocategories')]}" />
            </h1>
            <g:displayFlashMsg flash="${flash}"/>
            <g:if test="${productList.size()>0}">
                <f:table template="table_product" collection="${productList}" except="featured,id"/>
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