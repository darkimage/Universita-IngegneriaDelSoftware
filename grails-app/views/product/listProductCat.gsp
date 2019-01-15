<html>
    <head> 
        <title>ShopOnline-Product List</title>
        <meta name="layout" content="main" />
        <asset:javascript src="jquery-3.3.1.min.js"/>
    </head>
    <body>
        <g:set var="current_cat" value="${(params.cat!= null) ? params.cat : 1}"/>
        <g:set var="maxPerPage" value="${(params.max!= null) ? params.max : 5}"/>
        <%-- <div>${categories.asList()} ${productCount} ${params}</div> --%>
        <div style="padding-left:25px" class="pagination">
        <g:form controller="Product" action="test" method="GET">
            <div style="display: inline-block;">
                <label><g:message code="com.lucafaggion.Product.SelectCategory"/>:</label>
                <g:select name="cat" from="${categories}" value="${current_cat}" optionKey="id" optionValue="name" default = "1" />
            </div>
            <div style="display: inline-block;">
                <label style="margin-left:10px"><g:message code="com.lucafaggion.Product.maxPerPage"/>:</label> <!-- ${message(code:'my.localized.content')} -->
                <g:select name="max" from="${[5,10,50,100]}" value="${maxPerPage}" />
            </div>
            <input type="submit" value="${message(code:'com.lucafaggion.Product.Submit')}">
        </g:form>
        </div>

        <div id="list-Product" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="${[categories.get(current_cat.toInteger()-1).name]}" />
            </h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
           <f:table template="table_product" collection="${productList}" />

        <g:if test="${productCount > params.max.toInteger()}">
            <div class="pagination">
                <g:paginate controller="Product" action="test" total="${productCount}" params="${params}" />
            </div>
        </g:if>
        </div>
    </body>
</html>