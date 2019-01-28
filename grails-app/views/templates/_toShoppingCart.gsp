<sec:ifLoggedIn>
    <g:form controller="${controller}" action="${action}" method="POST">
        <div class="input-group mb-3 product_addtocart_quantity">
          <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1"><g:message code="com.lucafaggion.Product.addtocart.quantity"/>:</span>
            </div>
           <input type='number' name='quantity' id='quantity' min='1' max="${max}" value="1"/>
        </div>
         <input type="hidden" name='id' id='id' value="${id}">
        
        <div class="product_addtocart">
        <input type="submit" name='addtocart' id='addtocart' class="btn btn-primary " value="${message(code:'com.lucafaggion.Product.addtocart')}"></div>


    </g:form>
</sec:ifLoggedIn>
<!-- class="product_addtocart"-->