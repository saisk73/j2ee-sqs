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
            <a href="list-messages">List Messages</a>
        </div>
    </div>
</h1>

</body>
</html>