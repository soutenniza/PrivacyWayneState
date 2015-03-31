<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 9:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>PrivacyWayne</title>
    <link rel="favicon" href="/resources/images/favicon.ico" type="image/x-icon" />
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
<body>
<mytags:navbar/>
<mytags:modifybar/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-justified nav-pills">
                    <li class="active">
                        <a href="#">Add Interest</a>
                    </li>
                    <li class="">
                        <a href="/removeinterest">Remove Interest</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
     <%--   <div class="row">
            <div class="col-md-12">
                <div class="alert alert-dismissable alert-success">
                    <b>[SUCCESS] &nbsp;Interest added!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] You must fill in all fields!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] User is already has entered interest!</b>
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
                                <label class="control-label">User:
                                    <br>
                                </label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>George James</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Interest:</label>
                            </div>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" placeholder="Sports">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Privacy Value:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>1</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Visibility Value:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>1</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Sensitivity Value:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>1</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <div class="col-md-6">
                        <a class="btn btn-success">Add Interest</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
