function onLoad() {
	sessionId();
	notLoggedInRedirecter();
	changeLogInButton();
	let optionsList = document.getElementById("options");
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.status === 200 && this.readyState === 4) {
			let response = JSON.parse(this.responseText);
			document.getElementById("carName").innerText = response.car.make + " " + response.car.model + " €" + response.car.price.toFixed(2);
			let options = response.options;
			let totalPrice = response.car.price;
			for (const option of options) {
				let html = `<ul>${option.value} €` + (option.price).toFixed(2) + `</ul>`
				optionsList.innerHTML += html;
				totalPrice += option.price;
			}
			document.getElementById("totalPrice").innerText = "€" + totalPrice.toFixed(2);
		}
	}
	let localUrl = new URL(location.href); //Get the current url
	let searchParams = localUrl.searchParams; //Get the search parameters (?carID=<search parameter>)
	let configID = searchParams.get("config");
	request.open("GET", "rest/configurations/" + getSessionId() + "/" + configID, true);
	request.setRequestHeader("Content-Type", "text/plain");
	request.setRequestHeader("Accept", "application/json");
	request.send();
}

function loadCarInfo(carId) {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			document.getElementById("carName").innerText = `${response.car.make} ${response.car.model}`;
		}
	}
	request.open("GET", "rest/cars/" + carId, true);
	request.setRequestHeader("Accept", "application/json");
	request.send();
}