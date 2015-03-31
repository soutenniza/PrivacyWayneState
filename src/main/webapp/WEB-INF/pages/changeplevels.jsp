<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
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
<mytags:modifybar/>
<br>
<div class="section">
    <div class="container">
       <%-- <div class="row">
            <div class="col-md-12">
                <div class="alert alert-dismissable alert-success">
                    <b>[SUCCESS] &nbsp;Privacy levels updated!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] You must fill in all fields!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[WARN] This is a PRIVACY warning!</b>
                </div>
            </div>
        </div>--%>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">User:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>Tommy Bucks</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Attribute:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>--Get Attributes--</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Privacy Value:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>0</option>
                                    <option>0</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Sensitivity Value:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>0</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Visibility Value:</label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>0</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <a class="btn btn-primary">Get Attributes</a>
                    <a class="btn btn-success">Save Changes</a>
                    <a class="btn btn-warning">Run "What-If" Analysis</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
