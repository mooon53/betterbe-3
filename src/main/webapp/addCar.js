let options = [];
let rules = [];

function onLoad() {
    sessionId();
    changeLogInButton();
    nonEmployeeDestroyer();
}

function addCar(){
    let request = new XMLHttpRequest();
    //create variables that are connected to HTML input boxes
    let make = document.getElementById('make').value;
    let model = document.getElementById('model').value;
    let price = document.getElementById('price').value;
    let year = Number(document.getElementById('year').value);
    let layout = document.getElementById('driveLayout').value;
    let type = document.getElementById('bodyType').value;
    let size = document.getElementById('size').value;

    document.getElementById('make').value = null;
    document.getElementById('model').value = null;
    document.getElementById('price').value = null;
    document.getElementById('year').value = null;
    document.getElementById('driveLayout').value = null;
    document.getElementById('bodyType').value = null;
    document.getElementById('size').value = null;
    document.getElementById("Name").value = null;

    //create a car object and add it to the database
    let car = {make, model, price, year, layout, type, size};
    let sessionId = getSessionId();
    let response = {sessionId, car, options, rules};
    let responseString = JSON.stringify(response);

    request.open("POST", "http://localhost:8080/betterbe_3/rest/cars", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.send(responseString);
    console.log(responseString)
    options = [];
}

function addOption() {
    let value = document.getElementById("Name").value;
    let price = Number(document.getElementById("priceForOption").value);
    let option_type = document.getElementById("Type").value;
    let option = {value, price, option_type};
    let optionId = options.length;
    options.push(option);
    let optionHTML = `<tr>
                <td>` + option_type + `</td>
                <td>` + value + `</td>
                <td>€` + price.toFixed(2) + `</td>
            </tr>`
    document.getElementById("optionsTable").innerHTML += optionHTML;
    let optionName = option_type + value;
    optionHTML = `<p></p><input type="checkbox" name="` + optionName + `" id="` + optionId + `" class="optionForRule">
                   <label for="` + optionId + `">` + option_type + ` : ` + value + `</label></>`
    document.getElementById("optionsForRule").innerHTML += optionHTML;
    document.getElementById("Name").value = "";
    document.getElementById("priceForOption").value = null;
}

function addRule() {
    let optionButtons = document.getElementsByClassName("optionForRule");
    let chosenOptions = [];
    let chosenOptionsNames = [];
    for (let i = 0; i < optionButtons.length; i++) {
        let option = optionButtons.item(i);
        if (option.checked) {
            chosenOptions.push(Number(option.id));
            chosenOptionsNames.push(option.name);
            option.checked = false;
        }
    }
    let mandatory = document.getElementById("mandatory").checked;
    let exclusive = document.getElementById("exclusive").checked;
    document.getElementById("mandatory").checked = false;
    document.getElementById("exclusive").checked = false;
    let ruleHTML = `<tr>
                        <td>`
    for (let optionIndex in chosenOptions) {
        let option = chosenOptionsNames[optionIndex];
        ruleHTML += option;
        if (chosenOptions.indexOf(option) !== chosenOptions.length -1) ruleHTML += `<br>`;
    }
    ruleHTML += `</td>
                <td>` + mandatory + `</td>
                <td>` + exclusive + `</td></tr>`
    document.getElementById("rulesTable").innerHTML += ruleHTML;
    let rule = {options: chosenOptions, mandatory, exclusive};
    rules.push(rule);
    console.log(rule);
}
