/**
 * 
 */

$(() =>{
		
		loadReimbursements();
		
	})
	
	
	
	function loadReimbursements() {
		
		console.log('inside reimburse');
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			
			if(xhr.readyState == 4 && xhr.status == 200) {
				
				let reimbursements = JSON.parse(xhr.responseText);

				for(let reims of reimbursements) {
					
					console.log(reims);
					reimburseLists(reims);
					
				}
				
			}
			
		}
		
		xhr.open("GET", "manager", true);
		xhr.send();
		
	}
	
	function refreshStatus() {
		
		console.log('inside refresh');
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			
			if(xhr.readyState == 4 && xhr.status == 200) {
				
				let reimbursements = JSON.parse(xhr.responseText);
				console.log('1', xhr.responseText);
				for(let reims of reimbursements) {
					
					
 					console.log(reims);
					reimburseLists(reims);
					
					
				}
				$("#reimInfo tr").remove(); 
				loadReimbursements();
				
			}
			
		}
		
		xhr.open("GET", "manager", true);
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
		console.log(submitTime);
		var info = $(`
				<tr>
					<th>${reims.id}</th>
					<td>${reims.amount}</td>
					<td>${submitTime}</td>
				
					<td>${reims.description}</td>
					<td>${reims.author}</td>
					<td id = "status${reims.id}">${status}</td>
					<td>${type}</td>
					<td>
						<button class = "button1" onclick = "updateStatus(${reims.id}, 1)">Approve</button>
						<button class = "button2" onclick = "updateStatus(${reims.id}, 2)">Deny</button>
					</td>
				</tr>`
				)
		
		$('#reimInfo').append(info);
		
	}
	
	function updateStatus(r_id, status) {
		
		var stat = {
				
			id: r_id,
			status_id: status
				
		}
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			
			if(xhr.readyState == 4 && xhr.status == 200) {
				
				$(`status${r_id}`).html(status);
				refreshStatus();
				
			}
			
		}
		
		xhr.open("PUT", "login", true);
		xhr.setRequestHeader("Content-type", "application/json");
		var toSend = JSON.stringify(stat);
		xhr.send(toSend);
		
	}

