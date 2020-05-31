<div class="d-flex justify-content-center">
    <div class="col-4 border border-light p-4">
        <h3 class="text-white text-center">Please, fill in the form.</h3>
        <form class="pt-3 text-white" method="POST" action="/updateObject">
            <input type="hidden" name="id" value="${object.objectID}">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" id="name" placeholder="Enter your object name" value="${object.name}">
            </div>
            <div class="form-group">
                <label for="sh_desc">Short description</label>
                <input type="text" class="form-control" name="sh_desc" id="sh_desc" placeholder="Enter short description of object" value="${object.sh_desc}">
            </div>
            <div class="form-group">
                <label for="long_desc">Long description</label>
                <input type="text" class="form-control" name="long_desc" id="long_desc" placeholder="Enter long description of object" value="${object.long_desc}">
            </div>
            <div class="form-group mb-3">
                <label for="price_day">Price for one day</label>
                <input type="text" class="form-control" name="price_day" id="price_day" placeholder="Enter price for one day" value="${object.price_day}">
            </div>
            <div class="form-group">
                <label for="city">City</label>
                <input type="text" class="form-control" name="city" id="city" placeholder="Enter the city name" value="${object.city}">
            </div>
            <div class="form-group mb-3">
                <label for="postal_code">Postal code</label>
                <input type="text" class="form-control" name="postal_code" id="postal_code" placeholder="Enter postal code" value="${object.postal_code}">
            </div>
            <div class="form-group mb-3">
                <label for="address">Address</label>
                <input type="text" class="form-control" name="address" id="address" placeholder="Enter object full address" value="${object.address}">
            </div>
            <div class="form-group mb-3">
                <label for="imgsrc">Image source</label>
                <input type="text" class="form-control" name="imgsrc" id="imgsrc" placeholder="Enter image source" value="${object.imgsrc}">
            </div>

            <button type="submit" class="btn btn-primary mt-3">Edit object</button>

        </form>
    </div>
</div>