function sessionId() {
	console.log("sup");
	let cookieMap = getCookies();
	console.log(cookieMap);
	if (!cookieMap.has("sessionId")) {
		setSessionId();
	}
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
	request.open("GET", "http://localhost:8080/betterbe_3/rest/session", true);
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