<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.User.DomainName', default: 'User')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.User.DomainNamePlural', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="home" controller="ControlPanel" action="index"><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <g:form controller="${controllerName}" action="${actionName}" method="GET">
        <g:set var="maxPerPage" value="${(params.max!= null) ? params.max : 5}"/>
        <div class="d-flex justify-content-end m-2 align-items-center" id="list_options">
            <div class="p-2 flex-nowrap">
                <label ><g:message code="com.lucafaggion.User.maxperPage"/>:</label>
                <g:select name="max" from="${[5,10,50,100]}" value="${maxPerPage}" />
            </div>
            <div class="p-2 pr-3 flex-nowrap">
                <input type="submit" value="${message(code:'com.lucafaggion.Submit')}" class="btn btn-primary"/>
            </div>
        </div>
        </g:form>
        <div id="list-user" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <f:table collection="${userList}" code="com.lucafaggion.User.Fields." properties="username,name,surname,email,fiscalCode"/>
            <g:displayPagination count="${userCount}"/>
        </div>
    </body>
</html>