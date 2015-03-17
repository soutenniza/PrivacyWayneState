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
                        <a href="/import">Import Data Set</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/createuser.jsp' ? 'active' : ''}">
                        <a href="/createuser">Create / Delete User</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/addfriend.jsp' ? 'active' : ''}">
                        <a href="/addfriend">Add / Remove Friend</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/creategroup.jsp' ? 'active' : ''}">
                        <a href="/creategroup">Create / Delete Group</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/joingroup.jsp' ? 'active' : ''}">
                        <a href="/joingroup">Join / Leave Group</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/addinterest.jsp' ? 'active' : ''}">
                        <a href="/addinterest">Add / Remove Interest</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/changeplevels.jsp' ? 'active' : ''}">
                        <a href="/changeplevels">Change Privacy Levels</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/addcomment.jsp' ? 'active' : ''}">
                        <a href="/addcomment">New Comment / Like Comment</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/viewprofile.jsp' ? 'active' : ''}">
                        <a href="/viewprofile">View User Profile</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>