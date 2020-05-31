<div class="d-flex justify-content-center">
    <div class="col-4 border border-light p-4">
        <h3 class="text-white text-center">Please, fill in the registration form.</h3>
        <form class="pt-3 text-white" method="POST" action="/updateUser">
            <input type="hidden" name="id" value="${user.userID}">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" name="username" id="username" placeholder="Enter your username" value="${user.username}">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="Enter your password" value="${user.password}">
            </div>
            <div class="form-group">
                <label for="firstName">First name</label>
                <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Enter your first name" value="${user.firstName}">
            </div>
            <div class="form-group mb-3">
                <label for="lastName">Last name</label>
                <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Enter your last name" value="${user.lastName}">
            </div>
            <div class="form-group mb-3">
                <label for="usertype">Usertype (1 user/ 2 tenant/ 3 admin)</label>
                <input type="text" class="form-control" name="usertype" id="usertype" placeholder="Enter usertype" value="${user.usertype}">
            </div>


            <button type="submit" class="btn btn-primary mt-3">Update user</button>

        </form>
    </div>
</div>