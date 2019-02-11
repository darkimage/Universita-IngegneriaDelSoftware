<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'com.lucafaggion.ControlPanel.ControllerName', default: 'User')}" />
        <title><g:message code="com.lucafaggion.ControlPanel.ControllerName" /></title>
    </head>
    <body>
        <a href="#control-panel" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav shop-nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="control-panel" class="content scaffold-create" role="main">
            <h1><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></h1>
            <div class="container-fluid pt-4 ">
                <div class="row">
                    <sec:access expression="hasRole('ROLE_ADMIN')">
                        <div class="col-sm pt-2 text-center">
                            <g:link action="manageUsers" class="controller_action p-2 badge badge-primary "><g:message code="com.lucafaggion.ControlPanel.manageusers"/></g:link>
                        </div>
                    </sec:access>
                    <div class="col-sm pt-2 text-center">
                        <g:link action="manageOrders" class="controller_action p-2 badge badge-primary "><g:message code="com.lucafaggion.ControlPanel.manageorders"/></g:link>
                    </div>
                    <div class="col-sm pt-2 text-center">
                        <g:link action="manageProducts" class="controller_action p-2 badge badge-primary "><g:message code="com.lucafaggion.ControlPanel.manageproducts"/></g:link>
                    </div>
                    <div class="col-sm pt-2 text-center">
                        <g:link action="manageCategories" class="controller_action p-2 badge badge-primary "><g:message code="com.lucafaggion.ControlPanel.managecategories"/></g:link>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
