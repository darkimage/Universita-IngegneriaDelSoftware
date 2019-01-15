<table>
    <%-- <thead>
         <tr>
            <%-- <g:each in="${domainProperties}" var="p" status="i">
                <g:sortableColumn property="${p.property}" title="${p.label}" params="${params}" />
            </g:each>
        </tr>
    </thead> --%>
    <tbody>
        <%-- domainProperties --%>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td style="-webkit-box-shadow: 0 5px 5px -5px #AAAAAA;
    box-shadow: 0 5px 5px -5px #AAAAAA;padding-top:20px;padding-bottom:20px">
                <g:each in="${columnProperties}" var="p" status="j">
                    <g:if test="${p.name=="photo"}">
                        <div style="float:left;padding-left:50px">
                            <div style="overflow:hidden;border-radius: 20px">
                                <g:if test="${bean[p.property].cloudFile != null}">
                                <img src="${bean[p.property].url('medium')}">
                                </g:if>
                                <g:else>
                                    test
                                </g:else>
                            </div>
                        </div>
                    </g:if>
                </g:each>
                <g:each in="${columnProperties}" var="o" status="k">
                    <g:if test="${k==0}">
                        <div style="float:left;padding-left:30px">
                            <div style="font-weight:600;padding-bottom:20px;font-size: 17px;">
                                <g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${o.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link>
                            </div>
                            <g:each in="${columnProperties}" var="b" status="l">
                                <g:if test="${l>=1 && b.name != "photo"}">
                                <div>
                                    <span style="font-weight: bold;"><g:message code="com.lucafaggion.Product.Fields.${b.property}"/>:</span>
                                    <g:if test="${b.property == 'price'}">
                                        <g:formatPrice value="${bean[b.property]}"/>
                                    </g:if>
                                    <g:else>
                                        <f:display bean="${bean}" property="${b.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/>
                                    </g:else>
                                </div>
                                </g:if>
                            </g:each>
                        <div>
                    </g:if>
                </g:each>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>