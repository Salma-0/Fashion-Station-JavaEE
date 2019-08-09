/**
 * 
 */

function updateCategory(categoryID){
	 var redirect="http://localhost:8080/FashionStation/UpdateCategoryServlet?categoryID="+categoryID;
	    window.open(redirect,"_self");
}

function deleteCategory(categoryID){
	var confirmed = confirm("delete this category? (If you confirm deletion, the products belong to this category will be deleted as well)");
	if(confirmed){
	 var redirect="http://localhost:8080/FashionStation/AddCategoryServlet?categoryID="+categoryID;
	    window.open(redirect,"_self");
	}else
		return;
}


function uploadImage(){
	var confirmed  =confirm("The current image will be replaced by the newly uploaded one.");
	if(confirmed){
		document.getElementByID("uploadForm").submit();	
	}else 
		return;
}