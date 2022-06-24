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
			let table = `<body onload="onLoad()">
			<main class = "header">
			
				<div class="menu_nav">
					<a href="/configurator.html">Lease</a>
					<a href="#about">About</a>
					<a href="#contact">Contact</a>
					<a href="account.html">My Account</a>
			
					<div class="image-logo" onclick="location.href='/betterbe_3'">
						<img src="https://www.betterbe.com/wp-content/themes/betterbe/img/logo.svg"/>
					</div>
			
				</div>
			
				<hr class="line">
			</main>
			<div id="table" class="table"><table><thead>
                    <tr>
                            <th>Start Date</th>
                            <th>End date</th>
                            <th>Base Price</th>
                            <th>Option Type</th>
                            <th>Value</th>
                            <th>Effect</th>
                            <th>Remove option</th>
                    </tr></thead><tbody>`;

			for (let i in response) {
				let line = response[i];
				console.log(line);
				if (line.end_date === null){
					line.end_date = "-";
				}
				table += `<tr>
                            <td>${line.date}</td>
                            <td>${line.end_date}</td>
                            <td>${line.basePrice}</td>
                            <td>${line.optionType}</td>
                            <td>${line.optionValue}</td>
                            <td>${line.optionPrice}</td>
                            <td><button onclick="removeOption(${line.id})">remove option</button></td>
                        </tr>`;
			}
			table += `</tbody></table></div></div>`;
			document.getElementById("timeline").innerHTML += table;
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