<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="com.lucafaggion.denied" /></title>
    </head>
    <body>
        <div class="nav shop-nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="denied" class="content scaffold-list" role="main">
            <div id="info_message">
                <div class="alert alert-danger" role="alert"><g:message code="springSecurity.denied.message"/></div>
            </div>
        </div>
    </body>
</html>