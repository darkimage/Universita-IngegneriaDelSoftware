<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.Product.DomainName', default: 'Product')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.Product.DomainNamePlural', default: 'Product')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
            </ul>
        </div>
        <div id="create-product" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <g:displayErrors domain="${this.product}"/>
            <g:uploadForm resource="${this.product}" method="POST">
                <g:renderForm instance="${this.product}" type="create" template="/templates/renderProductForm"/>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
