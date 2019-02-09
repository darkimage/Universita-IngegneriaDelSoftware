<div class="container-fluid">
    <div class="col-md-8">
    <div class="row ">
        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" id="name-input" code='com.lucafaggion.Product.Fields.name' required="${isInputRequired(type:inputtype,required:true).toString()}">
            <input type="text" class="form-control" name="name" id="name" value="${setValueRequired(value:domain_instance,fieldproperty:'name',defaultval:'')}" required="${isInputRequired(type:inputtype,required:true)}">
        </g:formInput>
    </div>
    <div class="row">
        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend"  spanclass="ml-auto" id="quantity-input" code='com.lucafaggion.Product.Fields.quantity' required="${isInputRequired(type:inputtype,required:true).toString()}">
            <input type="number" min="0" class="form-control" name="quantity" value="${setValueRequired(value:domain_instance,fieldproperty:'quantity',defaultval:0)}" required="${isInputRequired(type:inputtype,required:true)}" id="quantity" />
        </g:formInput>
    </div>

    <div class="row ">
        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" id="description-input" code='com.lucafaggion.Product.Fields.category' required="${isInputRequired(type:inputtype,required:true).toString()}">
            <g:selectFormInput domain="com.lucafaggion.ProductCategory" property="name" name="category" id="category" instance="${(inputtype == "edit") ? domain_instance : null}" required="${isInputRequired(type:inputtype,required:true)}"/> 
        </g:formInput>
    </div>

    <div class="row ">
        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" id="description-input" code='com.lucafaggion.Product.Fields.description' required="${isInputRequired(type:inputtype,required:true).toString()}">
            <textarea rows="4" cols="50" name="description" id="Description" placeholder="${message(code: 'com.lucafaggion.Product.Fields.description.placeholder')}" class="form-control" required="${isInputRequired(type:inputtype,required:true)}">${setValueRequired(value:domain_instance,fieldproperty:'description',defaultval:'')}</textarea> 
        </g:formInput>
    </div>

   <div class="row ">
        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" append="${message(code:'com.lucafaggion.Product.Fields.currency')}" id="description-input" code="com.lucafaggion.Product.Fields.price" required="${isInputRequired(type:inputtype,required:true).toString()}">
            <input type="number" placeholder="9.99" min="0" step="0.01" data-number-to-fixed="2"  data-number-stepfactor="100" class="form-control" name="price" required="${isInputRequired(type:inputtype,required:true)}" id="price" value="${inputPrice(value:setValueRequired(value:domain_instance,fieldproperty:'price',defaultval:''))}" />
        </g:formInput>
    </div>

    <div class="row ">
        <g:formInput type="file" class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" id="description-input" code="com.lucafaggion.Product.Fields.imageinput" required="${isInputRequired(type:inputtype,required:true).toString()}">
            <input type="file" name="photo" accept="image/*" id="photo" class="custom-file-input" onChange="displayFileName('#photo')" />
            <label for="photo" class="custom-file-label"><g:message code="com.lucafaggion.Product.Fields.imageinput"/></label>
        </g:formInput>
    </div>

    <div class="row ">
        <div class="form_input_label my-3 flex-nowrap w-50">
            <g:message code="com.lucafaggion.Product.Fields.featured"/>:
            <label class="switch">
                <input type="checkbox" name="featured" class="success" value="${true}" id="featured">
                <span class="slider round"></span>
            </label>
            </div>
        </div>
    </div>
</div> 