<g:if test="${count > params.max.toInteger()}">
    <div class="pagination">
        <g:paginate controller="${controllerName}" action="${actionName}" total="${count}" params="${params}" />
    </div>
</g:if>