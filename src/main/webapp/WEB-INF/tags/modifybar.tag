<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Modify Network</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-pills">
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/import.jsp' ? 'active' : ''}">
                        <a href="/import">Import Data Set</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/addperson.jsp' ? 'active' : ''}">
                        <a href="/addperson">Add / Remove Nodes</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/addedge.jsp' ? 'active' : ''}">
                        <a href="/addedge">Add / Remove Edges</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/modifynode.jsp' ? 'active' : ''}">
                        <a href="/modifynode">Modify Node</a>
                    </li>
                    <li class="${pageContext.request.requestURI eq '/WEB-INF/pages/modifyedge.jsp' ? 'active' : ''}">
                        <a href="/modifyedge">Modify Edge</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>