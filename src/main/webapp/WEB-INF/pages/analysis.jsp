<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<body>
<mytags:navbar/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Analysis</h1><br>
                <h5><i>Get privacy warnings for a specific user</i></h5>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <form method="POST" action="/submitanalysis" class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">User</label>
                        </div>
                        <div class="col-sm-10">
                            <form:select path="inputPerson1" class="form-control" name="inputPerson1">
                                <form:options items="${peopleList}"/>
                            </form:select>
                        </div>
                    </div>
                    <input type="submit" value="Run Analysis" class="btn btn-success"/>
                </form>
            </div>
        </div>
    </div>
</div>
<br>
<c:if test="${ran != null}">
    <div class="section">
        <div class="container">
            <div class="jumbotron">
                <h3>${user}</h3>
                <div class="row">
                    <div class="col-md-12">
                        <h1>Relationships</h1>
                    </div>
                    <h4>Relationship Strength</h4>
                    <div class="col-md-12">
                        <c:if test="${relationshipstrength != null}">
                            ${relationshipstrength}
                        </c:if>
                        <c:if test="${relationshipstrengthok != null}">
                            <div id="message" class="alert alert-success">
                                <b>${relationshipstrengthok}</b>
                            </div>
                        </c:if>
                    </div>
                    <h4>Friend's Privacy Scores</h4>
                    <div class="col-md-12">
                        <c:if test="${fps != null}">
                            ${fps}
                        </c:if>
                        <c:if test="${fpsok != null}">
                            <div id="message" class="alert alert-success">
                                <b>${fpsok}</b>
                            </div>
                        </c:if>
                    </div>
                    <h4>Interactions</h4>
                    <div class="col-md-12">
                        <c:if test="${interactions != null}">
                            ${interactions}
                        </c:if>
                        <c:if test="${interactionsok != null}">
                            <div id="message" class="alert alert-success">
                                <b>${interactionsok}</b>
                            </div>
                        </c:if>
                    </div>
                    <h4>Mutual Friends</h4>
                    <div class="col-md-12">
                        <c:if test="${relationships != null}">
                            ${relationships}
                        </c:if>
                        <c:if test="${relationshipsok != null}">
                            <div id="message" class="alert alert-success">
                                <b>${relationshipsok}</b>
                            </div>
                        </c:if>
                    </div>
                    <h4>Social Distance</h4>
                    <div class="col-md-12">
                        <c:if test="${distances != null}">
                            ${distances}
                        </c:if>
                        <c:if test="${distancesok != null}">
                            <div id="message" class="alert alert-success">
                                <b>${distancesok}</b>
                            </div>
                        </c:if>
                    </div>

                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h1>Groups and Circles</h1>
                    </div>
                    <h4>Mutual Groups</h4>
                    <div class="col-md-12">
                            <c:if test="${gps != null}">
                                ${gps}
                            </c:if>
                            <c:if test="${gpsok != null}">
                                <div id="message" class="alert alert-success">
                                    <b>${gpsok}</b>
                                </div>
                            </c:if>
                    </div>
                    <h4>Associations</h4>
                    <div class="col-md-12">
                        <c:if test="${asc != null}">
                            ${asc}
                        </c:if>
                        <c:if test="${ascok != null}">
                            <div id="message" class="alert alert-success">
                                <b>${ascok}</b>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h1>Content</h1>
                    </div>
                </div>
                <div class="row">
                    <h4>Comment Sentiments</h4>
                    <div class="col-md-12">
                        <c:if test="${sentMsgs != null}">
                            ${sentMsgs}
                        </c:if>
                        <c:if test="${sentMsgsok != null}">
                            <div id="message" class="alert alert-success">
                                <b>${sentMsgsok}</b>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <h4>Comment Content</h4>
                    <div class="col-md-12">
                        <c:if test="${contentMsgs != null}">
                            ${contentMsgs}
                        </c:if>
                        <c:if test="${contentMsgsok != null}">
                            <div id="message" class="alert alert-success">
                                <b>${contentMsgsok}</b>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
</body>

</html>
