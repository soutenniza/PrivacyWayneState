<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>PrivacyWayne</title>
    <link  rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon" />
    <meta charset="utf-8">
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
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Help</h1>
            </div>
        </div>
    </div>
</div>
<hr>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <img src="http://i.imgur.com/znV2czx.png"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>About</h1>
                <h3>What is PrivacyWayne?</h3>
                <p>PrivacyWayne is a cutting-edge web privacy research tool. The project itself consists of proprietary social network,
                privacy analysis, and reporting systems.</p>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6">
                <img src="http://i.imgur.com/GQRSHQW.png"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>Objective</h1>
                <h3>What is it supposed to do?</h3>
                <p>PrivacyWayne is designed to simulate a proxy service that can be applied to any social network.
                By analyzing the actions made by and content related to users, it is able to provide a detailed and informative layer of
                privacy to the social network experience.</p>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6">
                <img src="http://i.imgur.com/7aAKmD7.png"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>Privacy Score</h1>
                <h3>What is a privacy score?</h3>
                <p>Privacy score is calculated by summing all of a persons attributes set visibility score multiplied by
                    its sensitivity score. The higher the score, the higher the risk. A higher privacy score generally means
                    that the person considers their information sensitive, but does not hide it from others.</p>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6">
                <img src="http://i.imgur.com/ZKA6Giy.png"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>Privacy Value</h1>
                <h3>What is a privacy value?</h3>
                <p>A privacy value is a numerical value you set on an individual attribute. The scale ranges from 0 - least private,
                to 4 - most private. When you set a privacy value for an attribute, it is considered a target value for that attribute.
                When setting this value, take into consideration how private you consider that individual attribute.</p>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6">
                <img src="http://i.imgur.com/I0CH1o7.png"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>Visibility Value</h1>
                <h3>What is a visibility value?</h3>
                <p>A visibility value is a numerical value that you set on an individual attribute. The scale ranges from 0 - only you can see this,
                 1 - only friends can see this, 2 - only friends of friends can see this, 3 - only friends of friends of friends can see this and
                4 - everyone on the network can see this. When setting the visibility value for an individual attribute, take into consideration
                who you want seeing this information.</p>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6">
                <img src="http://i.imgur.com/IB4zZNe.png"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>Sensitivity Value</h1>
                <h3>What is a sensitivity value?</h3>
                <p>A sensitivity value is a numerical value that you set on an individual attribute. The scale ranges from 0 - least sensitive,
                    to 4 - most sensitive. When setting the sensitivity value for an individual attribute, take into consideration
                    how sensitive you consider this information.</p>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6">
                <img src="http://i.imgur.com/A6AnuIo.png"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>Relationship Strength</h1>
                <h3>How is it calculated?</h3>
                <p>Relationship strength is calculated using magic.</p>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6">
                <img src="http://i.imgur.com/rTZhL4l.png"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>Group Analysis</h1>
                <h3>How is it done?</h3>
                <p>It is done by doing things.</p>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6">
                <img src="http://i.imgur.com/0MId6sq.png"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>Content Analysis</h1>
                <h3>How is it done?</h3>
                <p>It is done by doing things.</p>
            </div>
        </div>
    </div>
</div>
</body>

</html>
