function login(){
   

    var tagid = document.getElementById("id");
    var tagpw = document.getElementById("pw");
    var id = tagid.value;
    var pw = tagpw.value;
    console.log(id);
    console.log(pw);

    if(id =="cat" && pw == "1234" ){
        alert("로그인 성공");
        
    }else {
        alert("로그인 실패")
    }
}
