<table>
    <tbody>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td class="product_list_row">
                <div class="container-fluid">
                <div class="row">
                <g:each in="${columnProperties}" var="p" status="j">
                    <g:if test="${p.name=="photo"}">
                        <div class="col-sm-auto text-center">
                            <div class="product_list_image">
                                <g:if test="${bean[p.property].cloudFile != null}">
                                    <img src="${bean[p.property].url('medium')}">
                                </g:if>
                            </div>
                        </div>
                    </g:if>

                </g:each>
                <g:each in="${columnProperties}" var="o" status="k">
                    <g:if test="${o.name=="name"}">
                    <div class="col-sm-5 pt-3">
                        <div class="product_name_link">
                            <g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${o.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link>
                        </div>
                    </g:if>

                        <g:if test="${o.property != 'name' && o.property != 'photo'}">
                            <div> 
                                <span class="d-inline-block" style="font-weight: bold;"><g:message code="com.lucafaggion.Product.Fields.${o.property}"/>:</span>
                                <g:if test="${o.property == 'price'}">
                                    <g:formatPrice value="${bean[o.property]}"/>
                                </g:if>
                                <g:if test="${o.property == 'description'}">
                                    <span class="d-inline-block text-truncate" style="max-width: 150px;">
                                        <f:display bean="${bean}" property="${o.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/>
                                    </span>
                                </g:if>
                                <g:if test="${o.property != 'description' && o.property != 'price' }">
                                    <f:display bean="${bean}" property="${o.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}" />
                                </g:if>

                            </div>
                        </g:if>
                        <g:if test="${k == columnProperties.size()-1}">
                            </div>
                        </g:if>
                </g:each>
                
                <div class="col-lg-auto pt-3"> 
                    <g:showProductStatus product="${bean}"/>
                    <g:toShoppingCart controller="ShoppingCart" action="add" product="${bean}"/>
                </div>
                
                </div>
                </div>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>