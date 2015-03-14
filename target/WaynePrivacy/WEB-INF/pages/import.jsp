<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 1:36 PM
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
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Modify Network</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-pills">
                    <li class="active">
                        <a href="/import">Import Data Set</a>
                    </li>
                    <li>
                        <a href="/addperson">Add / Remove Nodes</a>
                    </li>
                    <li class="">
                        <a href="/addedge">Add / Remove Edges</a>
                    </li>
                    <li>
                        <a href="/modifynode">Modify Node</a>
                    </li>
                    <li>
                        <a href="/modifyedge">Modify Edge</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ul class="list-group">
                    <li class="list-group-item">dataset1.json</li>
                    <li class="list-group-item">Full_class_42_persons.json</li>
                    <li class="list-group-item">Large_network_100_persons.json</li>
                    <li class="list-group-item">new_set.json</li>
                    <li class="list-group-item">Import From Desktop</li>
                </ul>
            </div>
        </div>
        <div class="col-md-12">
            <a class="btn btn-block btn-lg btn-primary">DELETE EXISTING USER SET</a>
        </div>
    </div>
</div>
</body>

</html>
