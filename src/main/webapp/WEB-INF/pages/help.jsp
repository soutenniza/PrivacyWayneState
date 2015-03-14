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
    <meta charset="utf-8">
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
                <h1>Help</h1>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <img src="https://unsplash.imgix.net/photo-1416400453940-65c69d70ad91?q=75&amp;fm=jpg&amp;s=54806c1456b35f1d56ca356663d1bdd2"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>About</h1>
                <h3>What is PrivacyWayne?</h3>
                <p>PrivacyWayne is a cutting-edge web privacy research tool.</p>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1>Nodes</h1>
                <h3>What are nodes?</h3>
                <p>Nodes can be people, groups, attributes or comments.</p>
            </div>
            <div class="col-md-6">
                <img src="https://unsplash.imgix.net/photo-1418065460487-3e41a6c84dc5?q=75&amp;fm=jpg&amp;s=127f3a3ccf4356b7f79594e05f6c840e"
                     class="img-responsive">
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <img src="https://unsplash.imgix.net/photo-1414438992182-69e404046f80?q=75&amp;fm=jpg&amp;s=009738f458a5234335b2e05677029b39"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1>Edges</h1>
                <h3>What are edges?</h3>
                <p>Edges connect nodes. There are 5 types of edges. FRIEND, MEMBER, HAS,
                    OWNER, LIKES.</p>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1>Node Attribure</h1>
                <h3>What is a node attribute?</h3>
                <p>An attribute node has the LABEL "node". The PROPERTY is the identifier
                    for the specific attribute. For example a PROPERTY can be "interest", "age",
                    or "location", ect. The final value in each attribute node is the VALUE.
                    This contains the unique information. For example, if the PROPERTY was
                    "age" the VALUE could be "21". If the PROPERTY was "interest" the VALUE
                    could be "SPORTS".</p>
            </div>
            <div class="col-md-6">
                <img src="https://ununsplash.imgix.net/photo-1414637104192-f9ab9a0ee249?q=75&amp;fm=jpg&amp;s=39603686d9062f5c4a5309a45e28264e"
                     class="img-responsive">
            </div>
        </div>
    </div>
</div>
</body>

</html>
