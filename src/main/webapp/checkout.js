function onLoad() {
	changeLogInButton();
	sessionId();
	let optionsList = document.getElementById("options");
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.status === 200 && this.readyState === 4) {
			let response = JSON.parse(this.responseText);
			loadCarInfo(response.carId);
			let options = response.options;
			for (const option of options) {
				let html = `<ul>${option.value}</ul>`
				optionsList.innerHTML += html;
			}
		}
	}
	request.open("GET", "http://localhost:8080/betterbe_3/rest/cart", true);
	request.setRequestHeader("Content-Type", "text/plain");
	request.setRequestHeader("Accept", "application/json");
	let json = {"sessionId":getSessionId().toString()};
	request.send(JSON.stringify(json));
}

function loadCarInfo(carId) {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			document.getElementById("carName").innerText = `${response.car.make} ${response.car.model}`;
		}
	}
	request.open("GET", "http://localhost:8080/betterbe_3/rest/cars/" + carId, true);
	request.setRequestHeader("Accept", "application/json");
	request.send();
}