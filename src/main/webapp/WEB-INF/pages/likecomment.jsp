<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 8:57 PM
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
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-justified nav-pills">
                    <li class="">
                        <a href="/addcomment">New Comment</a>
                    </li>
                    <li class="active">
                        <a href="#">Like Comment</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
       <%-- <div class="row">
            <div class="col-md-12">
                <div class="alert alert-dismissable alert-success">
                    <b>[SUCCESS] Comment liked!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] You must fill in all fields!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] User already likes this comment!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[WARN] This is a PRIVACY warning!</b>
                </div>
            </div>
        </div>--%>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">User:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>Tommy Bucks</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Comment:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>Jimmy Playkid: "U wot 1v1 me m9"</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <a class="btn btn-success">Like Comment</a>
                    <a class="btn btn-warning">Run "What-If" Analysis</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
