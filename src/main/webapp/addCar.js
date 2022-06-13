function onClick(){
    let request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200){
            //create variables that are connected to HTML input boxes
            let make = document.getElementById('make').value;
            let model = document.getElementById('model').value;
            let price = document.getElementById('price').value;
            let productionYear = document.getElementById('year').value;
            let driveLayout = document.getElementById('driveLayout').value;
            let bodyType = document.getElementById('bodyType').value;
            let clazz = document.getElementById('class').value;

            //create a car object and add it to the database
            var car = {make: make, model: model, price: price, productionYear: productionYear, driveLayout: driveLayout, bodyType: bodyType, class: clazz};
            let carString = JSON.stringify(car);

            request.open("POST", "http://localhost:8080/betterbe_3/cars", true);
            request.send(carString);
        }
    }
}