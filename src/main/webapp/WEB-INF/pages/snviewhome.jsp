<%--
  Created by IntelliJ IDEA.
  User: Zack
  Date: 4/6/15
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<body>
<mytags:navbar/>
<mytags:session/>
<br>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                    <h1>${user}</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${attributes != null}">
                        ${attributes}
                    </c:if>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${friends != null}">
                        ${friends}
                    </c:if>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${groups != null}">
                        ${groups}
                    </c:if>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${comments != null}">
                        ${comments}
                    </c:if>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${likes != null}">
                        ${likes}
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
</body>
</html>
