let options = [];
let rules = [];

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
    let response = {car, options, rules};
    let responseString = JSON.stringify(response);

    request.open("POST", "http://localhost:8080/betterbe_3/rest/cars", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.send(responseString);
    options = [];
}

function addOption() {
    let value = document.getElementById("Name").value;
    //let manufacturer = document.getElementById("Manufacturer").value;
    let price = Number(document.getElementById("priceForOption").value);
    let option_type = document.getElementById("Type").value;
    let option = {value, price, option_type};
    options.push(option);
    let optionHTML = `<tr>
                <td>` + option_type + `</td>
                <td>` + value + `</td>
                <td>€` + price.toFixed(2) + `</td>
                <td>` + manufacturer + `</td>
            </tr>`
    document.getElementById("optionsTable").innerHTML += optionHTML;
    let optionId = option_type + value;
    optionHTML = `<input type="checkbox" name="` + optionId + `" id="` + optionId + `" class="optionForRule">
                    <label for="` + optionId + `">` + option_type + `: ` + value + `</label>`
    document.getElementById("optionsForRule").innerHTML += optionHTML;
}

function addRule() {
    let optionButtons = document.getElementsByClassName("optionForRule");
    let chosenOptions = [];
    for (let i = 0; i < optionButtons.length; i++) {
        let option = optionButtons.item(i);
        if (option.checked) chosenOptions.push(option.id);
    }
    let mandatory = document.getElementById("mandatory").checked;
    let exclusive = document.getElementById("exclusive").checked;
    let ruleHTML = `<tr>
                        <td>`
    for (let optionIndex in chosenOptions) {
        let option = chosenOptions[optionIndex];
        ruleHTML += option;
        if (chosenOptions.indexOf(option) !== chosenOptions.length -1) ruleHTML += `<br>`;
    }
    ruleHTML += `</td>
                <td>` + mandatory + `</td>
                <td>` + exclusive + `</td></tr>`
    document.getElementById("rulesTable").innerHTML += ruleHTML;
    let rule = {options: chosenOptions, mandatory, exclusive};
    rules.push(rule);
}
let options = [];
let rules = [];

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
    let response = {car, options, rules};
    let responseString = JSON.stringify(response);

    request.open("POST", "http://localhost:8080/betterbe_3/rest/cars", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
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
    let optionId = option_type + value;
    optionHTML = `<input type="checkbox" name="` + optionId + `" id="` + optionId + `" class="optionForRule">
                    <label for="` + optionId + `">` + option_type + `: ` + value + `</label>`
    document.getElementById("optionsForRule").innerHTML += optionHTML;
}

function addRule() {
    let optionButtons = document.getElementsByClassName("optionForRule");
    let chosenOptions = [];
    for (let i = 0; i < optionButtons.length; i++) {
        let option = optionButtons.item(i);
        if (option.checked) chosenOptions.push(option.id);
    }
    let mandatory = document.getElementById("mandatory").checked;
    let exclusive = document.getElementById("exclusive").checked;
    let ruleHTML = `<tr>
                        <td>`
    for (let optionIndex in chosenOptions) {
        let option = chosenOptions[optionIndex];
        ruleHTML += option;
        if (chosenOptions.indexOf(option) !== chosenOptions.length -1) ruleHTML += `<br>`;
    }
    ruleHTML += `</td>
                <td>` + mandatory + `</td>
                <td>` + exclusive + `</td></tr>`
    document.getElementById("rulesTable").innerHTML += ruleHTML;
    let rule = {options: chosenOptions, mandatory, exclusive};
    rules.push(rule);
}
