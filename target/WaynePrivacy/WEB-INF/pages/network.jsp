<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>PrivacyWayne</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/theme.css" rel="stylesheet" >
</head>

<body>
<mytags:navbar/>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>View Network</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-pills"></ul>
            </div>
        </div>
    </div>
</div>
<center>
    <object data="http://privacywayne.sb02.stations.graphenedb.com:24789/" width="1280" height="720">
        <embed src="http://privacywayne.sb02.stations.graphenedb.com:24789/" width="1280" height="020">
    </object>
</center>
</body>

</html>
