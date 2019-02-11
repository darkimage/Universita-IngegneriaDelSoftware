<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.ProductCategory.DomainName', default: 'Category')}" />
        <g:set var="entityNamePlural" value="${message(code: 'com.lucafaggion.ProductCategory.DomainNamePlural', default: 'Categories')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-productCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav shop-nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="home" controller="ControlPanel" action="index"><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-productCategory" class="content scaffold-list" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <div class="container-fluid py-3 row-no-padding">
                <div class="row row-no-padding justify-content-center">
                    <div class="col-md">
                        <ul class="list-group">
                            <li class="list-group-item active text-center"><g:message code="com.lucafaggion.ProductCategory.Headers.info"/></li>
                            <g:displayRow code="com.lucafaggion.ProductCategory.Fields.id"> ${this.productCategory.id}</g:displayRow>
                            <g:displayRow code="com.lucafaggion.ProductCategory.Fields.name"> ${this.productCategory.name}</g:displayRow>
                            <g:displayRow code="com.lucafaggion.ProductCategory.Fields.productCount"> ${this.productCount}</g:displayRow>
                        </ul>
                    </div>
                </div>
            </div>
            <h1><g:message code="com.lucafaggion.Product.DomainNamePlural"/></h1>
            <g:if test="${this.productsList.size()>0}">
                <g:form controller="${controllerName}" action="${actionName}" id="${this.productCategory.id}" method="GET">
                <div class="d-flex justify-content-end m-2 align-items-center" id="list_options">
                    <div class="p-2 flex-nowrap">
                        <label ><g:message code="com.lucafaggion.Product.maxPerPage"/>:</label>
                        <g:select name="max" from="${[5,10,50,100]}" value="${maxPerPage}" /></div>
                    <div class="p-2 pr-3 flex-nowrap">
                        <input type="submit" value="${message(code:'com.lucafaggion.Submit')}" class="btn btn-primary"/>
                    </div>
                </div>
                </g:form>
                <div class="container-fluid py-3 row-no-padding">
                    <div class="col-md">
                        <f:table collection="${this.productsList}" template="table_product_back" except="['photo','description','id']" />
                    </div>
                </div>
                <g:displayPagination count="${this.productCount}"/>
            </g:if>
            <g:else>
                <div id="info_message">
                    <div class="alert alert-info" role="alert"><g:message code="com.lucafaggion.Product.empty"/></div>
                </div>
            </g:else>
            <g:form resource="${this.productCategory}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.productCategory}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>