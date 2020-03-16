////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//定义全局变量
var initData = {};
var displayType = 'Out';                //当前展示的申请类型
var searchType = '';                         //搜索类型
var selectedToolModel = [];         //已选择的夹具实体
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 获取定义列表、刷新待申请列表（函数）
function refreshTable(){
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: '../TestData/TempApplicationList.json',  //后端Url，附加code参数
        success: function(result){
            initData = result;
            displayTable(initData[displayType]);
        },
        error: function(){
            alert('获取信息失败，请稍后重试...');
        }
    });
}
function displayTable(data){
    $('tbody').empty();
    for(let i = 0; i < data.length; i++){
        $('tbody').append(
            '<tr><td><input class="checkbox" onchange="selectOne(this);" type="checkbox">'
            + '</td><td>' + data[i].Code
            + '</td><td>' + data[i].SeqID
            + '</td><td>' + data[i].Name
            + '</td><td><i class="glyphicon glyphicon-remove remove-icon" onclick="remove(this);"></i></td></tr>');
    }
}

$(window).on('load', refreshTable());
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 切换展示的申请类型
function changeTab(e, type){
    $('#' + displayType + 'Tab').removeClass('a-tab-active');
    $(e).addClass('a-tab-active');
    displayType = type;
    displayTable(initData[displayType]);
}
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region checkbox
$('#selectAll').change(function(){
    if($(this).prop('checked')){
        for(let i = 0; i < $('tbody').children().length; i++){
            $('tbody').children().eq(i).children().eq(0).children().eq(0).prop("checked", true);
            $('tbody').children().eq(i).addClass('tr-selected');
        }
    }else{
        for(let i = 0; i < $('tbody').children().length; i++){
            $('tbody').children().eq(i).children().eq(0).children().eq(0).prop("checked", false);
            $('tbody').children().eq(i).removeClass('tr-selected');
        }
    }
})
function selectOne(e){
    if(!$(e).prop('checked')){
        $(e).prop('checked', false);
        $(e).parent().parent().removeClass('tr-selected');
    }else{
        $(e).prop('checked', true);
        $(e).parent().parent().addClass('tr-selected');
    }
}
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 删除按钮
function remove(e){
    var code = $(e).parent().parent().children().eq(1).text();
    var seqID = $(e).parent().parent().children().eq(2).text();
    var transData = [];
    transData.push({'Code': code, 'SeqID': seqID, 'Type': displayType});
    /* $.ajax({
        type: 'POST',
        dataType: 'JSON',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(transData),
        url: '',  //待改  后端接口
        success: function(result){
            if(result.Status == 'error'){
                alert('操作失败，请稍后重试...');
            }
        } 
    }); */
    displayTable(initData[displayType]);
}
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 查找
function chooseSearchType(e, type){
    $('#searchTypeBtn').text($(e).text());
    searchType = type;
    $('#paramInput').focus();
}

$('#searchBtn').click(function(){
    var param = $('#paramInput').val();
    switch(searchType){
        case 'Code':
            displayTable(initData[displayType].filter(item => { return item.Code == param}));
            break;
        case 'Name':
            displayTable(initData[displayType].filter(item => { return item.Name == param}));
            break;
        default:
            $('#paramInput').val('');
            displayTable(initData[displayType]);
    }
});
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 填写申请单
function showFillInModal(){
    for(let i = 0; i < $('tbody').children().length; i++){
        if($('tbody').children().eq(i).children().eq(0).children().eq(0).prop("checked")){
            
        }
    }
    switch(displayType){
        case 'Out': 
            $('#modalTitle').text('填写出库申请单');
            break;
        case 'In':
            $('#modalTitle').text('填写入库申请单');
            break;
        case 'Repair':
            $('#modalTitle').text('填写报修申请单');
            break;
        case 'Scrap':
            $('#modalTitle').text('填写报废申请单');
            break;
    }

    $('#fillInModal').modal('show');
}

function submitByAjax(data){          //url待填
    /* $.ajax({
        type: 'POST',
        dataType: 'JSON',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(data),
        url: '',  
        success: function(result){
            if(result.Status == 'error'){
                alert('操作失败，请稍后重试...');
            }
        } 
    }); */
}
function chooseBulkOperType(e){
    $('#bulkOperTypeBtn').text($(e).text());
    bulkOperType = $(e).text();
    $('#num1Input').focus();
}
$('#bulkOperBtn').click(function(){
    var transData = [];
    var displayedData = $('#definitionTbody').children();
    var num1 = Number.parseInt($('#num1Input').val());
    var num2 = Number.parseInt($('#num2Input').val());
    try{
        if(!(num1 >= 1 && num2 <= pageSize && num1 <= num2 && Number.isInteger(num1)) && Number.isInteger(num2))
            throw '序号格式错误';
        switch(bulkOperType){
            case '出库':
                for(let i = num1; i < num2; i++){
                    let state = displayedData.eq(i).children().eq(5).text();
                    if(state == '可用')
                        transData.push({
                            'Code': displayedData.eq(i).children().eq(1).text(),
                            'SeqID': displayedData.eq(i).children().eq(2).text(),
                            'Type': 'Out'
                        });
                    else    throw '夹具选择错误';
                }
                break;
            case '入库':
                for(let i = num1; i < num2; i++){
                    let state = displayedData.eq(i).children().eq(5).text();
                    if(state == '已报修' || state == '已出库')
                        transData.push({
                            'Code': displayedData.eq(i).children().eq(1).text(),
                            'SeqID': displayedData.eq(i).children().eq(2).text(),
                            'Type': 'Out'
                        });
                    else    throw '夹具选择错误';
                }
                break;
            case '报废':
                for(let i = num1; i < num2; i++){
                    let state = displayedData.eq(i).children().eq(5).text();
                    if(state != '已报废')
                        transData.push({
                            'Code': displayedData.eq(i).children().eq(1).text(),
                            'SeqID': displayedData.eq(i).children().eq(2).text(),
                            'Type': 'Out'
                        });
                    else    throw '夹具选择错误';
                }
                break;
        }
        submitByAjax(transData);
    }
    catch(err){
        alert(err + '，请重新填写...');
    } 
})
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 滚动条监听，操作窗固定
$(window).scroll(function(){
    if($(window).scrollTop() > 100)
        $('.oper-box').addClass('oper-box-sticky');
    else
        $('.oper-box').removeClass('oper-box-sticky');
})
//#endregion