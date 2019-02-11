<div class="container-fluid categories_menu">
    <div class="row justify-content-md-center">
    <g:each var="item" in="${value}" status="i">
        <g:if test="${i < 5}">
            <div class="col-auto py-2" id="categoryaction">
                <g:link class="text-truncate p-2 badge badge-primary" controller="product" action="list" params="${[cat:item.id]}">${item.name}</g:link>
            </div>
        </g:if>
    </g:each>
    <div class="col-2 py-2">
        <g:link class="text-truncate p-2 badge badge-primary" controller="product" action="list"><g:message code="com.lucafaggion.ProductCategory.browse"/></g:link>
    </div>
    </div>
</div>