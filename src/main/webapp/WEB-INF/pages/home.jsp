<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>PrivacyWayne</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/theme.css" rel="stylesheet" >
</head>
<mytags:navbar/>
<body>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <h1>Welcome</h1>
                    <p>to PrivacyWayne</p>
                    <a href="/createuser" class="btn btn-lg btn-primary">Get Started</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>