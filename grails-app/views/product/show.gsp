<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="${[message(code:'com.lucafaggion.Product.DomainNamePlural')]}" /></g:link></li>
                <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_DIPENDENTE'>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="${[message(code:'com.lucafaggion.Product.DomainName')]}" /></g:link></li>
                </sec:ifAnyGranted>
            </ul>
        </div>
        <div id="show-product" class="content scaffold-show" role="main">
            <h1 class="product_show_path"><g:productCategoryPath productid="${this.product.id}" separator="&gt;" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <div class="product_show_title product_show_title_top" style="padding: 0 2em 2em 2em;">${this.product.name}<hr></div>
            <div class="container-fluid py-3">
                <!-- IMAGES -->
                <div class="row product_show_nopadding">
                    <div class="col-sm-4 product_show_image_container product_show_nopadding">                      <div class="product_image_container">
                        <div class="product_show_image">
                            <g:if test="${this.product.photo.cloudFile != null}">
                                <img class="product_image_large" src="${this.product.photo.url('large')}">
                            </g:if>
                            <g:if test="${this.product.photo.cloudFile != null}">
                                <img class="product_image_medium" src="${this.product.photo.url('medium')}">
                            </g:if>
                        </div>
                        </div>
                    </div>
                    <!-- DETAILS -->
                    <div class="col-sm product_show_nopadding"> 
                        <div class="row product_show_nopadding">
                            <div class="col-sm-8 product_show_nopadding">
                                <div class="row product_show_nopadding">
                                    <div class="col-sm product_show_title">${this.product.name}<hr></div>
                                </div>
                                <div class="row product_show_nopadding product_show_first_field">
                                     <div class="col-sm"><span style="white-space: pre-wrap;"><b><g:message code="com.lucafaggion.Product.Fields.description"/>:</b> ${this.product.description}</span></div>
                                </div>
                                <div class="row product_show_nopadding" style="padding-top:3em">
                                    <div class="col-sm"><span style="white-space:nowrap"><b><g:message code="com.lucafaggion.Product.Fields.identifier"/>:</b> ${this.product.identifier}</span></div>
                                </div>
                                <div class="row product_show_nopadding">
                                    <div class="col-sm"><span style="white-space:nowrap"><b><g:message code="com.lucafaggion.Product.Fields.creation_date"/>:</b> <g:newformatDate format="d-MM-yyyy" date="${this.product.creation_date}"/></span> </div>
                                </div>
                            </div>
                            <!-- RIGHT COLUMN -->
                            <div class="col-sm">
                                <div class="row product_show_right product_show_nopadding">
                                    <div class="col-sm product_show_nopadding">
                                        <g:showProductStatus product="${this.product}"/>
                                        <div class="py-2 text-nowrap">
                                            <g:message code="com.lucafaggion.Product.Fields.price"/>:<span class="text-primary font-weight-bold"><g:formatPrice value="${this.product.price}"/></span>
                                        </div>
                                        <g:toShoppingCart controller="ShoppingCart" action="add" product="${this.product}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <sec:access expression="hasAnyRole('ROLE_DIPENDENTE','ROLE_ADMIN')">
                <g:form resource="${this.product}" method="DELETE">
                    <fieldset class="buttons">
                        <g:link class="edit" action="edit" resource="${this.product}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <sec:access expression="hasRole('ROLE_ADMIN')">
                            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </sec:access>
                    </fieldset>
                </g:form>
            </sec:access>
            </div>
        </div>
    </body>
</html>
