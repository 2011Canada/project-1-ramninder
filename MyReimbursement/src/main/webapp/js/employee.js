/**
 * 
 */			
		window.onload = function(){
		let submitReimbursement = document.getElementById("submitReimbusrement");
		submitReimbursement.addEventListener('click',()=>{
			console.log("you clicked me");
			createReim();
			location.reload(true);
		});
	
		loadReimbursements();
	}
		

	 function createReim() {
	
		/* REIMB_AMOUNT, REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID) */
		let time = new Date().getTime()
		let amount = document.getElementById("reimb_amount").value
		let description = document.getElementById("reimb_desc").value
		let type = document.getElementById("dataType").value
		
		
		let obj = {
			
			amount: amount,
			description:description,
			submitted: time,
			resolved:null,
			author:2,
			resolver:1,
			status_id:3,
			type_id: type
			
			
			
		}


		console.log(obj);

		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			
			if(xhr.readyState == 4 && xhr.status == 200) {
				console.log("inside create");
				console.log("create", toSend);
				loadEmployeeReims();
				
			}
			
		}
		
		xhr.open("POST", "create", false);
		xhr.setRequestHeader("Content-type", "application/json");
		var toSend = JSON.stringify(obj);
		xhr.send(toSend);	
	}
		
		
		
	

	function loadReimbursements() {
		
		console.log('inside reimburse');
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			
			if(xhr.readyState == 4 && xhr.status == 200) {
				
	//			$('#view').html(xhr.responseText);
				let reimbursements = JSON.parse(xhr.responseText);
				console.log('1', xhr.responseText);
				for(let reims of reimbursements) {
					
					console.log(reims);
					reimburseLists(reims);
					
				}
				
			}
			
		}
		
		xhr.open("GET", "create", true);
		xhr.send();
		
	}
	
	function loadEmployeeReims() {
		
		console.log('inside employee Reimburse');
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			
			if(xhr.readyState == 4 && xhr.status == 200) {
				
				let reimbursements = JSON.parse(xhr.responseText);
				/* console.log("reimb", reimbursements); */
				let r = reimbursements.pop();
 				/* console.log('employee: ', r); */
 				reimburseLists(r);
				
			}
			
		}
		
		xhr.open("GET", "employee", true);
		xhr.send();
		
	}
	
	
	function reimburseLists(reims){
		
		let status = "";
		let type = "";
		
		switch(reims.status_id) {
		    case 1:
		        status = "Approved";
		        break;
		        
		    case 2:
		        status = "Denied";
		        break;
		    
		    case 3:
		        status = "Pending";
		        break;
		     
		    default:
		    	break;
		
		}

		 switch(reims.type_id) {
		 
		    case 1:
		        type = "Other";
		        break;
		        
		    case 2:
		        type = "Lodging";
		        break;
		    
		    case 3:
		        type = "Food";
		        break;
		    
		    case 4:
		        type = "Travel";
		        break;
		        
		    default:
		    	break;
		    
		 
		}
		var submitTime = new Date(reims.submitted);
		var info = $(`
				<tr>
					<th>${reims.id}</th>
					<td>${reims.amount}</td>
					<td>${submitTime}</td>
					
					<td>${reims.description}</td>
					<td>${reims.author}</td>
					<td>${status}</td>
					<td>${type}</td>
				</tr>`
				)
		
		$('#reimInfo').append(info);
		
	}


