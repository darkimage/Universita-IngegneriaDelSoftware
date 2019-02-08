<div class="container-fluid py-3 row-no-padding">
    <div class="row row-no-padding justify-content-center">
        <div class="col-md">
            <ul class="list-group">
                <li class="list-group-item active text-center"><g:message code="com.lucafaggion.User.Headers.userinfo"/></li>
                <g:displayRow code="com.lucafaggion.User.Fields.username"> ${value.user.username}</g:displayRow>
                <g:displayRow code="com.lucafaggion.User.Fields.name"> ${value.user.name}</g:displayRow>
                <g:displayRow code="com.lucafaggion.User.Fields.surname"> ${value.user.surname}</g:displayRow>
                <g:displayRow code="com.lucafaggion.User.Fields.email">${value.user.email} </g:displayRow>
                <g:displayRow code="com.lucafaggion.User.Fields.birthdate"> <g:newformatDate date="${value.user.birthDate}" format="dd-MM-yyyy"/></g:displayRow>
                <g:displayRow code="com.lucafaggion.User.Fields.fiscalcode"> ${value.user.fiscalCode}</g:displayRow>
            </ul>
        </div>
        <div class="col-md pt-2">
            <ul class="list-group">
                <li class="list-group-item active text-center"><g:message code="com.lucafaggion.User.Headers.addressinfo"/></li>
                <g:displayRow code="com.lucafaggion.User.Fields.address"> ${value.userShippingInfo.address},${value.userShippingInfo.number}</g:displayRow>
                <g:displayRow code="com.lucafaggion.User.Fields.state"> ${value.userShippingInfo.state}</g:displayRow>
                <g:displayRow bottom="true" code="com.lucafaggion.User.Fields.postalzone"> ${value.userShippingInfo.postalzone}</g:displayRow>
            </ul>
        </div>
        <div class="col-md pt-2">
            <ul class="list-group">
                <li class="list-group-item active text-center"><g:message code="com.lucafaggion.User.Headers.paymentinfo"/></li>
                <g:displayRow code="com.lucafaggion.User.Fields.creditcard"> ${value.userPaymentInfo.creditcard}-${value.userPaymentInfo.cvc}</g:displayRow>
                <g:displayRow code="com.lucafaggion.User.Fields.circuit"> ${value.userPaymentInfo.circuit}</g:displayRow>
                <g:displayRow bottom="true" code="com.lucafaggion.User.Fields.expirationDate"><g:newformatDate date="${value.userPaymentInfo.expirationDate}" format="dd-MM-yyyy"/></g:displayRow>
            </ul>
        </div>
    </div>
</div>
<h1><g:message code="com.lucafaggion.Orders.DomainNamePlural"/></h1>
<g:if test="${value.userOrders.size() > 0}">
    <div class="px-md-5 pt-2"><f:table collection="${value.userOrders}" template="table_orders_user"  code="com.lucafaggion.Orders." except="['user','lineItem']"/></div>
</g:if>
<g:else>
    <div id="info_message">
        <div class="alert alert-info" role="alert"><g:message code="com.lucafaggion.User.Profile.ordersempty"/></div>
    </div>
</g:else>