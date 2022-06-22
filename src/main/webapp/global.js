function sessionId() {
	console.log("sup");
	let cookieMap = getCookies();
	console.log(cookieMap);
	if (!cookieMap.has("sessionId") || !sessionValid()) setSessionId();
}

function setSessionId() {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			console.log(response);
			let expiration = new Date(response.expiration);
			document.cookie = "sessionId=" + response.sessionId + ";expires=" + expiration.toUTCString();
		}
	};
	request.open("GET", "http://localhost:8080/betterbe_3/rest/sessions", false);
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
	console.log(session);
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		valid = this.status !== 500;
	}
	request.open("GET", "http://localhost:8080/betterbe_3/rest/sessions/" + session, false);
	request.setRequestHeader("Accept", "application/json");
	request.send()
	return valid;
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
			console.log(this.responseText);
			let response = JSON.parse(this.responseText);
			console.log(response)
			employee = (response.loggedIn && response.account.employee);
		}
	}
	request.open("GET", "http://localhost:8080/betterbe_3/rest/sessions/" + session, false);
	request.setRequestHeader("Accept", "application/json");
	request.send();
	console.log(employee)
	return employee;
}