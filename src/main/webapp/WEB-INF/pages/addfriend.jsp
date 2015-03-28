<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                    <li class="active">
                        <a href="#">Add Friend</a>
                    </li>
                    <li class="">
                        <a href="/removefriend">Remove Friend</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <p>Select two users to become friends!</p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <form  method="POST" action="/submitaddfriend" class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">User 1:</label>
                        </div>
                        <div class="col-sm-10">
                            <form:select path="inputPerson1" class="form-control" name="inputPerson1">
                                <form:options items="${peopleList}"/>
                            </form:select>

                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">User 2:</label>
                        </div>
                        <div class="col-sm-10">
                            <form:select path="inputPerson2" class="form-control" name="inputPerson2">
                                <form:options items="${peopleList}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <input type="submit" value="Add Friend" class="btn btn-block btn-lg btn-primary"/>
                            <a class="btn btn-warning">Run "What-If" Analysis</a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-6">
                <br>
                <br>
            </div>
        </div>
    </div>
</div>
</body>
</html>
