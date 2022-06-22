function onLoad() {
	sessionId();
	let container = document.getElementById("container");
}

function login() {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			let response = JSON.parse(this.responseText);
			if (response.success) alert("Correct :)"); //location.href = "/account.html";
			else alert("Wrong username or password :(");
		}
	};
	let email = document.getElementById("email").value;
	let password = stringToHashConversion(document.getElementById("password").value);
	let sessionId = getSessionId();
	console.log(password);
	console.log(email);
	let responseString = JSON.stringify({sessionId, email, password});
	request.open("POST", "http://localhost:8080/betterbe_3/rest/account", true);
	request.setRequestHeader("Accept", "application/json");
	request.setRequestHeader("Content-Type", "application/json");
	console.log(responseString);
	request.send(responseString);
}

function signUp() {
	let getRequest = new XMLHttpRequest();
	getRequest.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			document.cookie = response.session_id;
		}
	};
	let email = document.getElementById("signupEmail").value;
	let password = stringToHashConversion(document.getElementById("signupPassword").value);
	console.log(password);
	console.log(email);
	let response = {email, password};
	let responseString = JSON.stringify(response);
	getRequest.open("POST", "http://localhost:8080/betterbe_3/rest/signup", true);
	getRequest.setRequestHeader("Accept", "application/json");
	getRequest.setRequestHeader("Content-Type", "application/json");
	console.log(response);
	console.log(responseString);
	getRequest.send(responseString);

}

function stringToHashConversion(string) {
	let hashVal = 0;
	if (string.length == 0) return hashVal;
	for (i = 0; i < string.length; i++) {
		char = string.charCodeAt(i);
		hashVal = ((hashVal << 5) - hashVal) + char;
		hashVal = hashVal & hashVal;
	}
	return hashVal;
}