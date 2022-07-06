let carId;
let optionsNames = {};
let rules = [];
function onload() {
	sessionId();
	nonEmployeeDestroyer()
    changeLogInButton();
	let urlLocal = new URL(location.href); //Get the current url
	let searchParams = urlLocal.searchParams; //Get the search parameters (?carID=<search parameter>)
	carId = searchParams.get("carId"); //Get the id in the search parameters

    let getRequest = new XMLHttpRequest();
    getRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("timeline").innerHTML = '' + '';
            let response = JSON.parse(this.responseText);
            //String date, String make, String model, Long year, Double basePrice,
            // String optionType, String optionValue, Double optionPrice
            let carTable = document.getElementById("table2");
            let car = response.car;
            carTable.innerHTML += `<tr>
                <td>${car.make}</td>
                <td>${car.model}</td>
                <td>${car.year}</td>
                <td>${car.price}</td>
                <td>${car.layout}</td>
                <td>${car.type}</td>
                <td>${car.size}</td>`
            let table = document.getElementById("timeline");
            for (let i in response.options) {
                let line = response.options[i];
                if (!line.endDate) {
                    line.endDate = "-";
                }
                optionsNames[line.id] = line.value;
                table.innerHTML += `<tr>
                            <td>${line.startDate}</td>
                            <td>${line.endDate}</td>
                            <td>${line.optionType}</td>
                            <td>${line.value}</td>
                            <td>${line.price}</td>
                            <td><button class="button" onclick="removeOption(${line.id})">remove option</button></td>
                            <td><button class="button" onclick="editOption(${line.id})">Edit option</button></td>
                        </tr>`;
                if(line.endDate === "-") {
                    let optionHTML = `<p></p><input type="checkbox" name="` + line.value + `" id="` + line.id + `" class="optionForRule">
                   <label for="` + line.id + `">` + line.optionType + ` : ` + line.value + `</label></>`
                    document.getElementById("optionsForRule").innerHTML += optionHTML;
                }
            }
            let rulesTable = document.getElementById("rulesTable");
            for (let i in response.rules) {
                let line = response.rules[i];
                let ruleNames = [];
                rules = [];
                for(let j in line.options) {
                    ruleNames.push(optionsNames[line.options[j]]);
                    rules.push(line.options[j]);
                }
                rulesTable.innerHTML += `<tr>
                            <td>${ruleNames}</td>
                            <td>${line.exclusive}</td>
                            <td>${line.mandatory}</td>
                            <td><button class="button" onclick="removeRule([` + rules.toString() + `])">remove rule</button></td>
                        </tr>`;
            }
        }
    }
    getRequest.open("GET", "rest/timeline/" + carId, true);
    getRequest.setRequestHeader("Accept", "application/json");
    getRequest.setRequestHeader("sessionId", getSessionId());
    console.log(getRequest);
    getRequest.send();
}

function addOptionToCar() {
    let request = new XMLHttpRequest();
    let value = document.getElementById("Name").value;
    let price = Number(document.getElementById("priceForOption").value);
    let option_type = document.getElementById("Type").value;
    let string = {carId, value, price, option_type}
    let responseString = JSON.stringify(string);
    request.open("POST", "rest/addOption", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.send(responseString);
}

function removeOption(id) {
    let request = new XMLHttpRequest();
    let string = {id}
    let responseString = JSON.stringify(string);
    request.open("POST", "rest/remove", false);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.send(responseString);
}

function editOption(id) {
    let request = new XMLHttpRequest();
    let string = {id}
    let responseString = JSON.stringify(string);
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            //String date, String make, String model, Long year, Double basePrice,
            // String optionType, String optionValue, Double optionPrice
            let table = document.getElementById("timeline");

            if (!response.endDate) {
                response.endDate = "-";
            }
            table.innerHTML += `<tr>
                            <td>${response.start_date}</td>
                            <td>${response.endDate}</td>
                            <td><input type = "text" id="Type" placeholder=${response.option_type}></td>
                            <td><input type="text" class="option" id="Name" placeholder=${response.value}></td>
                            <td><input  type="number" class="option" id="priceForOption" placeholder=${response.price}></td>
                            <td><button class="button" onclick="addOptionToCar(); removeOption(${response.id});">Add option</button></td>
                        </tr>`;
        }
    }

request.open("POST", "rest/edit", true);
request.setRequestHeader("Content-Type", "application/json");
request.setRequestHeader("Accept", "application/json");
request.send(responseString);
}

function removeRule(options) {
    let request = new XMLHttpRequest();
    let optionsJSON = {options};
    request.open("POST", "rest/removeRule", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.send(JSON.stringify(optionsJSON));
}

function addRuleToCar() {
    let request = new XMLHttpRequest();
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

    let rule = {options: chosenOptions, mandatory, exclusive, carId};
    let ruleString = JSON.stringify(rule);
    request.open("POST", "rest/addRule", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.send(ruleString);
}

function removeCar() {
    let request = new XMLHttpRequest();
    let string = {carId}
    let responseString = JSON.stringify(string);
    request.onreadystatechange = function() {
        if (this.status === 200 && this.readyState === 4) {
            alert("Car deleted successfully");
            location.reload();
        } else if (this.readyState === 4) {
            alert("Something went wrong, please contact a system administrator");
            location.reload();
        }
    }
    request.open("POST", "rest/timeline", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.send(responseString);
}
