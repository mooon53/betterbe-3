function sessionId() {
	console.log("sup");
	let cookies = document.cookie.split(";");
	let cookieMap = new Map();
	for (let i in cookies) {
		cookies[i] = cookies[i].replaceAll(" ", "");
		let split = cookies[i].split("=");
		cookieMap.set(split[0], split[1]);
	}
	let sessionId = cookieMap.get("sessionId");
	let expiration = cookieMap.get("expiration");
	let now = new Date();
	console.log(cookies);
	console.log(cookieMap);
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			let response = JSON.parse(this.responseText);
			console.log(response);
			document.cookie = "sessionId=" + response.sessionId;
			let expirationTime = now.getTime() + 3600000;
			document.cookie = "expiration=" + expirationTime;
		}
	};
	request.open("POST", "http://localhost:8080/betterbe_3/rest/session", true);
	request.setRequestHeader("Accept", "application/json");
	request.setRequestHeader("Content-Type", "application/json")
	if (now.getTime() > expiration) {
		let expired = true;
		request.send(JSON.stringify(expired, sessionId));
	}
}