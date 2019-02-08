<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.User.DomainName', default: 'User')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.User.DomainNamePlural', default: 'User')}" />
        <title><g:message code="com.lucafaggion.User.registerlabel" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:access expression="hasRole('ROLE_ADMIN')">
                    <li><g:link class="home" controller="ControlPanel" action="index"><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
                    <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
                </sec:access>
            </ul>
        </div>
        <div id="create-user" class="content scaffold-create" role="main">
            <h1><g:message code="com.lucafaggion.User.registerlabel" args="[entityName]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <g:displayErrors domain="${this.user}"/>
            <g:form resource="${this.user}" method="POST">
                <g:renderForm template="/templates/renderUserForm" instance="${this}" type="create"/>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
