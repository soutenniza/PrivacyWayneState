<%--
  Created by IntelliJ IDEA.
  User: Zachary
  Date: 3/16/2015
  Time: 9:01 PM
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
<div class="container">
    <ul class="nav nav-justified nav-pills">
        <li class="active">
            <a href="#">Create User</a>
        </li>
        <li class="">
            <a href="/removeuser">Delete User</a>
        </li>
    </ul>
</div>
<br>
<div class="section">
    <div class="container">
        <c:if test="${valid != null}">
            <div id="message" class="alert alert-success">
                <b>[SUCCESS] ${valid}</b>
            </div>
        </c:if>
        <c:if test="${exists != null}">
            <div id="message" class="alert alert-danger">
                <b>[FAIL] ${exists}</b>
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
                    <form method="POST" action="/submitcreateuser" class="form-horizontal" role="form" >
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label path="name" class="control-label">First Name</label>
                            </div>
                            <div class="col-sm-10">
                                <input class="form-control" name="inputFirstName" placeholder="John" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2">
                                <label class="control-label">Last Name</label>
                            </div>
                            <div class="col-sm-10">
                                <input class="form-control" name="inputLastName" placeholder="Smith" required>
                            </div>
                        </div>
                        <table>
                            <colgroup>
                                <col width="55%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label">Age</label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input type="number" name="inputAge" min="5" max="125" value="21">
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeP" id="optionRadioAP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeP" id="optionRadioAP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeP" id="optionRadioAP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeP" id="optionRadioAP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeP" id="optionRadioAP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeV" id="optionRadioAV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeV" id="optionRadioAV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeV" id="optionRadioAV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeV" id="optionRadioAV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeV" id="optionRadioAV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeS" id="optionRadioAS0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeS" id="optionRadioAS1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeS" id="optionRadioAS2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeS" id="optionRadioAS3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputAgeS" id="optionRadioAS4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>
                        <table>
                            <colgroup>
                                <col width="55%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th>
                                    <div class="form-group">
                                            <label class="col-lg-2 control-label">Gender</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGender" id="optionRadioG0" value="Male" checked="">
                                                        Male
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGender" id="optionRadioG1" value="Female">
                                                        Female
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGender" id="optionRadioG2" value="Not Specified">
                                                        Not Specified
                                                    </label>
                                                </div>
                                            </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderP" id="optionRadioGP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderP" id="optionRadioGP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderP" id="optionRadioGP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderP" id="optionRadioGP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderP" id="optionRadioGP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderV" id="optionRadioGV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderV" id="optionRadioGV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderV" id="optionRadioGV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderV" id="optionRadioGV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderV" id="optionRadioGV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderS" id="optionRadioGS0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderS" id="optionRadioGS1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderS" id="optionRadioGS2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderS" id="optionRadioGS3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputGenderS" id="optionRadioGS4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>
                        <table>
                            <colgroup>
                                <col width="55%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label">Location</label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input type="text" name="inputLocation" required>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationP" id="optionRadioLP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationP" id="optionRadioLP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationP" id="optionRadioLP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationP" id="optionRadioLP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationP" id="optionRadioLP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationV" id="optionRadioLV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationV" id="optionRadioLV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationV" id="optionRadioLV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationV" id="optionRadioLV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationV" id="optionRadioLV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationS" id="optionRadioLS0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationS" id="optionRadioLS1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationS" id="optionRadioLS2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationS" id="optionRadioLS3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputLocationS" id="optionRadioLS4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>
                        <table>
                            <colgroup>
                                <col width="55%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label">Political View</label>
                                        </div>
                                        <div class="col-sm-10">
                                            <select name="inputPolitical" class="form-control">
                                                <option>Extreme Right</option>
                                                <option>Far Right</option>
                                                <option>Right</option>
                                                <option>Center Right</option>
                                                <option>Center</option>
                                                <option>Center Left</option>
                                                <option>Left</option>
                                                <option>Far Left</option>
                                                <option>Extreme Left</option>
                                            </select>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalP" id="optionRadioPP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalP" id="optionRadioPP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalP" id="optionRadioPP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalP" id="optionRadioPP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalP" id="optionRadioPP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalV" id="optionRadioPV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalV" id="optionRadioPV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalV" id="optionRadioPV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalV" id="optionRadioPV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalV" id="optionRadioPV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalS" id="optionRadioPS0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalS" id="optionRadioPS1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalS" id="optionRadioPS2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalS" id="optionRadioPS3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPoliticalS" id="optionRadioPS4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>
                        <table>
                            <colgroup>
                                <col width="55%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label">Work</label>
                                        </div>
                                        <div class="col-sm-10">
                                            <select name="inputWork" class="form-control">
                                                <option>Unemployed</option>
                                                <option>Accountancy, Banking and Finance</option>
                                                <option>Charity and Voluntary</option>
                                                <option>Energy and Utilities</option>
                                                <option>Environment and Agriculture</option>
                                                <option>Hospitality</option>
                                                <option>Law</option>
                                                <option>Leisure, Sport and Tourism</option>
                                                <option>Media and Internet</option>
                                                <option>Public Services and Admin</option>
                                                <option>Retail</option>
                                                <option>Science and Pharmaceuticals</option>
                                                <option>Teaching and Education</option>
                                                <option>Business, Consulting and Management</option>
                                                <option>Creative Arts and Design</option>
                                                <option>Engineering and Manufacturing</option>
                                                <option>Healthcare</option>
                                                <option>Information Technology</option>
                                                <option>Law Enforcement and Security</option>
                                                <option>Marketing, Advertising and PR</option>
                                                <option>Property and Construction</option>
                                                <option>Recruitment and HR</option>
                                                <option>Sales</option>
                                                <option>Social Care</option>
                                                <option>Transport and Logistics</option>
                                            </select>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkP" id="optionRadioWP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkP" id="optionRadioWP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkP" id="optionRadioWP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkP" id="optionRadioWP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkP" id="optionRadioWP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkV" id="optionRadioWV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkV" id="optionRadioWV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkV" id="optionRadioWV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkV" id="optionRadioWV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkV" id="optionRadioWV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkS" id="optionRadioWS0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkS" id="optionRadioWS1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkS" id="optionRadioWS2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkS" id="optionRadioWS3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputWorkS" id="optionRadioWS4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>
                        <table>
                            <colgroup>
                                <col width="55%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label">Education</label>
                                        </div>
                                        <div class="col-sm-10">
                                            <select name="inputEducation" class="form-control">
                                                <option>None</option>
                                                <option>GED</option>
                                                <option>Associates</option>
                                                <option>4-Year Degree</option>
                                                <option>Masters</option>
                                                <option>PHD</option>
                                            </select>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationP" id="optionRadioEP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationP" id="optionRadioEP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationP" id="optionRadioEP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationP" id="optionRadioEP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationP" id="optionRadioEP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationV" id="optionRadioEV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationV" id="optionRadioEV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationV" id="optionRadioEV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationV" id="optionRadioEV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationV" id="optionRadioEV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationS" id="optionRadioES0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationS" id="optionRadioES1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationS" id="optionRadioES2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationS" id="optionRadioES3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputEducationS" id="optionRadioES4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>
                        <table>
                            <colgroup>
                                <col width="55%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label">Birthday</label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input name="inputBday" type="date" class="form-control" placeholder="MM/DD/YYYY" required>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayP" id="optionRadioBP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayP" id="optionRadioBP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayP" id="optionRadioBP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayP" id="optionRadioBP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayP" id="optionRadioBP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayV" id="optionRadioBV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayV" id="optionRadioBV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayV" id="optionRadioBV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayV" id="optionRadioBV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayV" id="optionRadioBV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayS" id="optionRadioBS0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayS" id="optionRadioBS1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayS" id="optionRadioBS2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayS" id="optionRadioBS3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputBdayS" id="optionRadioBS4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>

                        <table>
                            <colgroup>
                                <col width="55%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label">Phone</label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input name="inputPhone" type="text" class="form-control" placeholder="XXX-XXX-XXXX" required>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneP" id="optionRadioPhP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneP" id="optionRadioPhP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneP" id="optionRadioPhP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneP" id="optionRadioPhP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneP" id="optionRadioPhP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneV" id="optionRadioPhV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneV" id="optionRadioPhV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneV" id="optionRadioPhV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneV" id="optionRadioPhV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneV" id="optionRadioPhV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneS" id="optionRadioPhS0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneS" id="optionRadioPhS1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneS" id="optionRadioPhS2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneS" id="optionRadioPhS3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputPhoneS" id="optionRadioPhS4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>
                        <table>
                            <colgroup>
                                <col width="55%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                                <col width="15%"/>
                            </colgroup>
                            <tr>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <label class="control-label">Interest</label>
                                        </div>
                                        <div class="col-sm-10">
                                            <input name="inputInterest1" type="text" class="form-control" placeholder="Sports or Hobbies" required>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Privacy</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1P" id="optionRadioIP0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1P" id="optionRadioIP1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1P" id="optionRadioIP2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1P" id="optionRadioIP3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1P" id="optionRadioIP4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Visibility</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1V" id="optionRadioIV0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1V" id="optionRadioIV1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1V" id="optionRadioIV2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1V" id="optionRadioIV3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1V" id="optionRadioIV4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div class="form-group">
                                        <div class="col-sm-10">
                                            <label class="col-lg-2 control-label">Sensitivity</label>
                                            <div class="col-lg-10">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1S" id="optionRadioIS0" value="0" checked="">
                                                        0
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1S" id="optionRadioIS1" value="1">
                                                        1
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1S" id="optionRadioIS2" value="2">
                                                        2
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1S" id="optionRadioIS3" value="3">
                                                        3
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="inputInterest1S" id="optionRadioIS4" value="4">
                                                        4
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                        </table>
                        <input type="submit" value="submit" class="btn btn-block btn-lg btn-primary"/>
                    </form>
                </div>
                <br><br>
            </div>
        </div>
    </div>
</div>
</body>
</html>
