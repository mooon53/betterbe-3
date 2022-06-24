function onLoad() {
	sessionId();
	let session = sessionValid();
	if (session.loggedIn) {
		location.href = "accountDetails.html";
	} else {
		location.href = "login.html";
	}
}