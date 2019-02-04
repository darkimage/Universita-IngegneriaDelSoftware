<div class="container-fluid">
    <div class="col-md-8">
        <div class="row">
            <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="name-input" code='com.lucafaggion.User.Fields.name'>
                <input type="text" class="form-control" value="${setValueRequired(value:domain_instance.user,fieldproperty:'name',defaultval:'')}" name="name" id="name" <g:isInputRequired type="${inputtype}" required="true" />/>
            </g:formInput>
        </div>
        <div class="row">
            <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="surname-input" code='com.lucafaggion.User.Fields.surname'>
                <input type="text" class="form-control" value="${setValueRequired(value:domain_instance.user,fieldproperty:'surname',defaultval:'')}" name="surname" id="surname" <g:isInputRequired type="${inputtype}" required="true" />/>
            </g:formInput>
        </div>
        <div class="row">
            <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="birthDate-input" code='com.lucafaggion.User.Fields.birthdate'>
                <g:formDateInput id="birthDate" class="form-control" value="${formatDateforInput(value:setValueRequired(value:domain_instance.user,fieldproperty:'birthDate',defaultval:''))}" <g:isInputRequired type="${inputtype}" required="true" />
            </g:formInput>
        </div>
        <div class="row">
            <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="fiscalCode-input" code='com.lucafaggion.User.Fields.fiscalCode'>
                <input type="text" pattern=".{16,16}" class="form-control" value="${setValueRequired(value:domain_instance.user,fieldproperty:'fiscalCode',defaultval:'')}" name="fiscalCode" id="fiscalCode" <g:isInputRequired type="${inputtype}" required="true" />/>
            </g:formInput>
        </div>
        <div class="row">
            <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="email-input" code='com.lucafaggion.User.Fields.email'>
                <input type="email" class="form-control" name="email" id="email" value="${setValueRequired(value:domain_instance.user,fieldproperty:'email',defaultval:'')}" <g:isInputRequired type="${inputtype}" required="true" /> placeholder="example@example.it"/>
            </g:formInput>
        </div>

        <div class="row" id="form_column_half">
            <div class="col-md-auto w-100" id="nopadding">
                <div class="form_set"><g:message code="com.lucafaggion.User.Form.login"/></div>
                <div class="row pt-2" id="nopadding">
                    <div class="col-sm" id="nopadding">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="username-input" code='com.lucafaggion.User.Fields.username'>
                        <input type="text" value="${setValueRequired(value:domain_instance.user,fieldproperty:'username',defaultval:'')}" maxlenght="24" class="form-control" name="username" id="username" <g:isInputRequired type="${inputtype}" required="true" />/>
                    </g:formInput>
                    </div>
                    <div class="col-sm" id="nopadding">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="password-input" code='com.lucafaggion.User.Fields.password'>
                            <input type="password" pattern=".{6,}" class="form-control" name="password" id="password" <g:isInputRequired type="${inputtype}" required="true" />/>
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
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="address-input" code='com.lucafaggion.User.Fields.address'>
                        <input type="text" class="form-control" name="address" value="${setValueRequired(value:domain_instance.userShippingInfo,fieldproperty:'address',defaultval:'')}" id="address" <g:isInputRequired type="${inputtype}" required="true" />/>
                    </g:formInput>
                    </div>
                    <div class="col-sm" id="nopadding">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="number-input" code='com.lucafaggion.User.Fields.number'>
                            <input type="number" class="form-control" name="number" value="${setValueRequired(value:domain_instance.userShippingInfo,fieldproperty:'number',defaultval:'')}" id="number" <g:isInputRequired type="${inputtype}" required="true" />/>
                        </g:formInput>
                    </div>
                </div>
            </div>
        </div>


        <div class="row" id="form_column_half">
            <div class="col-md-auto w-100" id="nopadding">
                <div class="row pt-2" id="nopadding">
                    <div class="col-sm" id="nopadding">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="state-input" code='com.lucafaggion.User.Fields.state'>
                            <input type="text" class="form-control" value="${setValueRequired(value:domain_instance.userShippingInfo,fieldproperty:'state',defaultval:'')}" name="state" id="state" <g:isInputRequired type="${inputtype}" required="true" />/>
                        </g:formInput>
                    </div>
                    <div class="col-sm" id="nopadding">
                        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="postalzone-input" code='com.lucafaggion.User.Fields.postalzone'>
                            <input type="number" class="form-control" value="${setValueRequired(value:domain_instance.userShippingInfo,fieldproperty:'postalzone',defaultval:'')}" name="postalzone" id="postalzone" <g:isInputRequired type="${inputtype}" required="true" />/>
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
                        <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="creditcard-input" code='com.lucafaggion.User.Fields.creditcard'>
                            <input type="text" value="${setValueRequired(value:domain_instance.userPaymentInfo,fieldproperty:'creditcard',defaultval:'')}" class="form-control" name="creditcard" id="creditcard" pattern=".{16,}" onkeyup="creditCardFormat('#creditcard')"   placeholder="4433 9330 1555 6781" <g:isInputRequired type="${inputtype}" required="true"/>/>
                        </g:formInput>
                    </div>
                    <div class="col-sm" id="nopadding">
                        <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="cvc-input" code='com.lucafaggion.User.Fields.cvc'>
                        <input type="number" value="${setValueRequired(value:domain_instance.userPaymentInfo,fieldproperty:'cvc',defaultval:'')}" class="form-control" name="cvc" id="cvc" <g:isInputRequired type="${inputtype}" required="true" />/>
                    </g:formInput>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="circuit-input" code='com.lucafaggion.User.Fields.circuit'>
                <select name="circuit" id="circuit" value="${setValueRequired(value:domain_instance.userPaymentInfo,fieldproperty:'circuit',defaultval:'')}" class="form-control" <g:isInputRequired type="${inputtype}" required="true" />>
                    <option value="mastercard">MasterCard</option>
                    <option value="visa">Visa</option>
                    <option value="americanexpress">American Express</option>
                </select>
            </g:formInput>
        </div>
        <div class="row">
            <g:formInput class="mb-3 flex-nowrap no-spinners" prependclass="form_input_prepend" spanclass="ml-auto" required="${isInputRequired(type:inputtype,required:true).toString()}" id="expirationDate-input" code='com.lucafaggion.User.Fields.expirationDate'>
                <g:formDateInput id="expirationDate" class="form-control"  value="${formatDateforInput(value:setValueRequired(value:domain_instance.userPaymentInfo,fieldproperty:'expirationDate',defaultval:''))}" <g:isInputRequired type="${inputtype}" required="true" />
            </g:formInput>
        </div>
    </div>
</div>