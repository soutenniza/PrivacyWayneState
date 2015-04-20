<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 9:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
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
<mytags:modifybar/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-justified nav-pills">
                    <li class="active">
                        <a href="#">Add Interest</a>
                    </li>
                    <li class="">
                        <a href="/removeinterest">Remove Interest</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<br>
<div class="section">
    <div class="container">
        <c:if test="${message != null}">
            <div id="message" class="alert alert-success">
                <b>[SUCCESS] Added Interest!</b>
            </div>
        </c:if>
        <c:if test="${messagefail != null}">
            <div id="message" class="alert alert-danger">
                <b>[FAIL] Person already had this interest!</b>
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
                    <form  method="POST" action="/submitnewinterest" class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">User:</label>
                            </div>
                            <div class="col-sm-10">
                                <form:select name="inputPerson" path="inputPerson" class="form-control">
                                    <form:options items="${peopleList}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Interest: </label>
                            </div>
                            <div class="col-sm-10">
                                <input class="form-control" name="inputInterest" placeholder="Music" required>
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
                        <input type="submit" value="add interest" class="btn btn-success"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
