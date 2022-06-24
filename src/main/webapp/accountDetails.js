let sessionID = getSessionId();
let acc;
let responsse;
function onLoad() {
	sessionId();
}

function addCarPage(){
	location.href = "addCar.html";
}
function checkoutPage(){
	location.href = "checkout.html";
}

function displayEmail(){
	let request = new XMLHttpRequest();
	request.onreadystatechange = function () {
		if (this.readyState === 4 && this.status === 200) {
			responsse = this.response;
			acc = JSON.parse(responsse);
		}
	}
	request.open("GET", "http://localhost:8080/betterbe_3/rest/sessions/" + sessionID, false);
	request.send();
	if(sessionValid()){
		console.log(responsse);
		let email = acc.account.email;
		console.log(email);
		document.getElementById("emailDisplay").innerHTML = email;
	}
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