<table>
    <thead>
         <tr>
            <%-- ${columnProperties} ${collection} --%>
            <g:each in="${columnProperties}" var="p" status="i">
                <g:sortableColumn property="${p.property}" title="${message(code:'com.lucafaggion.LineItem.Fields.'+ p.label.toLowerCase().replace(" ",''))}" params="${params}" />
            </g:each> 
        </tr>
    </thead>
    <tbody>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <g:each in="${columnProperties}" var="p" status="j">
                    <g:if test="${p.property=='quantity'}">
                        <td>
                        <g:form controller="${params.controller}" action="update" method="POST" 
                        style="display:inline;margin-left:10px">
                                <input type='number' name='value' id='value' min='1' value="${bean[p.property]}"/>
                                <input type='hidden' name='id' id='id' value="${bean['id']}"/>
                                <%-- <input type="submit" name="update" value="${message(code:'com.lucafaggion.ShoppingCart.updatequantity')}"/> --%>
                                <g:submitButton name="update" value="${message(code:'com.lucafaggion.ShoppingCart.updatequantity')}"/>
                        </g:form>
                        </td>
                    </g:if>
                    <g:else>
                        <g:if test="${p.property=='price'}">
                            <td><g:formatPrice value="${bean[p.property]}"/></td>
                        </g:if>
                        <g:else>
                            <td><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
                        </g:else>
                    </g:else>
                </g:each>
            </tr>
        </g:each>
    </tbody>
</table>