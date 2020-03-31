/*
$.ajax({                            //url待改
    type: 'POST',
    dataType: 'JSON',
    contentType: 'application/json',
    data: JSON.stringify(transData),
    url: '',
    success: function(result){
        if(result.resultCode == 0){

        }
        else{

        }
    },
    error: function(){

    }
})*/
$('#content').empty();
$('#content').load('../HTML/UserManage.html');
//$('#content').load('../HTML/ToolEntityDisplay.html');
//$('#content').load('../HTML/ToolDefinitionDisplay.html');
//$('#content').load('../HTML/AdminToolDefinitionDisplay.html');
//$('#content').load('../HTML/UserManage.html');
//$('#content').load('../HTML/PwResetApplicationManage.html');
//$('#content').load('../HTML/UserInfoDisplay.html');
//$('#content').load('../HTML/OldDataLoad.html');
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
var canshu= window.location.search.substr(10);
var mark=canshu.indexOf("&");
var userName=canshu.substring(0,mark);
userName = decodeURIComponent(userName);
$("#name").html(userName+" 欢迎");

//跳转

//用户信息
$('#user').click(function(){
    $('#content').empty();
    $('#content').load('../HTML/UserManage.html');
})


//导入
$('#import').click(function(){
    $('#content').empty();
    $('#content').load('../HTML/OldDataLoad.html');
})
//夹具定义
$('#manage').click(function(){
    $('#content').empty();
    $('#content').load('../HTML/AdminToolDefinitionDisplay.html');
})

/*
//家居实体管理
entity
$('#entity').click(function(){
    $('#content').empty();
    $('#content').load('../HTML/AdminToolDefinitionDisplay.html');
})*/
