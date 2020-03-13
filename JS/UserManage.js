///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//全局变量
var pageSize = 16;              //一页最多显示16条信息
var jData = [];
var searchType = '';
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//展示表格  
function displayTable(data){
    $('#paginationUserInfoList').jqPaginator({
        first: '<li class="first"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;"><<</a></li>',
        next: '<li class="next"><a href="javascript:;">>></a></li>',
        last: '<li class="last"><a href="javascript:;">末页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        totalPages: Math.ceil(data.length / pageSize),
        totalCounts: data.length,
        pageSize: pageSize,
        onPageChange: function(num){
            var begin = (num - 1) * pageSize;
            for(var i = begin; i < data.length && i < begin + pageSize; i++){
                $('tbody').append('<tr><td>' + data[i]['UserID']
                + '</td><td>' + data[i]['Name']
                + '</td><td>' + data[i]['Email']
                + '</td><td>' + data[i]['Privilege']
                + '</td><td>' + data[i]['Workcell']
                + '</td><td>' + data[i]['LastLogin']
                + '</td><td><button class="btn act-btn" onclick="showEditModal(this);">修改</button>'
                + '<button class="btn act-btn" onclick="delUser(this);">删除</button>'
                + '</td></tr>');
            }
        }
    });
}
function refleshTable(){
    $('tbody').empty();
    $.ajax({
        type: "GET",
        dataType: "JSON",
        url: "../TestData/UserInfoList.json",
        success: function(result){
            displayTable(result);
            jData = result;
        },
        error: function(){
            alert('获取信息失败，请稍后刷新重试...');
        }
    });
}
$(window).on('load', refleshTable());
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 查找
    function chooseSearchType(e){
        $('#searchTypeBtn').text($(e).text());
        searchType = $(e).text();
        $('#paramInput').focus();
    }
    
    $('#searchBtn').click(function(){
        var param = $('#paramInput').val();
        switch(searchType){
            case 'UserID':
                displayTable(jData.filter(item => { return item.UserID == param}));
                break;
            case 'Name':
                displayTable(jData.filter(item => { return item.Name == param}));
                break;
            case 'Privilege':
                displayTable(jData.filter(item => { return item.Privilege == param}));
                break;
            case 'Workcell':
                displayTable(jData.filter(item => { return item.Workcell == param}));
                break;
            default:
                $('#paramInput').val('');
                displayTable(jData);
        }
    });
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 修改用户信息
function showEditModal(e){
    $('#UserID').val($(e).parent().parent().children().eq(0).text());
    $('#NewName').val($(e).parent().parent().children().eq(1).text());
    $('#NewPrivilege').val($(e).parent().parent().children().eq(2).text());
    $("#NewWorkcell").val($(e).parent().parent().children().eq(3).text());

    $('#editModal').modal('show');
}
$('#EditBtn').click(function(){
    var transData = {
        'UserID': $('#UserID').val(),
        'NewName': $('#NewName').val(),
        'NewPrivilege': $('#NewPrivilege').val(),
        'NewWorkcell': $('#NewWorkcell').val()
    };
    /* $.ajax({
        type: 'POST',
        dataType: 'JSON',
        contentType: 'application/json',
        data: transData,
        url: '',               //待填
        success: function(result){
            if(result.Status == 'success'){
                alert('修改成功！');
                refleshTable();
            }else{
                alert('修改失败，请稍后重试...');
            }
        },
        error: function(){
            alert('修改失败，请稍后重试...')
        }
    }); */
});
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 删除用户信息
function delUser(e){
    var userID = $(e).parent().parent().children().eq(0).text();
    if(confirm('是否需要删除工号为' + userID + '用户？')){
        /* $.ajax({
            type: 'POST',
            dataType: 'JSON',
            url: '',               //待填  ?UserID=userID
            success: function(result){
                if(result.Status == 'success'){
                    alert('删除成功！');
                    refleshTable();
                }else{
                    alert('删除失败，请稍后重试...');
                }
            },
            error: function(){
                alert('删除失败，请稍后重试...')
            }
        }); */
    }
}
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 添加用户
$('#showAddModalBtn').click(function(){
    $('#addModal').modal('show');
});
$('#AddBtn').click(function(){
    var transData = {
        'Name': $('#NewUserName').val(),
        'Privilege': $('#NewUserPrivilege').val(),
        'Workcell': $('#NewUserWorkcell').val()
    };
    /* $.ajax({
        type: 'POST',
        dataType: 'JSON',
        contentType: 'application/json',
        data: transData,
        url: '',                      //待填
        success: function(result){
            if(result.Status == 'success'){
                alert('添加成功！');
                refleshTable();
            }else{
                alert('添加失败，请稍后重试...');
            }
        },
        error: function(){
            alert('添加失败，请稍后重试...')
        }
    }); */
});
//#endregion
