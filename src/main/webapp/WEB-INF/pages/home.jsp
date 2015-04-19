<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>PrivacyWayne</title>
    <link  rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon" />
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
<mytags:navbar/>
<body>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <h1>Welcome</h1>
                    <p>to PrivacyWayne</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <table width="100%" border="0">
                <tr>
                    <td><center><img src="http://i.imgur.com/wZXMf4l.png"
                                     class="img-responsive"></center></td>
                    <td>     </td>
                    <td><center><img src="http://i.imgur.com/NLdW3dz.png"
                                     class="img-responsive"></center></td>
                    <td>     </td>
                    <td><center><img src="http://i.imgur.com/33owvM4.png"
                                     class="img-responsive"></center></td>
                </tr>
                <tr>
                    <td><center><a href="/createuser" class="btn btn-lg btn-primary">Admin</a></center></td>
                    <td>     </td>
                    <td><center><a href="/snviewlogin" class="btn btn-lg btn-primary">User</a></center></td>
                    <td>     </td>
                    <td><center><a href="/help" class="btn btn-lg btn-primary">Help</a></center></td>
                </tr>
                <tr>
                    <td><center><p>Click the ADMIN button for a more high-level experience.</p></center></td>
                    <td>     </td>
                    <td><center><p>Click the USER button for a more user-perspective experience.</p></center></td>
                    <td>     </td>
                    <td><center><p>Click the HELP button if you have no idea what is going on or what any of this means.</p></center></td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>

</html>