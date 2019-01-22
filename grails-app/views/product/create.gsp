<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <style>
            .input-group-addon {
                padding: 6px 12px;
                font-size: 14px;
                font-weight: 400;
                line-height: 1;
                color: #555;
                text-align: center;
                background-color: #eee;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
        </style>
    </head>
    <body>

        <a href="#create-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-product" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.product}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.product}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:uploadForm resource="${this.product}" method="POST">
                <fieldset class="form">
                    <f:all bean="product" except="['identifier','creation_date','photo','price','description']"/>
                    <div class="fieldcontain required">
                        <label for="Description"><g:message code="com.lucafaggion.Product.Fields.description"/>
                        <span class="required-indicator">*</span></label>
                        <textarea rows="4" cols="50" name="description" id="Description" required="" placeholder="${message(code: 'com.lucafaggion.Product.Fields.description.placeholder')}"></textarea>
                    </div>
                    <div class="fieldcontain required">
                        <label for="price"><g:message code="com.lucafaggion.Product.Fields.price"/>
                        <span class="required-indicator">*</span></label>
                        <input type="number" placeholder="9.99" min="0" step="0.01" data-number-to-fixed="2"  data-number-stepfactor="100" class="currency" name="price" required="" id="price" />
                        <span class="input-group-addon"><g:message code="com.lucafaggion.Product.Fields.currency"/></span>
                    </div>
                    <div class="fieldcontain">
                        <label for="photo"><g:message code="com.lucafaggion.Product.Fields.imageinput"/>
                        <span class="required-indicator">*</span></label>
                        <input type="file" name="photo" accept="image/*" />

                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
