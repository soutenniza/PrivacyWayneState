<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 8:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                        <a href="#">Create Group</a>
                    </li>
                    <li class="">
                        <a href="/deletegroup">Delete Group</a>
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
                    <b>[SUCCESS] &nbsp;Created group!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] Group already exists!</b>
                </div>
            </div>
        </div>--%>
    <c:if test="${created != null}">
        <div id="message" class="alert alert-success">
            <b>[SUCCESS] Created group!</b>
        </div>
    </c:if>
    <c:if test="${exists != null}">
        <div id="message" class="alert alert-danger">
            <b>[FAIL] Group exists!</b>
        </div>
    </c:if>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                    <p>Enter a name for the new group!</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-6">
                    <form  method="POST" action="/submitcreategroup" class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Name:</label>
                            </div>
                            <div class="col-sm-10">
                                <input name="inputGroupName" type="text" class="form-control" placeholder="Panda Fan Club" required>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-12">
                                <input type="submit" value="create group" class="btn btn-block btn-lg btn-primary"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
