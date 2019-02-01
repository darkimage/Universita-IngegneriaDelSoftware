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
                <!--flex-nowrap-->
                <div class="container-fluid">
                    <div class="col-md-8">
                    <div class="row ">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="name-input" code='com.lucafaggion.Product.Fields.name'>
                            <input type="text" class="form-control" name="name" id="name" required/>
                        </g:formInput>
                    </div>
                    <div class="row">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend"  spanclass="ml-auto" required="true" id="quantity-input" code='com.lucafaggion.Product.Fields.quantity'>
                            <input type="number" min="0" value="0" class="form-control" name="quantity" id="quantity" required/>
                        </g:formInput>
                    </div>

                    <div class="row ">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="description-input" code='com.lucafaggion.Product.Fields.category'>
                            <g:selectFormInput domain="com.lucafaggion.ProductCategory" property="name" name="category" id="category"/>
                        </g:formInput>
                    </div>

                    <div class="row ">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="description-input" code='com.lucafaggion.Product.Fields.description'>
                            <textarea rows="4" cols="50" name="description" id="Description" required="" placeholder="${message(code: 'com.lucafaggion.Product.Fields.description.placeholder')}" class="form-control"></textarea>
                        </g:formInput>
                    </div>

                    <div class="row ">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" append="${message(code:'com.lucafaggion.Product.Fields.currency')}" required="true" id="description-input" code="com.lucafaggion.Product.Fields.price">
                            <input type="number" placeholder="9.99" min="0" step="0.01" data-number-to-fixed="2"  data-number-stepfactor="100" class="form-control" name="price" required id="price" />
                        </g:formInput>
                    </div>

                    <div class="row ">
                        <g:formInput type="file" class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="description-input" code="com.lucafaggion.Product.Fields.imageinput">
                            <input type="file" name="photo" accept="image/*" id="photo" class="custom-file-input" onChange="displayFileName('#photo')"/>
                            <label for="photo" class="custom-file-label"><g:message code="com.lucafaggion.Product.Fields.imageinput"/></label>
                        </g:formInput>
                    </div>
                    </div>
                    <%-- <div class="col-sm-4">
                    </div>  --%>
                </div>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
