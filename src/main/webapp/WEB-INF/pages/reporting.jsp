<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 1:55 PM
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
<div class="navbar navbar-default navbar-static-top">
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
                    <a href="/import">Modify Network</a>
                </li>
                <li class="">
                    <a href="/network">View Network</a>
                </li>
                <li class="">
                    <a href="/analysis">Analysis</a>
                </li>
                <li class="active">
                    <a href="/reporting">Reporting</a>
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
                <h1>Reporting</h1>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">User</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>Paul Jones</option>
                                <option>John Smith</option>
                                <option>Betty James</option>
                            </select>
                        </div>
                        <a class="btn btn-lg btn-primary">Submit</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Score / Metric</th>
                        <th>Target</th>
                        <th>Current Value</th>
                        <th>Threshold</th>
                        <th>% Change</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Attribute Exposure</td>
                        <td>25%</td>
                        <td>32%</td>
                        <td>45%</td>
                        <td>-2%</td>
                    </tr>
                    <tr>
                        <td>Privacy Score</td>
                        <td>2.5</td>
                        <td>2.23</td>
                        <td>1.5</td>
                        <td>+0.02%</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <img src="${pageContext.request.contextPath}/resources/images/img.png" class="center-block img-responsive">
            </div>
        </div>
    </div>
</div>
</body>

</html>
