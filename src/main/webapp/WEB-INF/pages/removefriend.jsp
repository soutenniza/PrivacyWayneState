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
<mytags:modifybar/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-justified nav-pills">
                    <li class="">
                        <a href="/addfriend">Add Friend</a>
                    </li>
                    <li class="active">
                        <a href="#">Remove Friend</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
<%--        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-dismissable alert-success">
                    <b>[SUCCESS] &nbsp;Removed friend!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] You must select two valid friends!</b>
                </div>
            </div>
        </div>--%>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                    <p>1. Select a root user as User 1
                        <br>2. Click "Get Friends List" to retrieve root user's friends list
                        <br>3. Select a friend to remove as User 2
                        <br>4. Click Remove Friend</p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">User 1:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>Jon Hopkins</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">User 2:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>-- Get Friends --</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-6">
                    <a class="btn btn-primary">Get Friends List</a>
                    <br>
                    <br>
                    <a class="btn btn-danger">Remove Friend</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
