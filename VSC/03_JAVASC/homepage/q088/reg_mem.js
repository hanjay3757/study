var ipId;
var ipPw;
var ipPw_re;
var ipUser_name;
var ipEmail;
var ipBirth_y;
var ipBirth_m;
var ipBirth_d;
var ipSexes;
var ipTel_1;
var ipTel_2;
var ipTel_3;

var id;
var pw;
var pw_re;
var user_name;
var email;
var birth_y;
var birth_m;
var birth_d;
var tel_1;
var tel_2;
var tel_3;

var sexes;
var sex;

window.onload = function(){
    ipId = document.getElementById("id");
    ipPw = document.getElementById("pw");
    ipPw_re = document.getElementById("pw_re");
    ipUser_name = document.getElementById("user_name");
    ipEmail = document.getElementById("email");
    ipBirth_y = document.getElementById("birth_y");
    ipBirth_m = document.getElementById("birth_m");
    ipBirth_d = document.getElementById("birth_d");
    ipTel_1 = document.getElementById("tel_1");
    ipTel_2 = document.getElementById("tel_2");
    ipTel_3 = document.getElementById("tel_3");
    
    ipSexes = document.getElementById("sex");
}

function check(){
    id = ipId.value;
    pw = ipPw.value;
    pw_re = ipPw_re.value;
    user_name = ipUser_name.value;
    email = ipEmail.value;
    birth_y = ipBirth_y.value;
    birth_m = ipBirth_m.value;
    birth_d = ipBirth_d.value;
    tel_1 = ipTel_1.value;
    tel_2 = ipTel_2.value;
    tel_3 = ipTel_3.value;

    for(var i=0; i<ipSexes.length; i++){
        if(ipSexes[i].checked == true){
            sex = ipSexes[i].value;
        }
    }

    var userInfo =
    "id:" + id + "\n"
    +"pw:" + pw + "\n"
    +"pw_re:" + pw_re + "\n"
    +"useName:" + userName + "\n"
    +"useName:" + userName + "\n"
}

