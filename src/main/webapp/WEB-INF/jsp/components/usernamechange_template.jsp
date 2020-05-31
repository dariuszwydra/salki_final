<div class="d-flex justify-content-center">
    <div class="col-4 border border-light p-4">
        <h3 class="text-white text-center">Changing the username</h3>
        <form class="pt-3 text-white" action="/username-change" method="POST">
            <div class="form-group">
                <label for="username">New username</label>
                <input type="text" class="form-control" name="username" id="username" placeholder="Enter your new username" value="${user.username}">
            </div>
            <button type="submit" class="btn btn-primary mt-3">Update</button>
        </form>
    </div>
</div>