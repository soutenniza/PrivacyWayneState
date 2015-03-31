<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Analysis</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-pills"></ul>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <form method="POST" action="/submitanalysis" class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">User</label>
                        </div>
                        <div class="col-sm-10">
                            <form:select path="inputPerson1" class="form-control" name="inputPerson1">
                                <form:options items="${peopleList}"/>
                            </form:select>
                        </div>
                    </div>
                    <input type="submit" value="Run Analysis" class="btn btn-success"/>
                </form>
            </div>
        </div>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <c:if test="${user != null}">
                ${user}
            </c:if>
            <div class="row">
                <div class="col-md-12">
                    <h1>Utility</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">

                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h1>Relationships</h1>
                </div>
            </div>
            <div class="row">
                <c:if test="${relationships != null}">
                    ${relationships}
                </c:if>
                <c:if test="${relationshipsok != null}">
                    <div id="message" class="alert alert-success">
                        <b>${relationshipsok}</b>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>

</html>
