////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//获取定义列表
$(window).on('load', function(){
    var code = getUrlVars()['Code'];
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: '../TestData/ToolEntityList.json',  //后端Url，附加code参数   
        success: function(data){
            for(var i = 0; i < data.length; i++){
                $('#definitionTbody').append('<tr><td>' + data[i]['Code']
                + '</td><td>' + data[i]['SeqID']
                + '</td><td>' + data[i]['RegDate']
                + '</td><td>' + data[i]['UsedCount']
                + '</td><td>' + data[i]['State']
                + '</td><td><button class="btn act-btn" onclick="getInfo(this);">查看详情</button>' 
                + '<button class="btn act-btn" onclick="putInOut(this);">加入出库单</button>' 
                + '<button class="btn act-btn" onclick="putRepair(this);">加入报修单</button>'
                + '<button class="btn act-btn" onclick="putScrap(this);">加入报废单</button>'
                + '</td></tr>');
            }
        },
        error: function(){
            alert('获取信息失败，请稍后重试...');
        }
    })
});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//缓存窗按钮
function changeTab(e){
    $(e).parent().children().eq(0).removeClass('a-active');
    $(e).parent().children().eq(1).removeClass('a-active');
    $(e).parent().children().eq(2).removeClass('a-active');
    $(e).addClass('a-active');
    if($(e).text() == '出库单'){
        $('#cacheTable').children().eq(1).addClass('tbody-display');
        $('#cacheTable').children().eq(2).addClass('tbody-display');
        $('#cacheTable').children().eq(3).addClass('tbody-display');
        $('#cacheTable').children().eq(1).removeClass('tbody-display');
    }else if($(e).text() == '报修单'){
        $('#cacheTable').children().eq(1).addClass('tbody-display');
        $('#cacheTable').children().eq(2).addClass('tbody-display');
        $('#cacheTable').children().eq(3).addClass('tbody-display');
        $('#cacheTable').children().eq(2).removeClass('tbody-display');
    }else{
        $('#cacheTable').children().eq(1).addClass('tbody-display');
        $('#cacheTable').children().eq(2).addClass('tbody-display');
        $('#cacheTable').children().eq(3).addClass('tbody-display');
        $('#cacheTable').children().eq(3).removeClass('tbody-display');
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//获取夹具定义的详细信息
function getInfo(e){
    var code = $(e).parent().parent().children().eq(0).text();
    var seqID = $(e).parent().parent().children().eq(1).text();
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: '../TestData/ToolEntityInfo.json',  //code附在url后  '...?Code=' + code + '&SeqID=' + seqID
        success: function(data){
            
            $('#Code').text(data.Code);
            $('#SeqID').text(data.SeqID);
            $('#RegDate').text(data.RegDate);
            $('#UsedCount').text(data.UsedCount);
            $('#State').text(data.State);
            $('#BillNo').text(data.BillNo);
            $('#Location').text(data.Location);
            $('#StoreHouse').text(data.StoreHouse);
            $('#LastTestTime').text(data.LastTestTime);
            $('#TotalUsedTime').text(data.TotalUsedTime);
            //$('#Image').attr('src', data.Image);
        },
        error: function(){
            alert('获取数据失败，请稍后重试...')
        }
    });

    $('#InfoModal').modal('show');
}
/* 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//加入出库单
function putInOut(e){
    
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//加入报修单
function putRepair(e){

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//加入报废单
function putScrap(e){

} */

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//获取Url查询字符串
function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
