var url;

function sessionId() {
	url = new URL(location.href).origin + "/betterbe_3/rest";
	let cookieMap = getCookies();
	if (!cookieMap.has("sessionId") || !sessionValid().valid) setSessionId();
}

function changeLogInButton() {
	if (sessionValid().loggedIn) document.getElementById("logIn").innerText = "My account";
}

function setSessionId() {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			let expiration = new Date(response.expiration);
			document.cookie = "sessionId=" + response.sessionId + ";expires=" + expiration.toUTCString();
		}
	};
	request.open("GET", url + "/sessions", false);
	request.setRequestHeader("Accept", "application/json");
	request.setRequestHeader("Content-Type", "application/json")
	request.send();
}

function getSessionId() {
	let cookieMap = getCookies();
	if (cookieMap.has("sessionId")) {
		return cookieMap.get("sessionId");
	} else {
		setSessionId();
		return getSessionId();
	}
}

function sessionValid() {
	let valid = false;
	let session = getSessionId();
	let request = new XMLHttpRequest();
	let result = {};
	request.onreadystatechange = function() {
		valid = this.status !== 500;
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			result["loggedIn"] = response.loggedIn;
			result["sessionId"] = response.sessionId;
			result["account"] = response.account;
		}
	}
	console.log(url);
	console.log(url + "/sessions/" + session);
	request.open("GET", url + "/sessions/" + session, false);
	request.setRequestHeader("Accept", "application/json");
	request.send()
	result["valid"] = valid;
	return result;
}

function getCookies() {
	let cookies = document.cookie.split(";");
	let cookieMap = new Map();
	for (let i in cookies) {
		cookies[i] = cookies[i].replaceAll(" ", "");
		let split = cookies[i].split("=");
		cookieMap.set(split[0], split[1]);
	}
	return cookieMap;
}

function nonEmployeeDestroyer() {
	if (!employeeCheck()) {
		document.getElementsByTagName("body")[0].innerHTML = "";
		alert("Sorry, this page is for employees only, you will be redirected to the home page");
		location.href = "./";
	}
}

function employeeCheck() {
	let session = getSessionId();
	let request = new XMLHttpRequest();
	let employee = false;
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			employee = (response.loggedIn && response.account.employee);
		}
	}
	request.open("GET", url + "/sessions/" + session, false);
	request.setRequestHeader("Accept", "application/json");
	request.send();
	return employee;
}
