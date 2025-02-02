function onLoad() {
	sessionId();
	changeLogInButton();
	let nextPage = "configurator.html?carID=";
	let buttonText = "Lease";
	if (employeeCheck()) {
		nextPage = "timeline.html?carId=";
		buttonText = "Edit";
	}
	let getRequest = new XMLHttpRequest(); //Create a new http request to get the cars in our catalogue
	getRequest.onreadystatechange = function() { //When any type of response is received, execute this script
		if (this.readyState === 4 && this.status === 200) { //If the response has been fully received, and the server says the request was processed ok
			let response = JSON.parse(this.responseText);
			let html = document.getElementById("product-container");
			html.innerHTML = "";
			for (const car of response) {
				let info = `${car.make} ${car.model}`;
				if (!car.available) info += " (UNAVAILABLE)";
				html.innerHTML += `<div class="card">
									<div class="title">
										` + info + `
									</div>
									<div class="image">
										<img src="images/cars/${car.id}.png" onerror="if (this.src !== 'images/cars/default.png') this.src = 'images/cars/default.png';">
									</div>
									<div class="text">
										${car.size} ${car.type}
									</div>
									<div>
										<button class="lease-button" onclick="location.href='` + nextPage + `${car.id}'">
											` + buttonText + `
										</button>
									</div>
								</div>`;
			}
		}
	};
	getRequest.open("GET", "rest/cars", true); //open the request, set the type and uri
	getRequest.setRequestHeader("Accept", "application/json"); //Tell the server we're accepting JSON responses only
	getRequest.setRequestHeader("sessionId", getSessionId());
	getRequest.send(); //Send the request
}
