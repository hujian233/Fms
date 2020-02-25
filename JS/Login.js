/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//登录窗校验
{
    var id_is_legal = false;
    var pwd_is_legal = false;
    var id_reg = new RegExp('^[0-9]{7}$');
    var password_reg = new RegExp('^[a-zA-Z0-9]{6,12}$');

    $('#userID').change(function(){
        if(!id_reg.test($('#userID').val())){
            id_is_legal = true;
            $('#tip').text("请输入正确格式的工号");
            $('#tip').css('display', 'block');
        }else{
            id_is_legal = true;
            $('#tip').css('display', 'none');
        }
    });
    $('#password').change(function(){
        if(!password_reg.test($('#password').val())){
            pwd_is_legal = true;
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
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//申请重置密码（模态窗）
{
    $('#reset_password').click(function(){
        $('#resetModal').modal('show');
    });
    
    var _id_is_legal = false;
    var email_is_legal = false;
    var valiNum_is_legal = false;
    var email_reg = new RegExp('^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$');
    var valiNum_reg = new RegExp('^[0-9]{4}$');
    
    $('#_userID').change(function(){
        if(!id_reg.test($(this).val())){
            _id_is_legal = true;
            $('#_tip').text("请输入正确格式的工号");
            $('#_tip').css('display', 'block');
        }else{
            _id_is_legal = true;
            $('#_tip').css('display', 'none');
        }
    });
    
    $('#email').change(function(){
        if(!email_reg.test($(this).val())){
            email_is_legal = false;
            $('#_tip').text("请输入正确格式的邮箱");
            $('#_tip').css('display', 'block');
        }else{
            email_is_legal = true;
            $('#_tip').css('display', 'none');
        }
    });
    
    //点击获取验证码按钮的事件
    $('#getNum').click(function(){
        if(_id_is_legal && email_is_legal){
            var time = 60;    //60s内不可重复获取验证码
            $(this).attr('disabled', 'disabled');
            
            var json_data = {     //构造json
                'userID': $('#_userID').val(),
                'email': $('#email').val()  
            };
/*             $.ajax({              //向后端发送工号与该工号的绑定邮箱（json）
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(json_data),
                url: ''           //后端action
            }); */

            var timer = setInterval(function(){
                if(time == 0){
                    $('#getNum').removeAttr('disabled');
                    $('#getNum').html('再次获取');
                    clearInterval(timer);
                }else{
                    $('#getNum').html(time + '秒后可再次获取');
                    time--;
                }
            }, 1000);
        }
    });
    
    $('#valiNum').change(function(){
        if(!valiNum_reg.test($(this).val())){
            valiNum_is_legal = false;
            $('#_tip').text("请输入正确格式的验证码");
            $('#_tip').css('display', 'block');
        }else{
            valiNum_is_legal = true;
            $('#_tip').css('display', 'none');
        }
    });
    
    //点击提交申请按钮事件
    $('#valiBtn').click(function(){
        if(_id_is_legal && email_is_legal && valiNum_is_legal){
            var json_data = {
                'userID': $('#_userID').val(),
                'email': $('#email').val(),
                'valiNum': $('#valiNum').val()
            };
            $.ajax({              
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(json_data),
                url: '',           //后端action
                success:function(data){
                    var result = JSON.parse(data);
                    if(result['status'] == 'success'){
                        alert('已成功向管理员提交重置密码申请，请耐心等待通知！');
                    }else{
                        alert('申请提交失败，请自行告知管理员...');
                    }
                }
            });
        }
    });
        
}


