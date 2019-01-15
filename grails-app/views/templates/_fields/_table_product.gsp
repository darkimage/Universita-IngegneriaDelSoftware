<table>
    <%-- <thead>
         <tr>
            <%-- <g:each in="${domainProperties}" var="p" status="i">
                <g:sortableColumn property="${p.property}" title="${p.label}" params="${params}" />
            </g:each>
        </tr>
    </thead> --%>
    <tbody>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>
                <g:each in="${domainProperties}" var="p" status="j">
                    <g:if test="${j==0}">
                        <div style="float:left;padding-left:50px">
                            <div style="width:200px;height:200px;text-align: center;vertical-align: middle;line-height:200px">Image</div>
                        </div>
                    </g:if>
                </g:each>
                <g:each in="${domainProperties}" var="o" status="k">
                    <g:if test="${k==0}">
                        <div style="float:left;padding-left:10px">
                            <div style="padding-bottom:20px">
                                <g:link method="GET" resource="${bean}"><f:display bean="${bean}" property="${o.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link>
                            </div>
                            <g:each in="${domainProperties}" var="b" status="l">
                                <g:if test="${l>=1}">
                                <div>
                                    <span style="font-weight: bold;">${b.name.substring(0,1).toUpperCase()+b.name.substring(1)}:</span>
                                    <f:display bean="${bean}" property="${b.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/>
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