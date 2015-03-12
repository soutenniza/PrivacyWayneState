<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 1:46 PM
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
                    <a href="#">Help</a>
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
                    <li class="active">
                        <a href="/addperson">Add / Remove Nodes</a>
                    </li>
                    <li class="">
                        <a href="/addedge">Add / Remove Edges</a>
                    </li>
                    <li>
                        <a href="#">Modify Node</a>
                    </li>
                    <li>
                        <a href="#">Modify Edge</a>
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
                    <li class="active">
                        <a href="/addperson">Add Person Node</a>
                    </li>
                    <li>
                        <a href="#">Add Group Node</a>
                    </li>
                    <li>
                        <a href="#">Add Attribute Node</a>
                    </li>
                    <li>
                        <a href="#">Add Comment Node</a>
                    </li>
                    <li>
                        <a href="#">Remove Node</a>
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
                    <b>You must fill in all fields. Please try again.</b>
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
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Interests</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>
                                        <input type="checkbox">Sports</td>
                                    <td>
                                        <input type="checkbox">Dance</td>
                                    <td>
                                        <input type="checkbox">Art</td>
                                    <td>
                                        <input type="checkbox">Politics</td>
                                    <td>
                                        <input type="checkbox">Education</td>
                                    <td>
                                        <input type="checkbox">Music</td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="checkbox">Cars</td>
                                    <td>
                                        <input type="checkbox">Woodworking</td>
                                    <td>
                                        <input type="checkbox">Food</td>
                                    <td>
                                        <input type="checkbox">Animals</td>
                                    <td>
                                        <input type="checkbox">Soccer</td>
                                    <td>
                                        <input type="checkbox">Football</td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="checkbox">Technology</td>
                                    <td>
                                        <input type="checkbox">Outdoors</td>
                                    <td>
                                        <input type="checkbox">Fitness</td>
                                    <td>
                                        <input type="checkbox">Culture</td>
                                    <td>
                                        <input type="checkbox">Space</td>
                                    <td>
                                        <input type="checkbox">Language</td>
                                </tr>
                                </tbody>
                            </table>
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
