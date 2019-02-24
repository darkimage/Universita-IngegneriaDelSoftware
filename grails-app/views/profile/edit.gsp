<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.User.DomainName', default: 'User')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.User.DomainNamePlural', default: 'User')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav shop-nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:access  expression="hasAnyRole('ROLE_DIPENDENTE','ROLE_ADMIN')">
                    <li><g:link class="home" controller="ControlPanel" action="index"><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
                </sec:access>
            </ul>
        </div>
        <div id="edit-user" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <g:displayErrors domain="${this.userData.user}"/>
            <g:form controller="profile" action="update" id="${this.userData.user.id}" method="PUT">
                <g:hiddenField name="version" value="${this.userData.user?.version + 1}" />
                <g:renderForm template="/templates/renderUserForm" type="edit" instance="${this.userData}"/>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
