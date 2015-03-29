<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        <a href="#">Join Group</a>
                    </li>
                    <li class="">
                        <a href="/leavegroup">Leave Group</a>
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
                <%--<div class="alert alert-dismissable alert-success">--%>
                    <%--<b>[SUCCESS] &nbsp;User joined group!</b>--%>
                <%--</div>--%>
                <%--<div class="alert alert-danger alert-dismissable">--%>
                    <%--<b>[ERROR] User is already in this group!</b>--%>
                <%--</div>--%>
                <%--<div class="alert alert-danger alert-dismissable">--%>
                    <%--<b>[ERROR] You must fill in all fields!</b>--%>
                <%--</div>--%>
                <%--<div class="alert alert-danger alert-dismissable">--%>
                    <%--<b>[WARN] This is a PRIVACY warning!</b>--%>
                <%--</div>--%>
                <c:if test="${message != null}">
                    <div id="message" class="alert alert-success">
                        <b>[SUCCESS] Joined group!</b>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <p>Select a user and a group to join!</p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <form  method="POST" action="/submitjoingroup" class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">Person:</label>
                        </div>
                        <div class="col-sm-10">
                            <form:select name="inputPerson" path="inputPerson" class="form-control">
                                <form:options items="${peopleList}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">Group:</label>
                        </div>
                        <div class="col-sm-10">
                            <form:select name="inputGroup" path="inputGroup" class="form-control">
                                <form:options items="${groupList}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <input type="submit" value="join group" class="btn btn-block btn-lg btn-primary"/>
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
