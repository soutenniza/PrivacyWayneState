<%--
  Created by IntelliJ IDEA.
  User: vannguyen
  Date: 3/11/15
  Time: 1:55 PM
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

    <!-- Load c3.css -->
    <link href="${pageContext.request.contextPath}/resources/c3/c3.css" rel="stylesheet" type="text/css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/theme.css" rel="stylesheet" >

    <!-- Load d3.js and c3.js -->
    <script src="${pageContext.request.contextPath}/resources/c3/d3.v3.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resources/c3/c3.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/d3/d3.v3.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resources/d3/d3.min.js"></script>

</head>

<body>
<mytags:navbar/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Reporting</h1><br>
                <h5><i>Get a full privacy report for a specific user</i></h5>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <form method="POST" action="/submitreport" class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">User</label>
                        </div>
                        <div class="col-sm-10">
                            <form:select path="inputPerson" class="form-control" name="inputPerson">
                                <form:options items="${peopleList}"/>
                            </form:select>
                        </div>
                    </div>
                    <input type="submit" value="Get Report" class="btn btn-success"/>
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
                <h5>${user}</h5><br>
                <h5>${pscore}</h5><br>
                <c:if test="${psdata != null}">
                    <div id="chartps"></div>

                    <script language="JavaScript">
                        var chartps = c3.generate({
                            bindto: '#chartps',
                            data: {
                                columns: [
                                    ${psdata}
                                ]
                            }
                        });
                    </script>
                </c:if>
                <br>
                <h6>${user2}</h6>
                <c:if test="${pspdata != null}">
                    <div id="chartpsp"></div>

                    <script language="JavaScript">
                        var chartps = c3.generate({
                            bindto: '#chartpsp',
                            data: {
                                columns: [
                                    ${pspdata}
                                ],
                                type: 'pie'
                            }
                        });
                    </script>
                </c:if>
                <br>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Attribute</th>
                            <th>Privacy Value</th>
                            <th>Visibility Value</th>
                            <th>Sensitivity Value</th>
                            <th>Attribute Exposure: Actual</th>
                            <th>Attribute Exposure: Target</th>
                            <th>Attribute Exposure: Change</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${netvis}
                    </tbody>
                </table>
                <br>
                <h5>Attribute Visibility Over Time</h5><br>
                <c:if test="${asdata != null}">
                    <div id="chart2"></div>

                    <script language="JavaScript">
                        var chart = c3.generate({
                            bindto: '#chart2',
                            data: {
                                columns: [
                                    ${asdata}
                                ]
                            }
                        });
                    </script>
                </c:if>
                <br>
                <h5>Communication Flow: </h5><i><h6> Incoming verses outgoing interactions between people on this network:</h6></i><br>
                ${cfdata}
                <br>
                <h5>Relationships over time: </h5><i><h6>This is how your relationship changed over time:</h6></i><br>
                    ${fddata}
                <br>



            </div>
        </div>
    </div>
</c:if>


</body>
</html>
