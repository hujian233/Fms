////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//定义全局变量
var jData = [];                 //从后端获取的初始数据           
var pageSize = 20;              //一页最多显示20条信息
var filterBy = {                //存放筛选条件
    'Code': '',
    'Name': '',
    'Family': '',
    'Model': ''
};   

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 初始化/刷新表格数据
function displayTable(data){
    $('#paginationToolDeinit').jqPaginator({
        first: '<li class="first"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;"><<</a></li>',
        next: '<li class="next"><a href="javascript:;">>></a></li>',
        last: '<li class="last"><a href="javascript:;">末页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        totalPages: Math.ceil(data.length / pageSize),
        totalCounts: data.length,
        pageSize: pageSize,
        onPageChange: function(num){
            $('tbody').empty();
            var begin = (num - 1) * pageSize;
            for(var i = begin; i < data.length && i < begin + pageSize; i++){
                $('tbody').append('<tr><td>' + data[i].Code
                + '</td><td>' + data[i].Name
                + '</td><td>' + data[i].Family
                + '</td><td>' + data[i].Model
                + '</td><td>' + data[i].PartNo
                + '</td><td>' + data[i].OwnerID + '&nbsp&nbsp&nbsp' + data[i].OwnerName
                + '</td><td><button class="btn act-btn" onclick="getInfo(this);">查看详情</button>'
                + '<button class="btn act-btn" onclick="getEntity(this);">查看实体</button>'
                + '</td></tr>');
            }
        }
    });
}

$(window).on('load', function(){
    $.ajax({                    //获取夹具定义数据
        type: 'GET',
        dataType: 'JSON',
        url: '../TestData/ToolDefinitionList.json',  //后端Url，待改
        success: function(result){
            displayTable(result);
            jData = result;
        },
        error: function(){
            alert('获取信息失败，请刷新重试...');
        }
    });
    $.ajax({                    //获取family、model字典
        type: 'GET',
        dataType: 'JSON',
        url: '../TestData/FamModDict.json',  //后端Url，待改
        success: function(result){
            for(let p in result.Family)
                $('#familyFilterInput').append('<option value="' + result.Family[p] + '">' + result.Family[p] + '</option>');
            for(let n in result.Model)
                $('#modelFilterInput').append('<option value="' + result.Model[n] + '">' + result.Model[n] + '</option>');
        },
        error: function(){
            $('#familyFilterInput').replaceWith('<input class="form-control filterby-input" id="familyFilterInput" onchange="changeFilter(this, ' + "'Model'" + ');">');
            $('#modelFilterInput').replaceWith('<input class="form-control filterby-input" id="modelFilterInput" onchange="changeFilter(this, ' + "'Family'" + ');">');
        }
    })
})
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 筛选
function runFilter(){  //执行筛选，并刷新展示的表格
    var tempData = jData;
    if(filterBy.Code != '')
        tempData = tempData.filter(item => {return item.Code == filterBy.Code});
    if(filterBy.Name != '')
        tempData = tempData.filter(item => {return item.Name == filterBy.Name});
    if(filterBy.Family != '')
        tempData = tempData.filter(item => {return item.Family == filterBy.Family});
    if(filterBy.Model != '')
        tempData = tempData.filter(item => {return item.Model == filterBy.Model});
    
    if(tempData.length > 0)
        displayTable(tempData);
    else
        alert('无筛选结果..');
}
function changeFilter(e, type){  //响应绑定的控件
    filterBy[type] = $(e).val();
    runFilter();
}
function removeFilter(e, type){  //响应绑定的控件
    filterBy[type] = '';
    $(e).parent().children().eq(0).val('');
    runFilter();
}
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 获取夹具定义详细信息
function getInfo(e){
    var code = $(e).parent().parent().children().eq(0).text();
    $.ajax({
        type: "GET",
        dataType: "JSON",
        url: "../TestData/ToolDefinitionInfo.json",  //code附在url后  "...?code=" + code
        success: function(result){
            
            $('#Code').text(result.Code);
            $('#Name').text(result.Name);
            $('#Family').text(result.Family);
            $('#Model').text(result.Model);
            $('#PartNo').text(result.PartNo);
            $('#UPL').text(result.UPL);
            $('#UsedFor').text(result.UsedFor);
            $('#PMPeriod').text(result.PMPeriod);
            $('#PMContent').text(result.PMContent);
            $('#OwnerID').text(result.OwnerID);
            $('#OwnerName').text(result.OwnerName);
            $('#RecOn').text(result.RecOn);
            $('#RecorderID').text(result.RecorderID);
            $('#RecorderName').text(result.RecorderName);
            $('#EditOn').text(result.EditOn);
            $('#EditorID').text(result.EditorID);
            $('#EditorName').text(result.EditorName);
            $('#Workcell').text(result.Workcell);
        },
        error: function(){
            alert('获取数据失败，请稍后重试...')
        }
    });

    $('#InfoModal').modal('show');
}
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//获取夹具实体列表
function getEntity(e){
    var code = $(e).parent().parent().children().eq(0).text();
    window.location = '../HTML/DisplayToolEntity.html?code=' + code;
}