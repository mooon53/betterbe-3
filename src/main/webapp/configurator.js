let doneTypes;
let chosenOptions;
let rules;
let options;
let carId;
let price;

function onLoad() { //When the page is loaded
	sessionId();
	changeLogInButton();
	let url = new URL(location.href); //Get the current url
	let searchParams = url.searchParams; //Get the search parameters (?carID=<search parameter>)
	let carId = searchParams.get("carID"); //Get the id in the search parameters
	if (carId !== null) { //If there is 1 set
		// dropDown.value = carId; //Set the dropdown to select this car
		loadConfigurator(carId); //Load the configurator for this car
	} else {
		location.href = "/betterbe_3#products";
	}
}


function loadConfigurator(carId) {
	rules = []; //Create empty variables
	options = new Map(); //We use a map for options because optionID's don't always start at 0
	chosenOptions = [];
	doneTypes = [];
	document.title = "BetterBe · Configurator"; //Set page title back to default without car info
	// document.getElementById("configurator").innerHTML = ""; //Empty the configurator
	// document.getElementById("total").innerHTML = ""; //Empty the total
	// document.getElementById("mandatoryCheck").innerHTML = ""; //Remove checking button
	// let carId = document.getElementById("cars").value; //Find the carID from the dropdown menu
	if (carId !== null && carId !== "null") { //If a car has been selected
		let url = new URL(location.href); //Get the current url
		let searchParams = url.searchParams; //Get the parameters of the url
		if (searchParams.get("carID") !== carId) { //If the carID in the parameters is not the actual carID (or does not exist)
			history.replaceState("","","?carID=" + carId); //Change the parameter to have teh right carID
		}
		let getRequest = new XMLHttpRequest(); //Create new HTTP request
		getRequest.onreadystatechange = function () { //When the HTTP request's status changes
			if (this.readyState === 4 && this.status === 200) { //If full response has been received and the response is OK
				let response = JSON.parse(this.responseText); //Parse the json out of the response
				let car = response.car; //Put the information from a car in the object
				for (const option of response.options) { //Loop through the options for this car
					options.set(option.id, option); //Add it to the map of options
				}
				rules = response.rules; //Set the list of rules
				let carPrice = Number(`${car.price}`); //Get the price of the car as a number
				document.getElementById("make").innerText = car.make;
				document.getElementById("model").innerText = car.model;
				document.getElementById("year_of_production").innerText = car.year;
				document.getElementById("image").src = "images/cars/" + carId + ".png";
				let carInfo = `${car.make} ${car.model} ${car.year}`; //Get the car info as 1 string
				document.getElementById("image").alt = carInfo;
				document.title = "BetterBe · Configurator · " + carInfo; //Set the title to contain the info of the car we're configuring
				console.log(options);
				doneTypes = []; //Create an empty list to store which types have been added to the html (because they are categorized by type)
				for (let entry of options) { //Loop through all options of the current car
					let optionType = entry[1].optionType; //Get the type of the current option
					if (!doneTypes.includes(optionType)) { //If the option's type is not yet in the array of done types
						doneTypes.push(optionType); //Add the option type to the array of done types
						let typeOptions = [] //Create an empty array to store all options of this type
						for (let option2 of options) { //Loop through all options again
							if (option2[1].optionType === optionType) { //If the option type of the current option matches the type of the option you're adding
								typeOptions.push(option2); //Add the option to the list of options of this type
							}
						}
						let optionHTML = `<div class="option">` + optionType + `</div>
								<hr class="line">`
						for (const optionOfType of typeOptions) { //Loop through the options of this type
							const curOption = optionOfType[1]; //Get the JSON from the option
							let price = Number(`${curOption.price}`); //Get the price of the option
							optionHTML += `<label class="container"> 
												<input id="${curOption.id}" name="${curOption.optionType}" type="checkbox" onchange="checkConfiguration(this)">
												<div class="text"> ${curOption.value} +€` + price.toFixed(2) + ` </div>
												<span class="checkmark"></span>
											</label>`;
						}
						console.log(optionHTML);
						console.log(document.getElementById("body"))
						document.getElementById("body").innerHTML += optionHTML; //add the option's form to the html
					}
				}
				document.getElementById("total").innerText = `Total price: €` + carPrice.toFixed(2); //Add the total price, initially this is just the price of the car
				// document.getElementById("mandatoryCheck").innerHTML = `<button onclick="mandatoryCheck()">Check configuration</button>`; //Add the check button
			}
		}
		getRequest.open("GET", "http://localhost:8080/betterbe_3/rest/cars/" + carId, true); //Set request to be a get request and set url
		getRequest.setRequestHeader("Accept", "application/json"); //Set the accept-header
		getRequest.send(); //Send the request
	} else { //If there's no option chosen (default)
		let url = new URL(location.href); //Get the current url
		history.replaceState("","",url.pathname); //Remove the carID parameter
	}
}

function checkConfiguration(checkbox) { //Checks whether configuration is allowed every time an option is selected
	let allowed = true; //Is the configuration allowed? (yes by default, will change to false otherwise)
	let id = Number(checkbox.id); //Get the id of the checkbox, which we can use to get the option and it's rules
	if (!checkbox.checked) chosenOptions.splice(chosenOptions.indexOf(id), 1); //If the checkbox was unchecked, remove the option from the list of chosen options (if it exists there)
	else if (!chosenOptions.includes(id)) chosenOptions.push(id); //If it was checked and not in the chosen options yet, add it to the chosen options
	let relevantRules = findRelevantRules(id);
	for (const rule of relevantRules) { //Loop through the relevant rules
		let curOptions = rule.options; //Get the options this rule applies to
		let count = 0; //Create a counter to count how many of the items have been selected
		for (const option of curOptions) { //Loop through the options the rule applies to
			if (chosenOptions.includes(Number(option))) count++; //Check if the number has been selected, if so increase the counter by 1
		}
		if (rule.exclusive && count > 1) allowed = false; //If the rule is exclusive, but more than 1 option in this rule has been selected, the options is not allowed to be chosen
	}
	if (allowed) { //If the option is allowed
		let totalDiv = document.getElementById("total"); //Find the div that stores the total price, this should be done by global variable
		let priceSplit = totalDiv.innerText.split("€"); //Get the price from this div
		price = Number(priceSplit[1]) //Turn the price into a number
		let selectedOption = options.get(id); //select the option that was changed
		if (checkbox.checked) { //If it was selected
			price += selectedOption.price; //Add the price to the total
		} else { //otherwise
			price -= selectedOption.price; //Subtract the price from the total
		}
		totalDiv.innerText = "Total price: €" + price.toFixed(2); //Put the total price and some text back in the div
		for (const rule of relevantRules) { //Loop through all relevant rules, to disable now unavailable rules
			let curOptions = JSON.parse(JSON.stringify(rule.options)); //Get the options (JSON stuff is to create a copy and not change the original array)
			curOptions.splice(curOptions.indexOf(id), 1); //Remove the option that was just selected, we don't want to disable this checkbox
			for (const option of curOptions) { //Loop through all options to be disabled
				disableCheckbox(option, checkbox.checked); //Disable the checkbox
			}
		}
	} else { //If option is not allowed
		chosenOptions.splice(chosenOptions.indexOf(id), 1); //Remove it from chosen options
		checkbox.checked = !checkbox.checked; //Invert the status of the checkbox
		document.getElementById(id).disabled = true; //Disable the checkbox
		alert("Sorry, that is not allowed"); //Warn the user
	}
}

function disableCheckbox(id, disable) { //sets the checkbox.disabled with id to the value of disable
	let checkbox = document.getElementById(id); //Find the checkbox
	if (disable) { //If you want to disable the checkbox
		checkbox.disabled = disable; //Just disable it, no check necessary
	} else { //If you want to enable it
		if (checkRules(id)) checkbox.disabled = disable; //If selecting this option would be allowed, enable it, otherwise you don't want to enable it
	}
}

function findRelevantRules(id) {
	let relevantRules = []; //Create an empty array to fill with the rules relevant to this option
	for (const rule of rules) { //Loop through the rules
		let curOptions = rule.options; //Get the options this rule applies to
		if (curOptions.includes(id)) relevantRules.push(rule); //If it applies to the current option, add it to the relevant rules
	}
	return relevantRules;
}

function checkRules(id) {
	let relevantRules = findRelevantRules(id);
	for (const rule of relevantRules) { //Loop through the relevant rules
		let curOptions = rule.options; //Get the options this rule applies to
		let count = 1; //Create a counter to count how many of the items have been selected, we put it to 1 because we are assuming the checkbox has been checked
		for (const option of curOptions) { //Loop through the options the rule applies to
			if (chosenOptions.includes(Number(option))) count++; //Check if the number has been selected, if so increase the counter by 1
		}
		if (rule.exclusive && count > 1) return false; //If the rule is exclusive, but more than 1 option in this rule has been selected, the options is not allowed to be chosen
	}
	return true;
}

function mandatoryCheck() { //To check if the mandatory options have been chosen
	let allowed = true; //Is it allowed, true by default, could change later
	for (const rule of rules) { //Loop through all rules
		let curOptions = rule.options; //Get the options the current rule applies to
		let count = 0; //Count how many of the options have been chosen
		for (const option of curOptions) { //Loop through the options this rule applies to
			if (chosenOptions.includes(Number(option))) count++; //If the option has been selected, add 1 to the count
		}
		if (rule.mandatory && count < 1) allowed = false; //If a rule isn't followed, set allowed to false
	}
	if (allowed) { //If the configuration is allowed
		alert("legal configuration!"); //Tell the user
	} else { //If not
		alert("illegal configuration"); //Tell the user
	}
	return allowed;
}

function addToCart() {
	if(mandatoryCheck()) {
		let postRequest = new XMLHttpRequest();
		let sessionId = getSessionId();
		let carConfiguration = {carId, chosenOptions, price, sessionId};
		let carConfString = JSON.stringify(carConfiguration);
		console.log(carConfString);
		postRequest.open("POST", "http://localhost:8080/betterbe_3/rest/checkout", true);
		postRequest.setRequestHeader("Accept", "application/json");
		postRequest.setRequestHeader("Content-Type", "application/json");
		postRequest.send(carConfString);
	}
}

function checkout() {
	addToCart();
	location.href="checkout.html";
}