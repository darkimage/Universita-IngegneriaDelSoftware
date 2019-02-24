<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.User.DomainName', default: 'User')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.User.DomainNamePlural', default: 'User')}" />
        <g:set var="header" value="bg-primary user_header_border text-light font-weight-bold text-nowrap px-2 w-100" />
        <title><g:message code="com.lucafaggion.User.Profile" args="${[this.userData.user.name]}" /></title>
    </head>
    <body>
        <a href="#user-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav shop-nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:access  expression="hasAnyRole('ROLE_DIPENDENTE','ROLE_ADMIN')">
                    <li><g:link class="home" controller="ControlPanel" action="index"><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
                </sec:access>
            </ul>
        </div>
        <div id="user-profile" class="content scaffold-create" role="main">
            <h1><g:message code="com.lucafaggion.User.Profile" args="${[this.userData.user.name]}"/></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <g:displayErrors domain="${this.userData.user}"/>
            <g:renderWithTemplate value="${this.userData}" template="/templates/showUser"/>
            <fieldset class="buttons">
                <g:link controller="profile" class="edit" action="edit" id="${this.userData.user.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            </fieldset>
        </div>
    </body>
</html>
