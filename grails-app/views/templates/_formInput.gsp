<div class="input-group ${css.container}">
  <div class="input-group-prepend ${css.prepend}">
    <span class="input-group-text ${css.span}" id="${id}">
    <g:if test="${required}">
        <span class="required-indicator">*</span>
    </g:if>
    <g:else>
        -
    </g:else>
    </span>
  </div>
    <g:if test="${type=='input'}">
        ${raw(body)}
    </g:if>
    <g:if test="${type=='file'}">
        <div class="custom-file">
            ${raw(body)}
        </div>
    </g:if>
  <g:if test="${append}">
    <div class="input-group-append">
        <span class="input-group-text">
            ${append}
        </span>
    </div>
  </g:if>

</div>