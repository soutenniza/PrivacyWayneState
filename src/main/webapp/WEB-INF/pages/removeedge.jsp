<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/11/2015
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <a class="navbar-brand" href="#"><span>PrivacyWayne</span></a>
        </div>
        <div class="collapse navbar-collapse" id="#navbar-ex-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="/">Home</a>
                </li>
                <li class="active">
                    <a href="/import">Modify Network</a>
                </li>
                <li>
                    <a href="/network">View Network</a>
                </li>
                <li>
                    <a href="/analysis">Analysis</a>
                </li>
                <li>
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
                <h1>Modify Network</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-pills">
                    <li class="">
                        <a href="/import">Import Data Set</a>
                    </li>
                    <li class="">
                        <a href="/addperson">Add / Remove Nodes</a>
                    </li>
                    <li class="active">
                        <a href="/addedge">Add / Remove Edges</a>
                    </li>
                    <li>
                        <a href="/modifynode">Modify Node</a>
                    </li>
                    <li>
                        <a href="/modifyedge">Modify Edge</a>
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
                <ul class="nav nav-pills">
                    <li class="">
                        <a href="/addedge">Add</a>
                    </li>
                    <li class="active">
                        <a href="/removeedge">Remove</a>
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
                <div class="alert alert-dismissable alert-success">
                    <b>[SUCCESS] John Smith and Tom Peters no longer are connected by the edge
                        FRIENDS.</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[FAIL] John Smith and Tom Peters are not connected by the edge FRIENDS.</b>
                </div>
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
                            <label class="control-label">NODE 1</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>[Person] John Smith</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">NODE 2</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>[Person] Tom Peters</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label" contenteditable="true">EDGE</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>FRIENDS</option>
                                <option>2</option>
                            </select>
                        </div>
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
                <a class="btn btn-block btn-lg btn-primary">Submit</a>
            </div>
        </div>
    </div>
</div>
</body>

</html>