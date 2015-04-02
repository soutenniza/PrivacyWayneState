<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 9:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
        <c:if test="${success != null}">
            <div id="message" class="alert alert-success">
                <b>[SUCCESS] ${success}</b>
            </div>
        </c:if>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${gotfriends == null}"><b></c:if>
                    <p>1. Select a root user as User 1
                        <br>2. Click "Get Friends List" to retrieve root user's friends list
                        <c:if test="${gotfriends == null}"></b></c:if>
                        <c:if test="${gotfriends != null}"><b></c:if>
                        <br>3. Select a friend to remove as User 2
                        <br>4. Click Remove Friend
                        <c:if test="${gotfriends != null}"></b></c:if>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <form method="POST" action="/submitremovefriend" class="form-horizontal" role="form">
                        <c:if test="${gotfriends == null}">
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
                            <br>
                            <input type="submit" name="submit" value="get friends list" class="btn btn-success"/>
                        </c:if>
                        <c:if test="${gotfriends != null}">
                            ${gotfriends}
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label class="control-label">User 2:</label>
                                </div>
                                <div class="col-sm-10">
                                    <form:select path="inputPerson2" class="form-control" name="inputPerson2">
                                        <form:options items="${peopleList2}"/>
                                    </form:select>
                                </div>
                            </div>
                            <br>
                            <input type="submit" name="remove" value="remove friend" class="btn btn-danger"/>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
