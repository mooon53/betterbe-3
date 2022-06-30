let sessionID = getSessionId();
let acc;
let response;
function onLoad() {
	sessionId();
	changeLogInButton();
	let session = sessionValid();
	if (!session.loggedIn) {
		location.href = "login.html";
	}
}

function addCarPage(){
	location.href = "addCar.html";
}
function checkoutPage(){
	location.href = "checkout.html";
}

function logout(){
	alert("logged out successfully!")
	location.href = "./";
}

function displayEmail(){
	let request = new XMLHttpRequest();
	request.onreadystatechange = function () {
		if (this.readyState === 4 && this.status === 200) {
			response = JSON.parse(this.responseText);
			if (sessionValid().valid && response.loggedIn){
				console.log(response);
				let email = response.account.email;
				console.log(email);
				document.getElementById("emailDisplay").innerHTML = email;
			}
		}
	}
	request.open("GET", url + "/sessions/" + sessionID, false);
	request.send();
	document.getElementById("moveToAdd").style.visibility = 'hidden';
	document.getElementById("moveToCheckout").style.visibility = 'hidden';

}

function empCheck(){
	if(employeeCheck()){
		document.getElementById("moveToAdd").style.visibility = 'visible';
	}
	else{
		document.getElementById("moveToCheckout").style.visibility = 'visible';

	}
}