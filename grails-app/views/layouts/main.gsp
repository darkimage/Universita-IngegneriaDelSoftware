<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <g:layoutHead/>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top " role="navigation">
    <div class="shop-topbar align-middle"><a class="navbar-brand " href="${createLink(uri: '/')}">
        <asset:image class="d-none d-lg-block" width="250" height="50" src="logo.png" alt="ShopOnline Logo"/>
        <asset:image class="d-lg-none" width="75" height="75" src="logo_small.png" alt="ShopOnline Logo"/>
    </a></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">
             <li class="dropdown searchbar-li">
               <g:form controller="product" action="search" method="GET">
                <div class="searchbar">
                    <input class="search_input " type="text" name="value" placeholder="${message(code:'com.lucafaggion.Product.searchplaceholder')}"/>
                    <label for="submitSearch" class="search_icon rounded-circle"><i class="fas fa-search"></i></label>
                    <input type="submit" id="submitSearch" class="d-none"/>
                </div>
                </g:form> 
            </li>

            <sec:ifLoggedIn>
                <li class="dropdown">
                    <g:link class="dropdown-toggle simpleNavButton" controller='shoppingCart' action="index"><g:img dir="asset/images" file="shopping-cart.png" width="40" height="40"/><g:cartCount /></g:link>
                </li>
            </sec:ifLoggedIn>

            <sec:ifNotLoggedIn>
            <li class="dropdown"><g:link class="dropdown-toggle simpleNavButton" controller="login" action="auth" params="[referee:true]">Login</g:link>
            </li>
            <li class="dropdown">
                <g:link class="dropdown-toggle simpleNavButton" controller='user' action="create">Register</g:link>
            </li>
            </sec:ifNotLoggedIn>
 
            <sec:ifLoggedIn>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="padding-top: 25px;padding-bottom: 25px;" role="button" aria-haspopup="true" aria-expanded="false"><sec:loggedInUserInfo field='name'/></a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-item"><g:link controller='profile'><g:message code="com.lucafaggion.Profile.DomainName"/></g:link></li>
                        <sec:access  expression="hasAnyRole('ROLE_DIPENDENTE','ROLE_ADMIN')">
                            <li class="dropdown-item"><g:link controller='controlPanel'><g:message code="com.lucafaggion.ControlPanel.ControllerName"/></g:link></li>
                        </sec:access>
                        <li class="dropdown-item"><g:link controller='logout'>Logout</g:link></li>
                    </ul>
                </li>
            </sec:ifLoggedIn>
          <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><g:currentLocaleIcon withText="true" request="${request}"/><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-item"><g:localeLink locale="it" region="IT"><g:localeIcon withText="true" locale="it"/></g:localeLink></li>
                        <li class="dropdown-item"><g:localeLink locale="en" region="US"><g:localeIcon withText="true" locale="en"/></g:localeLink></li>
                    </ul>
            </li>

            <g:pageProperty name="page.nav"/>
        </ul>
    </div>

</nav>

<g:layoutBody/>

<div class="footer row" role="contentinfo">
    <div class="col">
        <a href="http://guides.grails.org" target="_blank">
            <asset:image src="advancedgrails.svg" alt="Grails Guides" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://guides.grails.org" target="_blank">Grails Guides</a></strong>
        <p>Building your first Grails app? Looking to add security, or create a Single-Page-App? Check out the <a href="http://guides.grails.org" target="_blank">Grails Guides</a> for step-by-step tutorials.</p>

    </div>
    <div class="col">
        <a href="http://docs.grails.org" target="_blank">
            <asset:image src="documentation.svg" alt="Grails Documentation" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://docs.grails.org" target="_blank">Documentation</a></strong>
        <p>Ready to dig in? You can find in-depth documentation for all the features of Grails in the <a href="http://docs.grails.org" target="_blank">User Guide</a>.</p>

    </div>

    <div class="col">
        <a href="https://grails-slack.cfapps.io" target="_blank">
            <asset:image src="slack.svg" alt="Grails Slack" class="float-left"/>
        </a>
        <strong class="centered"><a href="https://grails-slack.cfapps.io" target="_blank">Join the Community</a></strong>
        <p>Get feedback and share your experience with other Grails developers in the community <a href="https://grails-slack.cfapps.io" target="_blank">Slack channel</a>.</p>
    </div>
</div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
