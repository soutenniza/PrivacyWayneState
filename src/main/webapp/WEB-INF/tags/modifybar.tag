<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Social Network Tools</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-justified nav-pills">
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/import.jsp' ? 'active' : ''}">
                        <a href="/import">Data Set&#x00ATools</a>
                    </li>
                    <li class="${(pageContext.request.requestURI eq '/WEB-INF/pages/createuser.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/removeuser.jsp') ? 'active' : ''}">
                        <a href="/createuser">Create/Delete&#x00A;User</a>
                    </li>
                    <li class="${(pageContext.request.requestURI eq '/WEB-INF/pages/addfriend.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/removefriend.jsp') ? 'active' : ''}">
                        <a href="/addfriend">Add/Remove&#x00A;Friend</a>
                    </li>
                    <li class="${(pageContext.request.requestURI eq '/WEB-INF/pages/creategroup.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/deletegroup.jsp') ? 'active' : ''}">
                        <a href="/creategroup">Create/Delete&#x00A;Group</a>
                    </li>
                    <li class="${(pageContext.request.requestURI eq '/WEB-INF/pages/joingroup.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/leavegroup.jsp') ? 'active' : ''}">
                        <a href="/joingroup">Join/Leave&#x00A;Group</a>
                    </li>
                    <li class="${(pageContext.request.requestURI eq '/WEB-INF/pages/addinterest.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/removeinterest.jsp') ? 'active' : ''}">
                        <a href="/addinterest">Add/Remove&#x00A;Interest</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/changeplevels.jsp' ? 'active' : ''}">
                        <a href="/changeplevels">Change Privacy&#x00A;Levels</a>
                    </li>
                    <li class="${(pageContext.request.requestURI eq '/WEB-INF/pages/addcomment.jsp')||(pageContext.request.requestURI eq '/WEB-INF/pages/likecomment.jsp') ? 'active' : ''}">
                        <a href="/addcomment">New/Like&#x00A;Comment</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/viewprofile.jsp' ? 'active' : ''}">
                        <a href="/viewprofile">View User&#x00A;Profile</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>