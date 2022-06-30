let carId;
let optionsNames = {};
let rules = [];
function onload() {
    sessionId();
    changeLogInButton();
    nonEmployeeDestroyer()
    let url = new URL(location.href); //Get the current url
    let searchParams = url.searchParams; //Get the search parameters (?carID=<search parameter>)
    carId = searchParams.get("carId"); //Get the id in the search parameters

    let getRequest = new XMLHttpRequest();
    getRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("timeline").innerHTML = '' + '';
            let response = JSON.parse(this.responseText);
            console.log(response);
            console.log(this.responseText);
            //String date, String make, String model, Long year, Double basePrice,
            // String optionType, String optionValue, Double optionPrice
            let table = document.getElementById("timeline");
            for (let i in response.options) {
                let line = response.options[i];
                console.log(line);
                if (line.end_date === null) {
                    line.end_date = "-";
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
                console.log(rules)
                rulesTable.innerHTML += `<tr>
                            <td>${ruleNames}</td>
                            <td>${line.exclusive}</td>
                            <td>${line.mandatory}</td>
                            <td><button class="button" onclick="removeRule.apply(this, [` + rules.toString() + `])">remove rule</button></td>
                        </tr>`;
            }
        }
    }
    getRequest.open("GET", "http://localhost:8080/betterbe_3/rest/timeline/" + carId, true);
    getRequest.setRequestHeader("Accept", "application/json");
    getRequest.send();
}

function addOptionToCar() {
    let request = new XMLHttpRequest();
    let value = document.getElementById("Name").value;
    let price = Number(document.getElementById("priceForOption").value);
    let option_type = document.getElementById("Type").value;
    let string = {carId, value, price, option_type}
    let responseString = JSON.stringify(string);
    request.open("POST", "http://localhost:8080/betterbe_3/rest/addOption", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.send(responseString);
}

function removeOption(id) {
    let request = new XMLHttpRequest();
    let string = {id}
    let responseString = JSON.stringify(string);
    request.open("POST", "http://localhost:8080/betterbe_3/rest/remove", true);
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
            console.log(response);
            console.log(this.responseText);
            //String date, String make, String model, Long year, Double basePrice,
            // String optionType, String optionValue, Double optionPrice
            let table = document.getElementById("timeline");

            if (response.end_date === null) {
                response.end_date = "-";
            }
            table.innerHTML += `<tr>
                            <td>${response.start_date}</td>
                            <td>${response.end_date}</td>
                            <td>${response.price}</td>
                            <td><input type = "text" id="Type" placeholder=${response.option_type}></td>
                            <td><input type="text" class="option" id="Name" placeholder=${response.value}></td>
                            <td><input  type="number" class="option" id="priceForOption" placeholder=${response.price}></td>
                            <td><button class="button" onclick="addOptionToCar(); removeOption(${response.id});">Add option</button></td>
                        </tr>`;
        }
    }

request.open("POST", "http://localhost:8080/betterbe_3/rest/edit", true);
request.setRequestHeader("Content-Type", "application/json");
request.setRequestHeader("Accept", "application/json");
request.send(responseString);
}

function removeRule() {
    let request = new XMLHttpRequest();
    console.log(rules)
    let ruleArray = [];
    for(let i in rules) {
        ruleArray.push(rules[i]);
    }
    console.log(ruleArray)
    let string = {ruleArray}
    let responseString = JSON.stringify(string);
    console.log(responseString)
    request.open("POST", "http://localhost:8080/betterbe_3/rest/removeRule", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader("Accept", "application/json");
    request.send(responseString);
}
