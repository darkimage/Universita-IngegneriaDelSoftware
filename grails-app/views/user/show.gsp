<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.User.DomainName', default: 'User')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.User.DomainNamePlural', default: 'User')}" />
        <g:set var="header" value="bg-primary user_header_border text-light font-weight-bold text-nowrap px-2 w-100" />
        <g:set var="row" value="border-right border-left w-100 text-nowrap" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-user" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>

            <%-- <f:display bean="user" /> --%>
            <div class="container-fluid py-3 ">
                <div class="row justify-content-center">
                    <div class="col-md">
                        <div class="row ">
                            <div class="${header}"><g:message code="com.lucafaggion.User.Headers.userinfo"/></div>
                        </div>
                        <div class="row ">
                            <div class="${row} ">
                                <div class="row ">
                                    <div class="col-auto font-weight-bold">
                                        <g:message code="com.lucafaggion.User.Fields.name"/>:
                                    </div>
                                    <div class="col-auto">
                                        ${this.userData.user.name}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="row ">
                            <div class="${header}"><g:message code="com.lucafaggion.User.Headers.addressinfo"/></div>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="row ">
                            <div class="${header}"><g:message code="com.lucafaggion.User.Headers.paymentinfo"/></div>
                        </div>
                    </div>
                </div>
            </div>

            <g:form resource="${this.userData.user}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.userData.user}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
