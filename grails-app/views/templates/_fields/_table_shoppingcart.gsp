<div id="responsible-table">
<table class="col-sm-12 table-striped table-condensed cf">
    <thead class="cf">
         <tr>
            <g:each in="${columnProperties}" var="p" status="i">
                <g:sortableColumn property="${p.property}" title="${message(code:headers + p.label.toLowerCase().replace(' ',''))}" params="${params}" class="${(i==0) ? 'header' : ((columnProperties.size-1==i) ? 'lastheader' : '')} p-2" />
            </g:each> 
        </tr>
    </thead>
    <tbody>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <g:each in="${columnProperties}" var="p" status="j">
                    <g:if test="${p.property=='quantity'}">
                        <td data-title="${message(code:headers + p.label.toLowerCase().replace(' ',''))} " class="align-middle">
                            <g:form controller="${params.controller}" action="update" method="POST" 
                            style="display:inline;margin-left:10px">
                                    <div class="input-group flex-nowrap">
                                        <input type='number' name='value' id='value' min='1' max="${bean['subProduct'].quantity}" value="${bean[p.property]}" class="form-control"/>
                                        <div class="input-group-append">
                                            <span class="input-group-text" id="basic-addon1">
                                                <input type='hidden' name='id' id='id' value="${bean['id']}"/>
                                                <g:formButtonImg dir="asset/images" image="update-icon.png" name="update" value="update"/>
                                                <g:formButtonImg dir="asset/images" image="delete-icon.png" name="delete" id="delete" value="delete"/>
                                            </span>
                                        </div>
                                    </div>
                            </g:form>
                        </td>
                    </g:if>
                    <g:else>
                        <g:if test="${p.property=='price'}">
                            <td data-title="${message(code:headers+ p.label.toLowerCase().replace(' ',''))}"><g:formatPrice value="${bean[p.property]}"/></td>
                        </g:if>
                        <g:else>
                            <td data-title="${message(code:headers + p.label.toLowerCase().replace(' ',''))}" class="first"><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
                        </g:else>
                    </g:else>
                </g:each>
            </tr>
        </g:each>
    </tbody>
</table>
</div>