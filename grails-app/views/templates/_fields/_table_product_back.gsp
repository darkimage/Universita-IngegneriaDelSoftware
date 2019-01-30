<div class="product_list_padding pt-1" id="responsible-table">
<table class="col-sm-12 table-striped table-condensed cf">
    <thead class="cf">
         <tr>
            <g:each in="${columnProperties}" var="p" status="i">
                <g:sortableColumn property="${p.property}" title="${message(code:'com.lucafaggion.Product.Fields.'+ p.label.toLowerCase())}" params="${params}" class="${(i==0) ? 'header' : ((columnProperties.size-1==i) ? 'lastheader' : '')} p-2" />
            </g:each> 
        </tr>
    </thead>
    <tbody>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <g:each in="${columnProperties}" var="p" status="j">
                    <g:if test="${j==0}">
                        <td data-title="${message(code:'com.lucafaggion.Product.Fields.'+ p.label.toLowerCase())} " class="first"><g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${p.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link></td>
                    </g:if>
                    <g:else>
                        <g:if test="${p.property!='price'}">
                            <td data-title="${message(code:'com.lucafaggion.Product.Fields.'+ p.label.toLowerCase())} "><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
                        </g:if>
                        <g:else>
                            <td data-title="${message(code:'com.lucafaggion.Product.Fields.'+ p.label.toLowerCase())} "><g:formatPrice value="${bean[p.property]}"/></td>
                        </g:else>
                    </g:else>
                </g:each>
            </tr>
        </g:each>
    </tbody>
</table>
</div>