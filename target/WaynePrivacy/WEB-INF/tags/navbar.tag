<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
            <a class="navbar-brand" href="/"><span>PrivacyWayne</span></a>
        </div>
        <div class="collapse navbar-collapse" id="#navbar-ex-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/home.jsp' ? 'active' : ''}">
                    <a href="/">Home</a>
                </li>
                <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/import.jsp' ? 'active' : ''}">
                    <a href="/import">Modify Network</a>
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