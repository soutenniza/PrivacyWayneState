<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<mytags:modifybar/>
<div class="container">
    <div class="col-md-12">
        <ul class="nav nav-justified nav-pills">
            <li class="">
                <a href="/createuser">Create User</a>
            </li>
            <li class="active">
                <a href="#">Delete User</a>
            </li>
        </ul>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
       <%-- <div class="row">
            <div class="col-md-12">
                <div class="alert alert-dismissable alert-success">
                    <b>[SUCCESS] &nbsp;User deleted!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] You must select a user!</b>
                </div>
            </div>
        </div>--%>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">User</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>Don Jones</option>
                                    <option>Camp Tucker</option>
                                </select>
                            </div>
                        </div>
                        <a class="btn btn-danger">Submit</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
