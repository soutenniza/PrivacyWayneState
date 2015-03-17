<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 9:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<ul class="nav nav-justified nav-pills">
    <li class="active">
        <a href="#">Create User</a>
    </li>
    <li class="">
        <a href="/removeuser">Delete User</a>
    </li>
</ul>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-dismissable alert-success">
                    <b>[SUCCESS] &nbsp;User created!</b>
                </div>
                <div class="alert alert-danger alert-dismissable">
                    <b>[ERROR] You must fill in all required fields!</b>
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
                    <div class="form-group has-error">
                        <div class="col-sm-2">
                            <label for="inputEmail3" class="control-label">First Name</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="John">
                        </div>
                    </div>
                    <div class="form-group has-error">
                        <div class="col-sm-2">
                            <label for="inputEmail3" class="control-label">Last Name</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="Smith">
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Age</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
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
                    <div class="form-group has-success">
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
                    <div class="form-group has-success">
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
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">Gender</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
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
                    <div class="form-group has-warning">
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
                    <div class="form-group has-warning">
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
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Location</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
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
                    <div class="form-group has-success">
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
                    <div class="form-group has-success">
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
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">Political View</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
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
                    <div class="form-group has-warning">
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
                    <div class="form-group has-warning">
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
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Work</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
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
                    <div class="form-group has-success">
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
                    <div class="form-group has-success">
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
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">Education</label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
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
                    <div class="form-group has-warning">
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
                    <div class="form-group has-warning">
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
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Birthday</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" placeholder="MM/DD/YYYY">
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
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
                    <div class="form-group has-success">
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
                    <div class="form-group has-success">
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
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">Phone</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" placeholder="XXX-XXX-XXXX">
                        </div>
                    </div>
                    <div class="form-group has-warning">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
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
                    <div class="form-group has-warning">
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
                    <div class="form-group has-warning">
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
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">Interest 1</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" placeholder="Sports">
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <div class="col-sm-2">
                            <label class="control-label">
                                <i>Privacy Value</i>
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
                    <div class="form-group has-success">
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
                    <div class="form-group has-success">
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
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <a class="btn btn-block btn-lg btn-primary">Submit</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
