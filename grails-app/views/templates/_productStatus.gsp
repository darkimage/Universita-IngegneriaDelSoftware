<g:if test="${available == true}">
    <div class="product_show_disponibility product_status_ok"><span style="white-space:nowrap"><asset:image src="ok-icon.png" width="12" height="12"/> <g:message code="com.lucafaggion.Product.statusok"/></span></div>
    <div class="product_status_ok_quantity"> ${product.quantity} <g:message code="com.lucafaggion.Product.left"/> </div>
</g:if>
<g:else>
    <div class="product_show_disponibility product_status_notok"><span style="white-space:nowrap"><asset:image src="notok-icon.png" width="12" height="12"/>  <g:message code="com.lucafaggion.Product.statusnotok"/></span></div>
</g:else>