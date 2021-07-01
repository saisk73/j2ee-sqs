<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Form1</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">
</head>
<body>
    <div class="container mt-4">
        <form action="post-message" method="post">
            <input type="text" class="form-control col-5" name="message" placeholder="enter a message" />
            <button type="submit" class="btn btn-dark mt-3">Send Message</button>
        </form>
        <div class="mt-4">
            <h3>Messages</h3>
            <div id="data-div" class="mt-4">
                <p>loading...</p>
            </div>
        </div>
    </div>
</h1>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script>
        $( document ).ready(function() {
            $.get( "/demo_war_exploded/list-messages", function( data ) {
                $( "#data-div" ).empty();
                for(var obj of data) {
                    console.log(obj);
                    $( "#data-div" ).append(`
                    <div id="message-${'${'}obj.messageId}" class="d-flex justify-content-between mt-2">
                        <p>${'${'}obj.body}</p>
                        <p class="text-secondary">Date Time: 01-07-2021 22:00 IST</p>
                    </div>
                    `);
                } if(data.length === 0) {
                    $( "#data-div" ).append(`<p>No Messages available</p>`);
                }
                console.log(data);
            });
        });
        function deleteMessage(id) {
            console.log(id);
            $.get( `/demo_war_exploded/delete-messages/${'${'}id}`, function( data ) {
                console.log(id)
            });
        }
    </script>

</body>
</html>