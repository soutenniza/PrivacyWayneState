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
                <p>Relationship strength is calculated based on how many mutual friends, common groups and interactions between
                    two users. Additionally, social distance is a factor of the strength but, lower the distance, higher the
                    relationship strength.</p>
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
                <p>Group Analysis is done by using a What-if Scenario where a User is trying to join a group. The what-if Analysis will find the percentage of the members in that group are actually friends with the user. If that value is below a certain threshold, a warning notification appears. An analysis also notifies of outliers in the user's circles are openly showing sensitive information and that they could be a risk.</p>
                <p>The full analysis shows how being in a group could allow someone to guess your attributes such as age. It finds the average age in the group and compares with the actual age to provide a percentage accuracy of guessing that value. </p>
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
                <p>Content analysis examines the comments, replies and likes that are exchanged between users. This is done in several ways.</p>
                <p>Comment Sentiments: The sentiment of each comment is created upon its creation. This is then used for analysis purposes. The service
                is able to examine an individual users overall sentiment by examining all of their comments. This can be useful for identifying friends in
                your network that are constantly posting negative content. This analysis uses
                <a href="http://nlp.stanford.edu/software/corenlp.shtml">Stanford CoreNLP (Natural Language Processing)</a>.</p>
                <p>Your Comment Content: Posts on this network are considered public. Every other user on the social network is able to see what you and
                others post. The analysis checks to make sure that you do not mention any attribute information that you have set as very sensitive, low visibility,
                 or high privacy.</p>
                <p>Others Comment Content: This analysis takes a look at the replies made to your posts. It checks to see if others have directly mentioned your attribute
                information that you set as very sensitive, low visibility, or high privacy. Others do not know you privacy values, so it's valuable to be notified of these
                occurrences.</p>
                <p>Possible Content Concerns: Sometimes attribute information is not directly mentioned in your or other peoples comments. With this analysis, the system
                tries to identify instances where a comment is eluding to the owner or recipients private data. This analysis is based off identifying synonyms and key pronouns
                 that direct ownership of attribute information. This analysis uses
                    <a href="http://lucene.apache.org/core/3_0_3/api/contrib-wordnet/org/apache/lucene/wordnet/package-summary.html">Lucene Wordnet</a>. This analysis is
                prone to false positives so a confidence score is also reported.</p>
            </div>
        </div>
        <hr>
        <br>
        <center><a href="">Still lost? Check out the complete USER MANUAL</a> </center>
        <br>
    </div>
</div>
</body>

</html>
