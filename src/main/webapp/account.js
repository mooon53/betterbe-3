function onLoad() {
	sessionId();
	let session = getSessionId();
	console.log(session);
	let request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			let response = JSON.parse(this.responseText);
			if (response.loggedIn) {
				alert("account page would be loaded if it existed");
			} else {
				// alert("redirect");
				location.href = "login.html";
			}
		}
	}
	request.open("GET", "http://localhost:8080/betterbe_3/rest/sessions/" + session, true);
	request.setRequestHeader("Accept", "application/json");
	request.send()
}