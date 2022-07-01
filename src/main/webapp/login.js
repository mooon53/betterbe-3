function onLoad() {
    sessionId();
    changeLogInButton();
    let container = document.getElementById("container");
}

function login() {
    let request = new XMLHttpRequest();
    request.onreadystatechange = function () {
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
    request.open("POST", "rest/account", true);
    request.setRequestHeader("Accept", "application/json");
    request.setRequestHeader("Content-Type", "application/json");
    console.log(responseString);
    request.send(responseString);
    location.href = "accountDetails.html"
}

function signUp() {
    let request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            response = JSON.parse(this.responseText);

        }
    };
    let email = document.getElementById('signupEmail').value;
    let password = stringToHashConversion(document.getElementById('signupPassword').value);
    let code = document.getElementById('empCode').value;
    let response;
    if (code !== null) {
        response = {email, password, code};
    } else {
        response = {email, password};
    }
    let responseString = JSON.stringify(response);
    request.open("POST", "rest/signup", true);
    request.setRequestHeader("Accept", "application/json");

    request.setRequestHeader("Content-Type", "application/json");
    console.log(response);
    console.log(responseString);
    request.send(responseString);

}

function stringToHashConversion(string) {
    let hashVal = 0;
    if (string.length === 0) return hashVal;
    for (let i = 0; i < string.length; i++) {
        const char = string.charCodeAt(i);
        hashVal = ((hashVal << 5) - hashVal) + char;
        hashVal = hashVal & hashVal;
    }
    return String(hashVal);
}