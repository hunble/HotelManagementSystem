/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addRoom(type)
{
    var http = new XMLHttpRequest();
    var url="addRoom?roomType="+type;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=http.responseText;
                if(isValid!="false")
                {
                    alert("A new room has been added");
                }
                else{ alert("Some Error Occured")}
            }
        }
    http.send();
}
function removeRoom(type)
{
    var http = new XMLHttpRequest();
    var url="deleteRoom?roomType="+type;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=http.responseText;
                if(isValid!="false")
                {
                    alert("Removed");
                }
                else{ alert("Some Error Occured")}
            }
        }
    http.send();
}

function unsetActive(type)
{

    currentRoomType=type;
    loadAmenities();
    var node=document.getElementById("adminMain");
    node.className="";
}

function updatePrice(type,price)
{
    var http = new XMLHttpRequest();
    var url="updatePrice?roomType="+type+"&price="+price;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=http.responseText;
                if(isValid!="false")
                {
                    alert("Price Updated");
                }
                else{ alert("Some Error Occured")}
            }
        }
    http.send();
    
}

function upload(event,type)
{
    event.preventDefault();
    var ParentDiv= document.getElementById("currentImages");
    ParentDiv.innerHTML="Wait Please!!!";
    var form = document.getElementById('file-form');
    var fileSelect = document.getElementById('file-select');
    var uploadButton = document.getElementById('upload-button');
    // Get the selected files from the input.
    var files = fileSelect.files;

    // Create a new FormData object.
    var formData = new FormData();

    // Loop through each of the selected files.
    for (var i = 0; i < files.length; i++) {
      var file = files[i];

      // Check the file type.
      if (!file.type.match('image.*')) {
        continue;
      }

      // Add the file to the request.
      formData.append('photos[]', file, file.name);

      // Set up the request.
        var xhr = new XMLHttpRequest();

        // Open the connection.
    xhr.open('POST', 'uploadRoomPicture?roomType='+type, true);

    // Set up a handler for when the request finishes.
    xhr.onload = function () {
                if(xhr.readyState == 4 && xhr.status == 200) {
                    var isValid=xhr.responseText;
                    if(isValid!="false")
                    {
                        loadImages(currentRoomType);
                    }
                    else{ alert("Some Error Occured Uploading Image")}
                }
    };

    // Send the Data.
    xhr.send(formData);

    }


}

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
                    document.getElementById("priceInput"+type).placeholder=isValid.price.toFixed(2);
            }
        }
    http.send();
}

function loadImages(type)
{
    var http = new XMLHttpRequest();
    var url="getImages?roomType="+type;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                var isValid=JSON.parse(http.responseText);
                    console.log(isValid);
                    var ParentDiv= document.getElementById("currentImages");
                    ParentDiv.innerHTML="";
                    for (var key in isValid) {
                      if (isValid.hasOwnProperty(key)) {
                        console.log(key + " -> " + isValid[key]);
                        var listItem=document.createElement('li');                  
                        var image=document.createElement("img");
                        var removeButton=document.createElement("button");
                        removeButton.setAttribute("class","btn btn-danger");
                        removeButton.innerHTML="x";
                        removeButton.setAttribute("onclick","removePic(this)");
                        removeButton.setAttribute("fileAdd",isValid[key]);
                        image.class="img-responsive";
                        image.src=isValid[key];
                        image.height="100";
                        image.width="150";
                        listItem.style.display="inline-block";
                        listItem.style.padding="2px";
                        listItem.appendChild(image);
                        listItem.appendChild(removeButton);
                        ParentDiv.appendChild(listItem);
                    }
                }
            }
        };
    http.send();
    
}

function removePic(node)
{
    var http = new XMLHttpRequest();
    var url = "deleteFile?fileName="+node.getAttribute("fileAdd");
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=JSON.parse(http.responseText);
                if(isValid!="false")
                {
                    loadImages(currentRoomType);
                }
                else{ alert("Something Went Wrong!")}
            }
        }
    http.send();
}

function updateAmenities()
{
    var amesArra=[];var k=0;
    var http = new XMLHttpRequest();
    var url = "updateFeature?roomType="+currentRoomType;
    for(var i=0;i<16;i++)
    {
       var abc=document.getElementsByName('amen[]')[i].checked;
       if(abc)
       {   
           amesArra[k++]=i+1;
       }
    }
       var JsonAmen = JSON.stringify(amesArra);
       console.log(JsonAmen);
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=http.responseText;
                if(isValid!="false")
                {
                    alert("New Features added")
                }
                else{ alert("Incorrect UserName or Password!")}
            }
        }
    http.send(JsonAmen);
}

function loadAmenities()
{
    for(var i=0;i<16;i++)document.getElementsByName('amen[]')[i].checked=false;
    var http = new XMLHttpRequest();
    var url = "getFeatures?roomType="+currentRoomType;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=JSON.parse(http.responseText);
                if(isValid!="false")
                {
                    for (var key in isValid) {
                    if (isValid.hasOwnProperty(key)) {
                        console.log(key + " -> " + isValid[key]);
                        document.getElementsByName('amen[]')[isValid[key]-1].checked=true;                       

                    }}
                }
                else{ alert("Incorrect UserName or Password!")}
            }
        }
    http.send();
}
