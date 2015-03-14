<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 2:09 PM
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
<mytags:modifybar/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-pills">
                    <li class="active">
                        <a href="/addedge">Add</a>
                    </li>
                    <li>
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
                <div class="alert alert-danger alert-dismissable">
                    <b>A Person node and &nbsp;a Person node cannot have the edge LIKES. Click
                        the HELP tab for more information.</b>
                </div>
                <div class="alert alert-dismissable alert-success">
                    <b>[SUUESS] John Smith and Tom Peters are now friends.</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[WARN] John Smith and Tom Peters have a low number of mutual friends.</b>SCORE:
                    2 AVERAGE: 4 THRESHOLD: 3.5
                    <b>&nbsp;</b>
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
                <blockquote>
                    <p>IF Attribute:</p>
                    <footer>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">
                                    <i>Privacy Value</i>
                                </label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>1</option>
                                    <option>2</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">
                                    <i>Visibility Value</i>
                                </label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">
                                    <i>Sensitivity Value</i>
                                </label>
                            </div>
                            <div class="col-sm-10">
                                <select class="form-control">
                                    <option>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                </select>
                            </div>
                        </div>
                    </footer>
                </blockquote>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <a class="btn btn-block btn-lg btn-primary">Run "What-If" Analysis</a>
            </div>
            <div class="col-md-12">
                <a class="btn btn-block btn-lg btn-primary">Submit</a>
            </div>
        </div>
    </div>
</div>
</body>

</html>
