<!doctype html>
<html>
    <head>
        <title>Page Not Found</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <div id="info_message">
            <div class="alert alert-danger" role="alert"><g:message code="com.lucafaggon.error400"/></div>
        </div>
        <g:if env="development">
            <ul class="errors">
                <li>Error: Page Not Found (404)</li>
                <li>Path: ${request.forwardURI}</li>
            </ul>
        </g:if>
    </body>
</html>
