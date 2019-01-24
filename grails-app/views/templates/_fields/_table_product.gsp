<table>
    <tbody>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td class="product_list_row">
                <g:each in="${columnProperties}" var="p" status="j">
                    <g:if test="${p.name=="photo"}">
                        <div class="product_list_container" style="padding-left:50px">
                            <div style="overflow:hidden;border-radius: 20px">
                                <g:if test="${bean[p.property].cloudFile != null}">
                                    <img src="${bean[p.property].url('medium')}">
                                </g:if>
                            </div>
                        </div>
                    </g:if>

                </g:each>
                
                <g:each in="${columnProperties}" var="o" status="k">
                    <g:if test="${o.name=="name"}">
                            <div class="product_list_container">
                            <div class="product_name_link">
                                <g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${o.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link>
                            </div>
                        </g:if>

                        <g:if test="${o.property != 'name' && o.property != 'photo'}">
                            <div>
                                <span style="font-weight: bold;"><g:message code="com.lucafaggion.Product.Fields.${o.property}"/>:</span>
                                <g:if test="${o.property == 'price'}">
                                    <g:formatPrice value="${bean[o.property]}"/>
                                </g:if>
                                <g:else>
                                    <f:display bean="${bean}" property="${o.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/>
                                </g:else>
                            </div>
                        </g:if>
                        <g:if test="${k == columnProperties.size()-1}">
                            </div>
                        </g:if>
                </g:each>
                <div class="product_list_container">
                    
                </div>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>