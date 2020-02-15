var is_password_legel = true;
var is_rePassword_legal = true;
var is_email = true;
var password_reg = new RegExp('^[a-zA-Z0-9]{6,12}$');
var email_reg = new RegExp('^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$');

$('#newPassword').change(function(){
    if($(this).val() != ""){
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

            is_rePassword_legel = false;
            $('#rePassword').parent().parent().attr('class', 'form-group has-error has-feedback');
            $('#rePassword').parent().children('span').remove();
            $('#rePassword').parent().append('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
        }
    }else{
        is_password_legel = true;
        $(this).parent().parent().attr('class', 'form-group');
        $(this).parent().children('span').remove();
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

$('#newEmail').change(function(){
    if($(this).val() != ""){
        if(!email_reg.test($(this).val())){
            is_email_legel = false;
            $(this).parent().parent().attr('class', 'form-group has-error has-feedback');
            $(this).parent().children('span').remove();
            $(this).parent().append('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
        }else{
            is_email_legel = true;
            $(this).parent().parent().attr('class', 'form-group has-success has-feedback');
            $(this).parent().children('span').remove();
            $(this).parent().append('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
        }
    }else{
        is_email_legel = true;
        $(this).parent().parent().attr('class', 'form-group');
        $(this).parent().children('span').remove();
    }
});

function validate(){
    if(is_password_legel && is_rePassword_legal && is_email_legel){
        return true;
    }else{
        return false;
    }
}