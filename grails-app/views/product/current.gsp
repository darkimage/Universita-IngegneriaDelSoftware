<html>
    <head> 
        <title>Testing</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
    <body>
        <div id="test"></div>
        <span>${tt} ${args}</span>
        <button onclick="test()">Click me</button>
        <g:form controller="Product" action="save">
            <label>First Name: </label>
            <g:textField name="firstName"/><br/>
            <label>Last Name: </label>
            <g:textField name="lastName"/><br/>
            <label>Age: </label>
            <g:textField name="age"/><br/>
            <g:actionSubmit value="Save"/>
        </g:form>

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