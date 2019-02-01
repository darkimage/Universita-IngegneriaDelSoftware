<g:if test="${flash.message}">
    <div class="alert alert-info alert-dismissible fade show mx-4" role="alert">
    ${flash.message}
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
    </div>
</g:if>

<g:if test="${flash.error}">
    <div class="alert alert-danger alert-dismissible fade show mx-4" role="alert">
    ${flash.error}
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
    </div>
</g:if>