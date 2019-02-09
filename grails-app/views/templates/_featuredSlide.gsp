<g:if test="${ (collection) ? collection.size() > 0 : collection }">
<div id="itemsindicator" class="carousel slide featured_slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <g:each var="product" in="${collection}" status="i">
      	<g:if test="${ i == 0 }">
        	<li data-target="#itemsindicator" data-slide-to="${i}" class="active"></li>
		</g:if>
		<g:else>
        	<li data-target="#itemsindicator" data-slide-to="${i}"></li>
      	</g:else>
    </g:each>
  </ol>
  <div class="carousel-inner">
    <g:each var="product" in="${collection}" status="i">
		<div class="carousel-item ${(i==0) ? 'active' : ''} overflow-hidden" style="height:450px;">
			<g:if test="${product.photo.cloudFile != null}">
				<g:link controller="product" action="show" id="${product.id}"><img class="d-block image_slide" src="${product.photo.url('original')}"></g:link>
			</g:if>
			<div class="featured-caption d-md-block">
				<h2 class="text-truncate">${product.name}</h2>
				<p id="description" class="d-none d-md-block">${product.description}</p>
				<p id="price"><g:formatPrice value="${product.price}"/></p>
				<div id="goto" class="d-none d-md-block ">
					<g:form controller="product" action="show" resource="${product}"> 
						<input type="submit" class="btn btn-primary" id="gotoProduct" value="${message(code:'com.lucafaggion.Product.goto')}">
					</g:form>
				</div>
			</div>
		</div>
    </g:each>
  </div>
  <a class="carousel-control-prev" href="#itemsindicator" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#itemsindicator" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
   
</g:if>