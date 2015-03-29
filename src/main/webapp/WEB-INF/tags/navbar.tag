<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
            <a class="navbar-brand" href="/"><span>PrivacyWayne</span></a>
        </div>
        <div class="collapse navbar-collapse" id="#navbar-ex-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/home.jsp' ? 'active' : ''} ">
                    <a href="/">Home</a>
                </li>
                <li class="${(pageContext.request.requestURI eq '/WEB-INF/pages/import.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/createuser.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/removeuser.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/addfriend.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/removefriend.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/creategroup.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/deletegroup.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/joingroup.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/leavegroup.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/addinterest.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/removeinterest.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/changeplevels.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/addcomment.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/likecomment.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/viewprofile.jsp') ? 'active' : ''}">
                    <a href="/import">Social Network</a>
                </li>
                <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/network.jsp' ? 'active' : ''}">
                    <a href="/network">View Network</a>
                </li>
                <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/analysis.jsp' ? 'active' : ''}">
                    <a href="/analysis">Analysis</a>
                </li>
                <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/reporting.jsp' ? 'active' : ''}">
                    <a href="/reporting">Reporting</a>
                </li>
                <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/help.jsp' ? 'active' : ''}">
                    <a href="/help">Help</a>
                </li>
            </ul>
        </div>
    </div>
</div>