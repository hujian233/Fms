////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//获取定义列表、刷新带申请列表（函数）
$(window).on('load', function(){
    var code = getUrlVars()['Code'];
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: '../TestData/ToolEntityList.json',  //后端Url，附加code参数   
        success: function(result){
            for(var i = 0; i < result.length; i++){
                $('#definitionTbody').append('<tr><td>' + result[i]['Code']
                + '</td><td>' + result[i]['SeqID']
                + '</td><td>' + result[i]['RegDate']
                + '</td><td>' + result[i]['UsedCount']
                + '</td><td>' + result[i]['State']
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
    });

    refleshCache();
});

function refleshCache(){
    $('#inOutTbody').empty();
    $('#repairTbody').empty();
    $('#scrapTbody').empty();
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: '../TestData/CacheList.json',   //待改  后端URL
        success: function(result){
            for(var i = 0; i < result.InOut.length; i++){
                $('#inOutTbody').append('<tr><td>' + result.InOut[i].Code
                + '</td><td>' + result.InOut[i].SeqID
                + '</td><td><span class="glyphicon glyphicon-remove cache-remove-icon" onclick="remove(this);"></span></td></tr>')
            }
            for(var i = 0; i < result.Repair.length; i++){
                $('#repairTbody').append('<tr><td>' + result.Repair[i].Code
                + '</td><td>' + result.Repair[i].SeqID
                + '</td><td><span class="glyphicon glyphicon-remove cache-remove-icon" onclick="remove(this);"></span></td></tr>')
            }
            for(var i = 0; i < result.Scrap.length; i++){
                $('#scrapTbody').append('<tr><td>' + result.Scrap[i].Code
                + '</td><td>' + result.Scrap[i].SeqID
                + '</td><td><span class="glyphicon glyphicon-remove cache-remove-icon" onclick="remove(this);"></span></td></tr>')
            }
        },
        error: function(){
            alert('待提交申请中，部分夹具被其他用户操作，系统已自动将其去除');
        }
    });
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
        success: function(result){
            
            $('#Code').text(result.Code);
            $('#SeqID').text(result.SeqID);
            $('#RegDate').text(result.RegDate);
            $('#UsedCount').text(result.UsedCount);
            $('#State').text(result.State);
            $('#BillNo').text(result.BillNo);
            $('#Location').text(result.Location);
            $('#StoreHouse').text(result.StoreHouse);
            $('#LastTestTime').text(result.LastTestTime);
            $('#TotalUsedTime').text(result.TotalUsedTime);
            //$('#Image').attr('src', result.Image);
        },
        error: function(){
            alert('获取数据失败，请稍后重试...')
        }
    });

    $('#InfoModal').modal('show');
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//加入出库单
function putInOut(e){
    var code = $(e).parent().parent().children().eq(0).text();
    var seqID = $(e).parent().parent().children().eq(1).text();
    var transData = [];
    transData.push({'Code': code, 'SeqID': seqID, 'Type': 'InOut'});
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

    refleshCache();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//加入报修单
function putRepair(e){
    var code = $(e).parent().parent().children().eq(0).text();
    var seqID = $(e).parent().parent().children().eq(1).text();
    $('#repairCode').val(code);
    $('#repairSeqID').val(seqID);
    $('#RepairModal').modal('show');
}

//预览图片
$('#repairImage').change(function(){
    url = document.getElementById('repairImage').files[0];
    var fileReader = new FileReader();
    fileReader.readAsDataURL(url);
    fileReader.onload = function(){
        console.log(fileReader.result);
        $('#repairImage').attr('src', fileReader.result);
    }
    
});


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//加入报废单
function putScrap(e){
    var code = $(e).parent().parent().children().eq(0).text();
    var seqID = $(e).parent().parent().children().eq(1).text();
    var transData = [];
    transData.push({'Code': code, 'SeqID': seqID, 'Type': 'Scrap'});
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
    
    refleshCache();
} 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//缓存窗tab按钮
function changeTab(e){
    if($(e).text() == '出库单'){
        $('#cacheTable').children().eq(1).addClass('tbody-nodisplay');
        $('#cacheTable').children().eq(2).addClass('tbody-nodisplay');
        $('#cacheTable').children().eq(3).addClass('tbody-nodisplay');
        $('#cacheTable').children().eq(1).removeClass('tbody-nodisplay');
    }else if($(e).text() == '报修单'){
        $('#cacheTable').children().eq(1).addClass('tbody-nodisplay');
        $('#cacheTable').children().eq(2).addClass('tbody-nodisplay');
        $('#cacheTable').children().eq(3).addClass('tbody-nodisplay');
        $('#cacheTable').children().eq(2).removeClass('tbody-nodisplay');
    }else{
        $('#cacheTable').children().eq(1).addClass('tbody-nodisplay');
        $('#cacheTable').children().eq(2).addClass('tbody-nodisplay');
        $('#cacheTable').children().eq(3).addClass('tbody-nodisplay');
        $('#cacheTable').children().eq(3).removeClass('tbody-nodisplay');
    }
    $(e).parent().children().eq(0).removeClass('a-active');
    $(e).parent().children().eq(1).removeClass('a-active');
    $(e).parent().children().eq(2).removeClass('a-active');
    $(e).addClass('a-active');
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//缓存窗单个清除按钮
function remove(e){ 
}

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
