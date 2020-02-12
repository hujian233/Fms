/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//轮播图
$183(document).ready(function(){	
    $183('#camera_wrap_1').camera();
});
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//客户端校验
var id_is_legal = false;
var pwd_is_legal = false;
var id_reg = new RegExp('^[0-9]{7}$');
var password_reg = new RegExp('^[a-zA-Z0-9]{6,12}$');

$('#userID').change(function(){
    if(!id_reg.test($('#userID').val())){
        $('#tip').text("请输入正确格式的工号");
        $('#tip').css('display', 'block');
    }else{
        id_is_legal = true;
        $('#tip').css('display', 'none');
    }
});
$('#password').change(function(){
    if(!password_reg.test($('#password').val())){
        $('#tip').text("请输入正确格式的密码");
        $('#tip').css('display', 'block');
    }else{
        pwd_is_legal = true;
        $('#tip').css('display', 'none');
    }
});
function validate(){
    if(id_is_legal == true && pwd_is_legal == true){
        return true;
    }else{
        return false;
    }
}