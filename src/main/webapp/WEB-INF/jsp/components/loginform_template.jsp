<div class="d-flex justify-content-center">
    <div class="col-4 border border-light p-4">
            <h3 class="text-white text-center">Please, fill in the login form.</h3>
            <form class="pt-3 text-white" action="/login-confirm" method="POST">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="Enter your username" value="${user.username}">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Enter your password" value="${user.password}">
                </div>
                <button type="submit" class="btn btn-danger mt-3">Login</button>
            </form>
    </div>
</div>
