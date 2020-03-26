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

var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
var userName= window.location.search.substr(10,3);
$("#name").html(userName+" 欢迎");

//注销

