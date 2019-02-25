<g:form controller="${controller}" action="${action}" params="${params}" method="POST">
    <div class="input-group mb-3 products_addtocart_quantity">
        <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1"><g:message code="com.lucafaggion.Product.addtocart.quantity"/>:</span>
        </div>
        <input type='number' name='quantity' id='quantity' min='1' class="form-control" max="${max}" value="1"/>
    </div>
        <input type="hidden" name='id' id='id' value="${id}">
        <input type="hidden" name='lastController' id='lastController' value="${controllerName}">
        <input type="hidden" name='lastAction' id='lastAction' value="${actionName}">

    <div class="product_addtocart">
    <input type="submit" name='addtocart' id='addtocart' class="btn btn-primary " value="${message(code:'com.lucafaggion.Product.addtocart')}"></div>
</g:form>
<!-- class="product_addtocart"-->