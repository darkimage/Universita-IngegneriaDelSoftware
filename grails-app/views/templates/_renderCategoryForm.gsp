<div class="container-fluid">
    <div class="col-md-8">
    <div class="row ">
        <g:formInput class="mb-3 flex-nowrap" prependclass="form_input_prepend" spanclass="ml-auto" id="name-input" code='com.lucafaggion.ProductCategory.Fields.name' required="${isInputRequired(type:inputtype,required:true).toString()}">
            <input type="text" class="form-control" name="name" id="name" value="${setValueRequired(value:domain_instance,fieldproperty:'name',defaultval:'')}" required="${isInputRequired(type:inputtype,required:true)}">
        </g:formInput>
    </div>
    </div>
</div>