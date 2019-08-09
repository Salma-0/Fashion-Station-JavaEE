/**
 * 
 */

/*$(document).ready(function(){ 
    	  $('#selectFilter').on('change', function (e) {
    		    var valueSelected = this.value;
    		    if(valueSelected == "date"){
    		    	$('#argument').attr('placeholder', 'yyyy-MM-dd');
    		    }else{
    		    	$('#argument').attr('placeholder', '');

    		    }
    		});
    	});
  */
 

 function gotoCustomer(username){
	
	 var redirect="http://localhost:8080/FashionStation/ShowCustomerDetailsServlet?username="+username;
	    window.open(redirect,"_self");
 }
 
 
 function showItems(orderID){
	 var redirect="http://localhost:8080/FashionStation/ShowOrderServlet?orderID="+orderID;
	    window.open(redirect,"_self");
 }
 
