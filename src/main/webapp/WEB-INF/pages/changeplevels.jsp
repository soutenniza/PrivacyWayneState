<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<mytags:modifybar/>
<div class="section">
    <div class="container">
        <c:if test="${success != null}">
            <div id="message" class="alert alert-success">
                <br>
                <b>[SUCCESS] ${success}</b>
            </div>
        </c:if>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${gotfriends == null}"><b></c:if>
                    <p>1. Select a user
                        <br>2. Click "Get Attribute List" to retrieve attributes for that user
                        <c:if test="${gotatts == null}"></b></c:if>
                        <c:if test="${gotatts != null}"><b></c:if>
                        <br>3. Select new privacy values
                        <br>4. Click Save Changes
                        <c:if test="${gotatts != null}"></b></c:if>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <form method="POST" action="/submitsavechanges" class="form-horizontal" role="form">
                        <c:if test="${gotatts == null}">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label class="control-label">User 1:</label>
                                </div>
                                <div class="col-sm-10">
                                    <form:select path="inputPerson" class="form-control" name="inputPerson1">
                                        <form:options items="${peopleList}"/>
                                    </form:select>
                                </div>
                            </div>
                            <br>
                            <input type="submit" name="submit" value="get attribute list" class="btn btn-success"/>
                        </c:if>
                        <c:if test="${gotatts != null}">
                            ${gotatts}
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label class="control-label">Attribute:</label>
                                </div>
                                <div class="col-sm-10">
                                    <form:select path="inputAtt" class="form-control" name="inputAtt1">
                                        <form:options items="${attList}"/>
                                    </form:select>
                                </div>
                            </div>
                            <table>
                                <tr>
                                    <td><div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputP" id="optionRadioAP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputP" id="optionRadioAP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputP" id="optionRadioAP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputP" id="optionRadioAP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputP" id="optionRadioAP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div></td>
                                    <td><div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputV" id="optionRadioV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputV" id="optionRadioV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputV" id="optionRadioV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputV" id="optionRadioV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputV" id="optionRadioV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div><td/>
                                    <td><div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputS" id="optionRadioS0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputS" id="optionRadioS1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputS" id="optionRadioS2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputS" id="optionRadioS3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputS" id="optionRadioS4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div></td>
                                </tr>
                            </table>
                            <input type="submit" name="savechanges" value="save changes" class="btn btn-danger"/> <input type="submit" name="dynamicv" value="Dynamic Visibility" class="btn btn-info"/>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>