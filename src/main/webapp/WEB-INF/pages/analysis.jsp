<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
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
                    <input type="submit" value="Run Analysis" class="btn btn-block btn-lg btn-primary"/>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Utility</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-danger alert-dismissable">
                    <b>Edward Smith is an outlier in your personal network. SCORE: 1.34 AVERAGE:
                        7.23 THRESHOLD: 2.5</b>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Relationships</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-danger alert-dismissable">
                    <b>John Clause has a low number of mutual friends. SCORE: 2 AVERAGE: 4 THRESHOLD:
                        3.5</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>John Clause has infrequent contact with you. SCORE: 0 AVERAGE: 0.2 THRESHOLD:
                        0.1</b>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
