let options = [];

function addCar(){
    let request = new XMLHttpRequest();
    //create variables that are connected to HTML input boxes
    let make = document.getElementById('make').value;
    let model = document.getElementById('model').value;
    let price = document.getElementById('price').value;
    let year = document.getElementById('year').value;
    let layout = document.getElementById('driveLayout').value;
    let type = document.getElementById('bodyType').value;
    let size = document.getElementById('size').value;

    //create a car object and add it to the database
    let car = {make, model, price, year, layout, type, size};
    let response = {car, options};
    let responseString = JSON.stringify(response);

    request.open("POST", "http://localhost:8080/betterbe_3/rest/cars", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    console.log(response);
    request.send(responseString);
    options = [];
}

function addOption() {
    let value = document.getElementById("Name").value;
    let manufacturer = document.getElementById("Manufacturer").value;
    let price = Number(document.getElementById("priceForOption").value);
    let option_type = document.getElementById("Type").value;
    let option = {value, manufacturer, price, option_type};
    options.push(option);
    let optionHTML = `<tr>
                <td>` + option_type + `</td>
                <td>` + value + `</td>
                <td>€` + price.toFixed(2) + `</td>
                <td>` + manufacturer + `</td>
            </tr>`
    document.getElementById("optionsTable").innerHTML += optionHTML;
    console.log(options)
}