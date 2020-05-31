<div class="d-flex justify-content-center">
    <div class="col-4 border border-light p-4">
        <h3 class="text-white text-center">Changing the password</h3>
        <form class="pt-3 text-white" action="/password-change" method="POST">
            <div class="form-group">
                <label for="password">New password</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="Enter your new password">
            </div>
            <div class="form-group">
                <label for="password2">Re-enter new password</label>
                <input type="password2" class="form-control" name="password2" id="password2" placeholder="Re-enter your new password">
            </div>
            <button type="submit" class="btn btn-primary mt-3">Update</button>
    </div>
</div>