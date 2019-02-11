<div class="container-fluid pb-2">
    <g:each var="category" in="${value.categories}" status='i'>
        <g:if test="${value.newest[i].size() > 0}">
            <div class="row row-no-padding">
                <h3>${category.name}</h3>
                <f:table template="table_product" collection="${value.newest[i]}" except="featured,id" />
            </div>
        </g:if>
    </g:each>
</div>