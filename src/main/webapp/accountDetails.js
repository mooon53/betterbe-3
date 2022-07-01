let sessionID = getSessionId();
let acc;
let response;
function onLoad() {
	sessionId();
	changeLogInButton();
	let session = sessionValid();
	if (!session.loggedIn) {
		location.href = "login.html";
	} else {
		empCheck();
		displayEmail();
		addConfigurations();
	}
}

function addCarPage(){
	location.href = "addCar.html";
}
function checkoutPage(){
	location.href = "checkout.html";
}

function logout(){
	alert("logged out successfully!")
	location.href = "./";
}

function displayEmail(){
	let request = new XMLHttpRequest();
	request.onreadystatechange = function () {
		if (this.readyState === 4 && this.status === 200) {
			response = JSON.parse(this.responseText);
			if (sessionValid().valid && response.loggedIn){
				console.log(response);
				let email = response.account.email;
				console.log(email);
				document.getElementById("emailDisplay").innerHTML = email;
			}
		}
	}
	request.open("GET", "rest/sessions/" + sessionID, false);
	request.send();
}

function addConfigurations() {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.status === 200 && this.readyState === 4) {
			let response = JSON.parse(this.responseText);
			let cars = new Map();
			for (const i in response) {
				const configuration = response[i];
				let carId = configuration.car.id;
				if (cars.has(carId)) {
					cars.get(carId).push(configuration);
				} else {
					cars.set(carId, [configuration]);
				}
			}
			console.log(cars)
			for (const i in response) {
				const configuration = response[i];
				console.log(configuration);
				let id = Number(cars.get(configuration.car.id).indexOf(configuration)) + 1;
				document.getElementById("configs").innerHTML += `<li> ${configuration.car.make} ${configuration.car.model} ` + id + `
					<button class="ConfigButton" onClick="location.href = 'configuration.html?config=${configuration.id}'"> Open</button>
				</li>`
			}
		}
	}
	request.open("GET", "rest/configurations/" + getSessionId(), true);
	request.setRequestHeader("Accept", "application/json");
	request.send();
}

function empCheck() {
	if (employeeCheck()) {
		document.getElementById("add").innerHTML = "<button class=\"UIbutton\" id=\"moveToAdd\" onclick=\"addCarPage()\"> Go to add car page</button>";
	}
}