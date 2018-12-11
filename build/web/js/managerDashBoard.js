/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getRooms(type)
{
    var http = new XMLHttpRequest();
    var url="getRoom?roomType="+type;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=JSON.parse(http.responseText);
                    console.log(isValid);
                    document.getElementById("priceInput"+type).placeholder=isValid.approvedPrice.toFixed(2);
                    document.getElementById("priceInput"+type).value="";
                    document.getElementById("oldPrice"+type).innerHTML=isValid.approvedPrice.toFixed(2);
                    document.getElementById("newPrice"+type).innerHTML=isValid.price.toFixed(2);
                    document.getElementById("oldQuantity"+type).innerHTML=isValid.approvedQuantity;
                    document.getElementById("newQuantity"+type).innerHTML=isValid.quantity;

            }
        }
    http.send();
}

function approvePrice(type)
{
    var http = new XMLHttpRequest();
    var url="approvePrice?roomType="+type;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=http.responseText;
                if(isValid!="false")
                {
                    //alert("Price Updated");
                    getRooms(type);
                }
                else{ alert("Some Error Occured")}
            }
        }
    http.send();
}

function updateApprovedPrice(type,price)
{
   var http = new XMLHttpRequest();
    var url="updateApprovedPrice?roomType="+type+"&price="+price;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=http.responseText;
                if(isValid!="false")
                {
                    //alert("Price Updated");
                    getRooms(type);
                }
                else{ alert("Some Error Occured")}
            }
        }
    http.send();
}

function approveQuantity(type)
{
        var http = new XMLHttpRequest();
    var url="approveQuantity?roomType="+type;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=http.responseText;
                if(isValid!="false")
                {
                    //alert("Price Updated");
                    getRooms(type);
                }
                else{ alert("Some Error Occured")}
            }
        }
    http.send();

}