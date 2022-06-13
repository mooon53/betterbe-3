function onClick(){
    let request = new XMLHttpRequest();
    //create variables that are connected to HTML input boxes
    let make = document.getElementById('make').value;
    let model = document.getElementById('model').value;
    let price = document.getElementById('price').value;
    let productionYear = document.getElementById('year').value;
    let driveLayout = document.getElementById('driveLayout').value;
    let bodyType = document.getElementById('bodyType').value;
    let clazz = document.getElementById('clazz').value;

    //create a car object and add it to the database
    let car = `{"id" : 10, "make": ` + make + `, "model":` + model + `, "price":` + price + `, "production_year":` + productionYear + `, "driveLayout":` + driveLayout + `, "bodyType":` + bodyType + `, "clazz":` + clazz + `}`;
    let carString = JSON.stringify(car);

    request.open("POST", "http://localhost:8080/betterbe_3/rest/cars", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    console.log(car);
    request.send(car);

}