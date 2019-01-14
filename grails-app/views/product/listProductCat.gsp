<html>
    <head> 
        <title>ShopOnline-Product List</title>
        <meta name="layout" content="main" />
        <asset:javascript src="jquery-3.3.1.min.js"/>
    </head>
    <body>
       
        <div id="test"></div>
        <div>${categories.name} ${productCount} ${params}</div>
        <button onclick="test()">Click me</button>
        <g:form controller="Product" action="test" method="GET">
            <label>Select Category: </label>
            <g:select name="cat" from="${categories}" value="${(params.cat!= null) ? params.cat : 1}" optionKey="id" optionValue="name" noSelection="['null':'-Choose your age-']"/>
            <input type="submit" value="Submit">
        </g:form>

        <div id="list-Product" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="${params.controllerName}" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table template="templates/_field/_table" collection="${productList}" />

        <g:if test="${productCount > params.max}">
            <div class="pagination">
                <g:paginate controller="Product" action="test" total="${productCount}" />
            </div>
        </g:if>
        </div>


        <script>
        document.getElementById("test").innerHTML = "Hello JavaScript!";
        function test() {
            $.ajax({
                type: 'POST',
                url: "${createLink(controller:'Product', action:'receiveJson')}",
                data: '{"name":"jonas"}', // or JSON.stringify ({name: 'jonas'}),
                success: function(data) { 
                    console.log(data);
                    console.log(status);
                    alert('data: ' + data); 
                },
                contentType: "application/json",
                dataType: 'json'
            });
        }
        </script>
    </body>
</html>