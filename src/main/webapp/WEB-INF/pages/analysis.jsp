<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
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
<div class="navbar navbar-default navbar-static-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
            <a class="navbar-brand" href="/"><span>PrivacyWayne</span></a>
        </div>
        <div class="collapse navbar-collapse" id="#navbar-ex-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="/">Home</a>
                </li>
                <li class="">
                    <a href="/removeedge">Modify Network</a>
                </li>
                <li class="">
                    <a href="/network">View Network</a>
                </li>
                <li class="active">
                    <a href="/analysis">Analysis</a>
                </li>
                <li>
                    <a href="/network">Reporting</a>
                </li>
                <li>
                    <a href="/help">Help</a>
                </li>
            </ul>
        </div>
    </div>
</div>
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
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">User</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>John Smith</option>
                                <option>Betty James</option>
                            </select>
                        </div>
                    </div>
                </form>
                <a class="btn btn-lg btn-primary">Run Analysis</a>
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
