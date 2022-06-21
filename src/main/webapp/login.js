function onLoad() {let container = document.getElementById('container');}

function login() {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function () {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			if(response.login === 'true') {
				document.location = "/betterbe_3";
			} else {
				alert("wrong password");
			}
			document.cookie = response.session_id;
		}
	}
	let email = document.getElementById('email').value;
	let password = stringToHashConversion(document.getElementById('password').value);
	let response = {email, password};
	let responseString = JSON.stringify(response);
	request.open("POST", "http://localhost:8080/betterbe_3/rest/account", true);
	request.setRequestHeader("Accept", "application/json");
	request.setRequestHeader("Content-Type", "application/json")
	request.send(responseString);
}

function signUp() {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function () {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			if(response.signup === 'true') {
				document.location = "/betterbe_3/login.html";
				alert("account was added, you now can login");
			} else {
				alert("something went wrong, account did not register");
			}
			document.cookie = response.session_id;
		}
	}
	let email = document.getElementById('signupEmail').value;
	let password = stringToHashConversion(document.getElementById('signupPassword').value);
	let code = document.getElementById('empCode').value;
	let response = {email, password, code};
	let responseString = JSON.stringify(response);
	request.open("POST", "http://localhost:8080/betterbe_3/rest/signup", true);
	request.setRequestHeader("Accept", "application/json");
	request.setRequestHeader("Content-Type", "application/json")
	request.send(responseString);

}
function stringToHashConversion(string) {
	let hashVal = 0;
	if (string.length === 0) {
		return hashVal;
	}
	let char;
	for (i = 0; i < string.length; i++) {
		char = string.charCodeAt(i);
		hashVal = ((hashVal << 5) - hashVal) + char;
		hashVal = hashVal & hashVal;
	}
	return String(hashVal);
}
