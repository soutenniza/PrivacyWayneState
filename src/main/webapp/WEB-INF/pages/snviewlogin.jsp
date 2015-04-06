<%--
  Created by IntelliJ IDEA.
  User: Zack
  Date: 4/6/15
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<br>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <c>
                <div class="row">
                    <div class="col-md-12">
                        <p>Select a user to login as:</p>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-6">
                        <form  method="POST" action="/submitlogin" class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label class="control-label">User:</label>
                                </div>
                                <div class="col-sm-10">
                                    <form:select path="inputPerson" class="form-control" name="inputPerson">
                                        <form:options items="${peopleList}"/>
                                    </form:select>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-12">
                                    <input type="submit" name="login" value="login" class="btn btn-success"/>     <input type="submit" name="new" value="create user" class="btn btn-default"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c>
        </div>
    </div>
</div>

</body>
</html>
