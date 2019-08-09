/**
 * 
 */

function gotoProductPage(productID){
	var redirect="http://localhost:8080/FashionStation/ChooseProductServlet?linkID="+productID;
    window.open(redirect,"_self");
}

function updateProduct(productID){
	var redirect="http://localhost:8080/FashionStation/UpdateProductServlet?productID="+productID;
    window.open(redirect,"_self");
}

function deleteImage(image_id, product_id){
	var confirmed = confirm("Delete this Image?");
	if(confirmed){
	var redirect="http://localhost:8080/FashionStation/AddImageServlet?productID="+product_id+"&imageID="+image_id;
    window.open(redirect,"_self");
	}
	else 
		return;
}

function deleteProduct(productID){
	var confirmed = confirm("Delete this product?");
	if(confirmed){
	var redirect="http://localhost:8080/FashionStation/FilterProductServlet?productID="+productID;
    window.open(redirect,"_self");
	}else 
		return;
}