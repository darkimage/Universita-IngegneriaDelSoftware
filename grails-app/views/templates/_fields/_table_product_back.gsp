<table>
    <thead>
         <tr>
            <g:each in="${domainProperties}" var="p" status="i">
                <g:sortableColumn property="${p.property}" title="${message(code:'com.lucafaggion.Product.Fields.'+ p.label.toLowerCase())}" params="${params}" />
            </g:each> 
        </tr>
    </thead>
    <tbody>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <g:each in="${domainProperties}" var="p" status="j">
                    <g:if test="${j==0}">
                        <td><g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${p.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link></td>
                    </g:if>
                    <g:else>
                        <g:if test="${p.property!='price'}">
                            <td><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
                        </g:if>
                        <g:else>
                            <td><g:formatPrice value="${bean[p.property]}"/></td>
                        </g:else>
                    </g:else>
                </g:each>
            </tr>
        </g:each>
    </tbody>
</table>