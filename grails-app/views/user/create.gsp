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
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
            </ul>
        </div>
        <div id="create-user" class="content scaffold-create" role="main">
            <h1><g:message code="com.lucafaggion.User.registerlabel" args="[entityName]" /></h1>
            <g:displayFlashMsg flash="${flash}"/>
            <g:displayErrors domain="${this.user}"/>
            <g:form resource="${this.user}" method="POST">
                <div class="container-fluid">
                    <div class="col-md-8">
                        <div class="row">
                            <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="name-input" code='com.lucafaggion.User.Fields.name'>
                                <input type="text" class="form-control" name="name" id="name" required/>
                            </g:formInput>
                        </div>
                        <div class="row">
                            <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="surname-input" code='com.lucafaggion.User.Fields.surname'>
                                <input type="text" class="form-control" name="surname" id="surname" required/>
                            </g:formInput>
                        </div>
                        <div class="row">
                            <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="birthDate-input" code='com.lucafaggion.User.Fields.birthdate'>
                                <g:datePicker name="birthDate" precision="day" default="none" noSelection="['': '']" />
                            </g:formInput>
                        </div>
                        <div class="row">
                            <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="fiscalCode-input" code='com.lucafaggion.User.Fields.fiscalCode'>
                                <input type="text" pattern=".{16,16}" class="form-control" name="fiscalCode" id="fiscalCode" required/>
                            </g:formInput>
                        </div>
                        <div class="row">
                            <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="email-input" code='com.lucafaggion.User.Fields.email'>
                                <input type="email" class="form-control" name="email" id="email" required placeholder="example@example.it"/>
                            </g:formInput>
                        </div>

                        <div class="row" id="form_column_half">
                            <div class="col-md-auto w-100" id="nopadding">
                                <div class="form_set"><g:message code="com.lucafaggion.User.Form.login"/></div>
                                <div class="row pt-2" id="nopadding">
                                    <div class="col-sm" id="nopadding">
                                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="username-input" code='com.lucafaggion.User.Fields.username'>
                                        <input type="text" maxlenght="24" class="form-control" name="username" id="username" required/>
                                    </g:formInput>
                                    </div>
                                    <div class="col-sm" id="nopadding">
                                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="password-input" code='com.lucafaggion.User.Fields.password'>
                                            <input type="password" pattern=".{6,}" class="form-control" name="password" id="password" required/>
                                        </g:formInput>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" id="form_column_half">
                            <div class="col-md-auto w-100" id="nopadding">
                                <div class="form_set"><g:message code="com.lucafaggion.User.Form.address"/></div>
                                <div class="row pt-2" id="nopadding">
                                    <div class="col-sm" id="nopadding">
                                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="address-input" code='com.lucafaggion.User.Fields.address'>
                                        <input type="text" class="form-control" name="address" id="address" required/>
                                    </g:formInput>
                                    </div>
                                    <div class="col-sm" id="nopadding">
                                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="number-input" code='com.lucafaggion.User.Fields.number'>
                                            <input type="number" class="form-control" name="number" id="number" required/>
                                        </g:formInput>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row" id="form_column_half">
                            <div class="col-md-auto w-100" id="nopadding">
                                <div class="row pt-2" id="nopadding">
                                    <div class="col-sm" id="nopadding">
                                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="state-input" code='com.lucafaggion.User.Fields.state'>
                                            <input type="text" class="form-control" name="state" id="state" required/>
                                        </g:formInput>
                                    </div>
                                    <div class="col-sm" id="nopadding">
                                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="postalzone-input" code='com.lucafaggion.User.Fields.postalzone'>
                                            <input type="number" class="form-control" name="postalzone" id="postalzone" required/>
                                        </g:formInput>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row" id="form_column_half">
                            <div class="col-md-auto w-100" id="nopadding">
                                <div class="form_set"><g:message code="com.lucafaggion.User.Form.paymentinfo"/></div>
                                <div class="row pt-2" id="nopadding">
                                    <div class="col-sm" id="nopadding">
                                        <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="creditcard-input" code='com.lucafaggion.User.Fields.creditcard'>
                                            <input type="text" class="form-control" name="creditcard" id="creditcard" pattern=".{16,}" onkeyup="creditCardFormat('#creditcard')"  required placeholder="4433 9330 1555 6781"/>
                                        </g:formInput>
                                    </div>
                                    <div class="col-sm" id="nopadding">
                                        <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="cvc-input" code='com.lucafaggion.User.Fields.cvc'>
                                        <input type="cvc" class="form-control" name="cvc" id="cvc" required/>
                                    </g:formInput>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="circuit-input" code='com.lucafaggion.User.Fields.circuit'>
                                <select name="circuit" id="circuit" class="form-control" >
                                    <option value="mastercard">MasterCard</option>
                                    <option value="visa">Visa</option>
                                    <option value="americanexpress">American Express</option>
                                </select>
                            </g:formInput>
                        </div>
                        <div class="row">
                            <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="true" id="expirationDate-input" code='com.lucafaggion.User.Fields.expirationDate'>
                                <g:datePicker name="expirationDate" precision="day" default="none" noSelection="['': '']" />
                            </g:formInput>
                        </div>          
                </div>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
