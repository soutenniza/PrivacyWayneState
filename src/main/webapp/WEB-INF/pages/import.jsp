<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 8:55 PM
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
<br>
<div class="section">
    <div class="container">
        <c:if test="${deleted != null}">
            <div id="message" class="alert alert-success">
                <b>[SUCCESS] ${deleted}</b>
            </div>
        </c:if>
        <c:if test="${wrong != null}">
            <div id="message" class="alert alert-danger">
                <b>[FAIL] ${wrong}</b>
            </div>
        </c:if>
        <c:if test="${pass != null}">
            <div id="message" class="alert alert-success">
                <b>[SUCCESS] ${pass}</b>
            </div>
        </c:if>
        <c:if test="${fail != null}">
            <div id="message" class="alert alert-danger">
                <b>[FAIL] ${fail}</b>
            </div>
        </c:if>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <div class="section">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <p class="text-danger text-left">Warning: Importing a data set will append the existing data set!&nbsp;</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form method="POST" action="/submitimport" class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Data set:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>class_42_people.json</option>
                                </select>
                            </div>
                        </div>
                        <input type="submit" value="delete" class="btn btn-success"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <div class="section">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <p class="text-danger text-left">Warning: Deleting the data set will DELETE THE DATA SET!&nbsp;</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form method="POST" action="/submitdeleteset" class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label path="name" class="control-label">Passcode</label>
                            </div>
                            <div class="col-sm-10">
                                <input class="form-control" name="pass" placeholder="top secret do not tell ok" required type="password">
                            </div>
                        </div>
                        <input type="submit" value="delete" class="btn btn-danger"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
