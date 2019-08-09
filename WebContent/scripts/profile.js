/**
 * 
 */

function editInformation(username){
    	 var inputs=document.getElementsByTagName('input');
    	 for(i=1;i<5;i++){
    	     inputs[i].readOnly=false;
    	     inputs[i].style.border = "1px solid red";
    	     var btn = document.getElementById("update-btn");
    	     btn.style.display = "inline";
    	 }   
     }
     
     function editShipment(){
    	 var inputs=document.getElementsByTagName('input');
    	 document.getElementById('notes').readOnly = false;
    	 document.getElementById('notes').style.border = "1px solid red";

    	 for(i=5;i<inputs.length;i++){
    	     inputs[i].readOnly=false;
    	     inputs[i].style.border = "1px solid red";
    	     var btn = document.getElementById("update-ship");
    	     btn.style.display = "inline";
    	 }   
     }
     
     
    function update(){
    	 var inputs=document.getElementsByTagName('input');
    	
    	 for(i=1;i<5;i++){
    	     inputs[i].readOnly=true;
    	     inputs[i].style.border = "1px solid white";
    	     var btn = document.getElementById("update-btn");
    	     btn.style.display = "none";
    	 }
    }
     
    function updateShip(){
   	 var inputs=document.getElementsByTagName('input');
   	 document.getElementById('notes').readOnly = true;
	 document.getElementById('notes').style.border = "1px solid white";

   	 for(i=5;i<inputs.length;i++){
   	     inputs[i].readOnly=true;
   	     inputs[i].style.border = "1px solid white";
   	     var btn = document.getElementById("update-ship");
   	     btn.style.display = "none";
   	 }
   }