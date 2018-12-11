/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var currentUser=0;
var currentlySelectedRoomType=0;
var currentUserName=""
var aStatus=0;

function Search()
{
    var http = new XMLHttpRequest();
    var url = "Search";
    var formData="{\"RoomID\":\""+
            document.getElementById("roomTypeSearch").value+"\",\"checkin\":\""+
            document.getElementById("checkInSearch").value+"\",\"checkout\":\""+
            document.getElementById("checkoutSearch").value+"\",\"sPrice\":\""+
            document.getElementById("minPrice").value+"\",\"ePrice\":\""+
            document.getElementById("maxPrice").value+"\"}";
            
    console.log(formData);
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=JSON.parse(http.responseText);
                console.log(isValid);
                var parent=document.getElementById('SearchReults');
                var listItem="RoomID\tBookedDate\tRemainingRoom\taprovedPrice\taprovedQuantity";          
                parent.innerHTML=listItem;
                var br=document.createElement("br");
                parent.appendChild(br);

                for (var key in isValid) {
                if(isValid!="-1")
                {
                    var JSONUser = JSON.parse(http.responseText);
                    RoomID=JSONUser[key].RoomID;
                    BookedDate=JSONUser[key].BookedDate;
                    RemainingRoom=JSONUser[key].RemainingRoom;
                    aprovedPrice=JSONUser[key].aprovedPrice;
                    aprovedQuantity=JSONUser[key].aprovedQuantity;
                    aStatus=JSONUser[key].aStatus;
                    var listItem=RoomID+"\t"+BookedDate+"\t"+RemainingRoom+"\t"+aprovedPrice+"\t"+aprovedQuantity;          
                    var node=document.createTextNode(listItem);
                    var br=document.createElement("br");
                    parent.appendChild(node);
                    parent.appendChild(br);
                }
                else{ alert("No Result Found!")}
            }
        }
    }
    http.send(formData);
}
function checkSession()
{
    var http = new XMLHttpRequest();
    var url = "getSessionUser";
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                //alert(http.responseText);
                var JSONUser = JSON.parse(http.responseText);
                console.log(JSONUser);
                if(null === JSONUser)
                {
                    status="";
                    loginCheck(status,"");
                }
                else
                {
                    status="kjfhdkjas";
                    currentUser=JSONUser.userID;
                    currentUserName=JSONUser.name;
                    aStatus=JSONUser.aStatus;
                    loginCheck(status,JSONUser.name);                    
                }
            }
        }
    http.send();    
}
function loginCheck(status,userName)
{
    var LoggedIn=status;
    if(LoggedIn!="")
    {
            document.getElementById('signoutTab').style.visibility='initial';
            document.getElementById('userTab').style.visibility='initial';
            var t = document.createTextNode(userName);
            
            var caret=document.createElement("span");
            caret.setAttribute("class","caret");
            document.getElementById('userTab').appendChild(t);
                        document.getElementById('userTab').appendChild(caret);
            document.getElementById("profileImg").src="assets/media/user.png";

            document.getElementById('logInTab').remove();
            document.getElementById('signUpTab').remove();
            console.log("Logged In");
            if(currentUserName=="Admin"&&window.location.href.substr(window.location.href.lastIndexOf('/'),window.location.href.length)!="/adminDashBoard.html")
            {
                console.log(window.location.href.substr(window.location.href.lastIndexOf('/'),window.location.href.length));
                var node=document.getElementById("adminMain");
                var newNode=document.createElement("li");
                var att=document.createElement("a");
                att.href="adminDashBoard.html";
                att.innerHTML="Administrator Home";
                newNode.appendChild(att);
                node.parentNode.insertBefore(newNode, node.nextSibling);
            }
            else if(currentUserName=="Manager"&&window.location.href.substr(window.location.href.lastIndexOf('/'),window.location.href.length)!="/managerDashBoard.html")
            {
                console.log(window.location.href.substr(window.location.href.lastIndexOf('/'),window.location.href.length));
                var node=document.getElementById("adminMain");
                var newNode=document.createElement("li");
                var att=document.createElement("a");
                att.href="managerDashBoard.html";
                att.innerHTML="Manager Dashboard";
                newNode.appendChild(att);
                node.parentNode.insertBefore(newNode, node.nextSibling);    
            }
            else
            {  
                    //loadRegistration();
    var http1 = new XMLHttpRequest();
    var url = "getUserImage";
    http1.open("GET", url, true);
    http1.setRequestHeader("Content-type", "multipart/form-data" );                
    http1.onreadystatechange = function() {//Call a function when the state changes.
            if(http1.readyState == 4 && http1.status == 200) {
                if(http1.responseText!=="-1")
                {
                    var isValid1=JSON.parse(http1.responseText);
                     for (var key in isValid1) {
                         document.getElementById("blah1").src=isValid1[key];
                         document.getElementById("profileImg").src=isValid1[key];
                     }
                 }
                }
        }
        }
    http1.send();
            
    }
    else
    {
            document.getElementById('userTab').style.visibility='hidden';
            document.getElementById('signoutTab').style.visibility='hidden';
    }
    
}
function loadRegistration()
{
    var http = new XMLHttpRequest();
        var url = "UserRegistration";
        http.open("GET", url, true);
        http.setRequestHeader("Content-type", "multipart/form-data" );                
        http.onreadystatechange = function() {//Call a function when the state changes.
                if(http.readyState == 4 && http.status == 200) {
                    var isValid=parseInt(http.responseText,10);
                        var regNode=document.getElementById("myReg");
                    if(isValid!="-1"){
                        var UserRegJSON = JSON.parse(http.responseText);
                        var table=document.createElement("table");
                            var tr=document.createElement("tr");
                            var th=document.createElement("th");
                            th.innerHTML="Booking Date";
                            tr.appendChild(th);
                            var th=document.createElement("th");
                            th.innerHTML="Room Type";
                            tr.appendChild(th);

                            table.appendChild(tr);

                        for(var i = 0; i < UserRegJSON.length; i++)
                        {
                            var th1=document.createElement("td");
                            var th2=document.createElement("td");
                            var tr=document.createElement("tr");
                            if(UserRegJSON[i].RoomID==1)
                            {
                                th1.innerHTML=UserRegJSON[i].BookedDate;
                                th2.innerHTML="Single";
                            }
                            else if(UserRegJSON[i].RoomID==2)
                            {
                                th1.innerHTML=UserRegJSON[i].BookedDate;
                                th2.innerHTML="Double";
                            }
                            else if(UserRegJSON[i].RoomID==3)
                            {
                                th1.innerHTML=UserRegJSON[i].BookedDate;
                                th2.innerHTML="Luxery";
                            }
                            tr.appendChild(th1);
                            tr.appendChild(th2);
                            table.appendChild(tr);
                        }
                        regNode.appendChild(table);
                        regNode.appendChild(document.createElement("br"));
                        regNode.appendChild(document.createElement("br"));    
                        regNode.appendChild(document.createElement("br"));
                    }
                    else{ console.log("No Reg Data Found");
                        if (regNode != null)
                            regNode.innerHTML="No REgistartion Data Found";
                    }
                }
            }
        http.send();
}
function unsetMainActive()
{
    var node=document.getElementById("adminMain");
    node.className="";
}
function login()
{
    var http = new XMLHttpRequest();
    var url = "signIn";
    var formData="{\"email\":\""+
            document.getElementById("loginEmail").value+"\",\"password\":\""+
            document.getElementById("loginPass").value+"\"}";
            document.getElementById("signinModalDisMiss").click();
    console.log(formData);
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=parseInt(http.responseText,10);
                if(isValid!="-1")
                {
                    var JSONUser = JSON.parse(http.responseText);
                    currentUser=JSONUser.userID;
                    currentUserName=JSONUser.name;
                    aStatus=JSONUser.aStatus;
                    status="dsfkdsm";
                    loginCheck(status,JSONUser.name);
                }
                else{ alert("Incorrect UserName or Password!")}
            }
        }
    http.send(formData);
    

}
function EditProfile()
{
    var http = new XMLHttpRequest();
    var url = "UpdateUser";
    var formData="{\"email\":\""+
            document.getElementById("emailAddressE").value+"\",\"name\":\""+
            document.getElementById("userNameE").value+"\",\"password\":\""+
            document.getElementById("userPasswordE").value+"\"}";

    console.log(formData);
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=parseInt(http.responseText,10);
                if(isValid==1){
                    alert("Updated");
                }
                else{
                    alert("Something Happened");
                }
            }
        }
    http.send(formData);
}

function logout()
{
    var http = new XMLHttpRequest();
    var url = "destroySession";
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                status="";
                window.location.href = "userDashBoard.html";
            }
        }
    http.send();    
}
function uploadProfileImg(event)
{
    event.preventDefault();
    var form = document.getElementById('file-form1');
    var fileSelect = document.getElementById('file-select1');
    var uploadButton = document.getElementById('upload-button1');
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
      formData.append('photos1[]', file, file.name);

      // Set up the request.
        var xhr = new XMLHttpRequest();

        // Open the connection.
    xhr.open('POST', 'UploadUserImage?email='+document.getElementById("emailAddress").value, true);

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
function EditProfileImg(event)
{
    event.preventDefault();
    var form = document.getElementById('file-form11');
    var fileSelect = document.getElementById('file-select11');
    var uploadButton = document.getElementById('upload-button11');
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
      formData.append('photos1[]', file, file.name);

      // Set up the request.
        var xhr = new XMLHttpRequest();

        // Open the connection.
    xhr.open('POST', 'UploadUserImage?email='+document.getElementById("emailAddress").value, true);

    // Set up a handler for when the request finishes.
    xhr.onload = function () {
                if(xhr.readyState == 4 && xhr.status == 200) {
                    var isValid=xhr.responseText;
                    if(isValid!="false")
                    {
                        alert("Updated");
                    }
                    else{ alert("Some Error Occured Uploading Image")}
                }
    };

    // Send the Data.
    xhr.send(formData);
    }
}
function showImgToBeUploaded(input)
{
if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah1')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
            $('#profileImg').attr('src',e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function showImgToBeUploadeed(input)
{
document.getElementById('blah').style.visibility='initial';
if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function signUp()
{
    var http = new XMLHttpRequest();
    var url = "signUp";
    var formData="{\"name\":\""+document.getElementById("userName").value+"\",\"email\":\""+
            document.getElementById("emailAddress").value+"\",\"password\":\""+
            document.getElementById("userPassword").value+"\"}";
    
    document.getElementById('upload-button1').click();
    document.getElementById("signUpBody").innerHTML="";
    var successImg=document.createElement("img");
    var para=document.createElement("p");
    successImg.src="assets/media/dec/loading.gif";
    successImg.height=200;
    successImg.width=200;
    document.getElementById("signUpBody").setAttribute("align", "center");
    document.getElementById("signUpBody").appendChild(successImg);
      
    console.log(formData);
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                document.getElementById("signUpBody").innerHTML="";
                var successImg=document.createElement("img");
                var para=document.createElement("p");
                successImg.src="assets/media/dec/success.png";
                successImg.height=100;
                successImg.width=100;
                var confText=document.createTextNode("You have successfully Signed Up! A confirmation email has been sent to your account please follow the link in email to continue");
                para.appendChild(confText);
                document.getElementById("signUpBody").appendChild(successImg);
                document.getElementById("signUpBody").appendChild(para);
            }
    }

    http.send(formData);
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
                    document.getElementById("priceInput"+type).innerHTML=isValid.approvedPrice.toFixed(1);
            }
        }
    http.send();
}
function loadCauroselImages(type)
{
    var http = new XMLHttpRequest();
    var url="getImages?roomType="+type;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState === 4 && http.status === 200) {
                var isValid=JSON.parse(http.responseText);
                    console.log(isValid);
                    var ParentInd= document.getElementById("staticIndicators");
                    var ParentImg= document.getElementById("staticImgaes");

                    ParentInd.innerHTML="";
                    ParentImg.innerHTML="";
                    var i=0;
                    for (var key in isValid) {
                      if (isValid.hasOwnProperty(key)) {
                        console.log(key + " -> " + isValid[key]);
          
                        var listInd=document.createElement("li");
                        listInd.setAttribute("data-target","#myCarousel2");
                        listInd.setAttribute("data-slide-to",i);
                        
                        var Imgdiv=document.createElement("div");
                        var ImgSelf=document.createElement("img");
                        ImgSelf.src=isValid[key];
                        ImgSelf.setAttribute("class","img-responsive")
                        if(i==0)
                        {
                            listInd.setAttribute("class","active");
                            Imgdiv.setAttribute("class","item active");
                        }
                        else
                        {
                            Imgdiv.setAttribute("class","item");
         
                        }
                        Imgdiv.appendChild(ImgSelf);
                        ParentImg.appendChild(Imgdiv);
                        ParentInd.appendChild(listInd);
                    i++;
                    }
                }
            }
        };
    http.send();
    
}
function loadMainAmenities(type)
{
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
     if(dd<10){
            dd='0'+dd
        } 
        if(mm<10){
            mm='0'+mm
        } 

    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("checkIn").setAttribute("min", today);
    document.getElementById("checkout").setAttribute("min", today);
    document.getElementById("checkInSearch").setAttribute("min", today);
    document.getElementById("checkoutSearch").setAttribute("min", today);

    currentlySelectedRoomType=type;
    if(type==1)
    {
        document.getElementById("roomTypeName").innerHTML="Single";
    }
    else if(type==2)
    {
        document.getElementById("roomTypeName").innerHTML="Double";
    }
    else if(type==3)
    {
        document.getElementById("roomTypeName").innerHTML="Suite";
    }
    var http = new XMLHttpRequest();
    var url = "getFeatures?roomType="+type;
    http.open("GET", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                var isValid=JSON.parse(http.responseText);
                    var Parent= document.getElementById("amenitylist");
                    Parent.innerHTML="";

                if(isValid!="false")
                {
                    for (var key in isValid) {
                    if (isValid.hasOwnProperty(key)) {
                        console.log(key + " -> " + isValid[key]);
                        var AmenList=document.createElement("li");
                        AmenList.style.display="inline";
                        var AmenImg=document.createElement("img");
                        AmenImg.src="assets/media/Amen/"+isValid[key]+".png";
                        AmenList.appendChild(AmenImg);
                        Parent.appendChild(AmenList);
                    }}
                }
                else{ alert("Something occured")}
            }
        }
    http.send();
}
function register()
{
    
    var http = new XMLHttpRequest();
    var url = "register";
    var formData="{\"userID\":\""+currentUser+"\","+
                   "\"roomType\":\""+currentlySelectedRoomType+"\","+
                   "\"checkIn\":\""+document.getElementById("checkIn").value+"\","+
                   "\"checkOut\":\""+document.getElementById("checkout").value+"\"}";

    console.log(formData);
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "multipart/form-data" );                
    http.onreadystatechange = function() {//Call a function when the state changes.
            if(http.readyState == 4 && http.status == 200) {
                  if(http.responseText=="true")
                      alert("Registred");
                  else
                      alert("sorry but rooms not avaibale for these dates")
            }
    }

    http.send(formData);
    
}