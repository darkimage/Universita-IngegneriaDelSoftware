<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="com.lucafaggion.greeting"/></title>
</head>
<body>

<div id="index-view" class="content" role="main">
    <g:renderWithTemplate template="/templates/categoriesMenu" value="${categories.cat}"/>
    <g:featuredSlide collection="${featuredList}"/>
    <g:displayFlashMsg flash="${flash}"/>
    <h1><g:message code="com.lucafaggion.Product.newest"/></h1>
    <g:renderWithTemplate template="/templates/newestProducts" value="${[categories:categories.cat,newest:newestProducts]}"/>
</div>

</body>
</html>
