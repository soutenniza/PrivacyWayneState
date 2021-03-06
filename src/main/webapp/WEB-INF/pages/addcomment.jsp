<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 8:58 PM
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
    <link  rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon" />
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
                        <a href="#">New Comment</a>
                    </li>
                    <li>
                        <a href="/likecomment">Like Comment</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
        <c:if test="${fail != null}">
            <div id="message" class="alert alert-danger">
                <b>[FAIL] ${fail}</b>
            </div>
        </c:if>
        <c:if test="${message != null}">
            <div id="message" class="alert alert-success">
                <b>[SUCCESS] Added Comment!</b>
            </div>
        </c:if>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                    <form  method="POST" action="/submitnewcomment" class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">User:</label>
                            </div>
                            <div class="col-sm-10">
                                <form:select name="inputPerson" path="inputPerson" class="form-control">
                                    <form:options items="${peopleList}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Comment:</label>
                            </div>
                            <div class="col-sm-10">
                                <textarea name="inputComment" class="form-control"></textarea>
                            </div>
                        </div>
                        <input type="submit" value="add comment" class="btn btn-success"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
