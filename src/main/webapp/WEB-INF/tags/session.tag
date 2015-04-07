<style>
span.tab{
padding: 0 20px;
}
</style>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h5> Logged in as ${user}</h5>
        </div>
        <form  method="POST" action="/submitsession" class="form-horizontal" role="form">
            <div class="col-md-6">
                <div align="right"><input type="submit" name="myprofile" value="My Profile" class="btn btn-default"/><span class="tab"></span><input type="submit" name="logout" value="log out" class="btn btn-danger"/><span class="tab"></span></div>
            </div>
        </form>
    </div>
</div>
<hr>