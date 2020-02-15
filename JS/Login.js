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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刷卡重置密码
$('#reset_password').click(function(){
    if(id_is_legal){
        alert('请刷卡，系统将验证信息...');
        try{
            //var cartNo = readCard();   //readCard()函数调用读卡器接口，返回员工卡号
            $.ajax({
                type : 'post',
                dataType : 'json',
                url : 'http://服务器名/mesvc01/api/emploee/query/' + cartNo.toString(),
                error : alert('获取信息失败，请稍后重试...'),
                success : function(data){
                    if(data[3] == $('#userID').val()){
                        $('#resetModal').modal('show');
                    } else{
                        alert('验证失败，工号与磁卡信息不匹配...');
                    }
                }
            });
        }catch{
            alert('获取信息失败，请稍后重试...');
        }        
    }else{
        $('#tip').text("请输入正确格式的工号");
        $('#tip').css('display', 'block');
    }
});

//表单验证
var is_password_legel = false;
var is_rePassword_legal = false;
var password_reg = new RegExp('^[a-zA-Z0-9]{6,12}$');

$('#newPassword').change(function(){
    if(!password_reg.test($(this).val())){
        is_password_legel = false;
        $(this).parent().parent().attr('class', 'form-group has-error has-feedback');
        $(this).parent().children('span').remove();
        $(this).parent().append('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
    }else{
        is_password_legel = true;
        $(this).parent().parent().attr('class', 'form-group has-success has-feedback');
        $(this).parent().children('span').remove();
        $(this).parent().append('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
    }
});

$('#rePassword').change(function(){
    if($(this).val() != $('#newPassword').val()){
        is_rePassword_legel = false;
        $(this).parent().parent().attr('class', 'form-group has-error has-feedback');
        $(this).parent().children('span').remove();
        $(this).parent().append('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
    }else{
        is_rePassword_legel = true;
        $(this).parent().parent().attr('class', 'form-group has-success has-feedback');
        $(this).parent().children('span').remove();
        $(this).parent().append('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
    }
});

function validate(){
    if(is_password_legel && is_rePassword_legal){
        return true;
    }else{
        return false;
    }
}