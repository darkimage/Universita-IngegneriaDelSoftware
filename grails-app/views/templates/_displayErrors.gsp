<g:hasErrors bean="${domain}">
<ul class="alert alert-danger mx-3 px-5" role="alert">
    <g:eachError bean="${domain}" var="error">
    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
    </g:eachError>
</ul>
</g:hasErrors>