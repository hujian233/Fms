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
$('#content').load('../HTML/TempApplicationManage2.html');
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
//夹具管理
$('#apply1').click(function(){
    $('#content').empty();
    $('#content').load('../HTML/TempApplicationManage2.html');
})

//高级功能
$('#function').click(function(){
    $('#content').empty();
    $('#content').load('../HTML/OldDataLoad.html');
})

