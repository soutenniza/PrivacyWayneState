<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
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
