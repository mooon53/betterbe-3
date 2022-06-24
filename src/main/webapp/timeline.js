let carId;

function onload() {
	sessionId();
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
			for (let i in response) {
				let line = response[i];
				console.log(line);
				if (line.end_date === null){
					line.end_date = "-";
				}
				table.innerHTML += `<tr>
                            <td>${line.date}</td>
                            <td>${line.end_date}</td>
                            <td>${line.basePrice}</td>
                            <td>${line.optionType}</td>
                            <td>${line.optionValue}</td>
                            <td>${line.optionPrice}</td>
                            <td><button onclick="removeOption(${line.id})">remove option</button></td>
                        </tr>`;
			}
		}
	}
	getRequest.open("GET", "http://localhost:8080/betterbe_3/rest/timeline/"+carId, true);
	getRequest.setRequestHeader("Accept", "application/json");
	getRequest.send();
}

function addOptionToCar() {

}

function removeOption(id) {
	let request = new XMLHttpRequest();
	let string = {id}
	console.log(id);
	console.log(string);
	let responseString = JSON.stringify(string);
	console.log(responseString);
	request.open("POST", "http://localhost:8080/betterbe_3/rest/edit", true);
	request.setRequestHeader("Content-Type", "application/json");
	request.setRequestHeader("Accept", "application/json");
	request.send(responseString);
}