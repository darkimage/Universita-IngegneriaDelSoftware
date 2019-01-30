<html>
    <head> 
        <title>ShopOnline-Product List</title>
        <meta name="layout" content="main" />
        <asset:javascript src="jquery-3.3.1.min.js"/>
    </head>
    <body>
        <g:set var="current_cat" value="${(params.cat!= null) ? params.cat : 1}"/>
        <g:set var="maxPerPage" value="${(params.max!= null) ? params.max : 5}"/>
        
        <g:form controller="${controllerName}" action="${actionName}" method="GET">
        <div class="d-flex justify-content-end m-2 align-items-center" id="product_list_options">
            <div class="p-2 flex-nowrap">
                <label ><g:message code="com.lucafaggion.Product.SelectCategory"/>:</label>
                <g:select name="cat" from="${categories}" value="${current_cat}" optionKey="id" optionValue="name" default = "1"/>
            </div>
            <div class="p-2 flex-nowrap">
                <label ><g:message code="com.lucafaggion.Product.maxPerPage"/>:</label>
                <g:select name="max" from="${[5,10,50,100]}" value="${maxPerPage}" /></div>
            <div class="p-2 pr-3 flex-nowrap">
                <input type="submit" value="${message(code:'com.lucafaggion.Product.Submit')}" class="btn btn-primary"/>
            </div>
        </div>
        </g:form>

        <div id="list-Product" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="${[categories.get(current_cat.toInteger()-1).name]}" />
            </h1>
            <g:displayFlashMsg flash="${flash}"/>
           <f:table template="table_product" collection="${productList}" />

        <g:if test="${productCount > params.max.toInteger()}">
            <div class="pagination">
                <g:paginate controller="${controllerName}" action="${actionName}" total="${productCount}" params="${params}" />
            </div>
        </g:if>
        </div>
    </body>
</html>