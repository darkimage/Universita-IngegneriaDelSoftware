<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.ProductCategory.DomainName', default: 'Category')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.ProductCategory.DomainNamePlural', default: 'Categories')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-productCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav shop-nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="home" controller="ControlPanel" action="index"><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-productCategory" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <g:displayErrors domain="${this.productCategory}"/>
            <g:form resource="${this.productCategory}" method="PUT">
                <g:hiddenField name="version" value="${this.productCategory?.version + 1}" />
                <g:renderForm instance="${this.productCategory}" type="edit" template="/templates/renderCategoryForm"/>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
