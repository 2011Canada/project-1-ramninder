/**
 * 
 */
window.onload = function() {
	let loginButton = document.getElementById("login");
	loginButton.addEventListener('click',  ()=>{

		 loadLoginView();
	});

	let logoutButton = document.getElementById("logout");
	logoutButton.addEventListener('click',  ()=>{

		 logoutSession();
	});
	
	loadHomeView();

}

function logoutSession() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			
			document.getElementById("view").innerhtml=this.responseText
			
			$('#view').html(xhr.responseText);
			
		}
		
	}
	
xhr.open("GET", "logout", true);
	xhr.send();	
	
}

function loadHomeView() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			//returning response as html page 
			//document.getElementById("view").innerhtml = this.responseText
			$('#view').html(xhr.responseText);
			
		}
		
	}
	
	xhr.open("GET", "home.view", true);
	xhr.send();
	
}

function loadLoginView() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			//do things with response
			document.getElementById("view").innerHTML = this.responseText;
			
			let signInButton = document.getElementById("signin");
			
			signInButton.addEventListener('click',()=>{
				
				login(event);
			})
		
			
		}
		
	}
	
	xhr.open("GET", "login.view", false);
	xhr.send();
	
}

function login(event) {

	event.preventDefault();
	
	let username = document.getElementById("username").value
	let password = document.getElementById("password").value
		
	console.log("i am login")
	let user = {
			
		username: username,
		password: password
	
	};
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			//document.getElementById("view").outerHTML = this.responseText;
		window.location = this.responseURL
			//$('#view').html(xhr.responseText);
//			loadReimbursements();
			//loadVIEW();
			
		}
		
	}
	
	xhr.open("POST", "login", true);
	xhr.setRequestHeader("Content-type", "application/json");
	var toSend = JSON.stringify(user);
	console.log("send in login:" + toSend);
	xhr.send(toSend);
	
	
}

function loadVIEW() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {

		document.getElementById("view").innerHTML = this.responseText;
						
			
		}
		
	}
	
	xhr.open("GET", "create", true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send();
	
}



// function loadManagerView () {
	
// 	var xhr = new XMLHttpRequest();
// 	xhr.onreadystatechange = function () {
		
// 		if(xhr.readyState == 4 && xhr.status == 200) {
			
// 			$('#view').html(xhr.responseText);
// 			console.log("inside loadManagerView");
			
// 		}
		
// 	}
	
// 	xhr.open("GET", "manager.view", true);
// 	xhr.send();
	
// }

// function loadEmployeeView () {
	
// 	var xhr = new XMLHttpRequest();
// 	xhr.onreadystatechange = function () {
		
// 		if(xhr.readyState == 4 && xhr.status == 200) {
			
// 			$('#view').html(xhr.responseText);
			
// 		}
		
// 	}
	
// 	xhr.open("GET", "employee.view", true);
// 	xhr.send();
	
// }