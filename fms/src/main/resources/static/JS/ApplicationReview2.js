//////////////////////////////////////////////////////////////////////////////////////////////////////
var selectedApplication = [];   //已选择的申请
var displayType = 'In';                //当前展示的申请类型
refreshTable(2);
//////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 展示表格、刷新
function refreshTable(type){
    $('tbody').empty();
    debugger;
    //type=3:出库
    $.ajax({ 
        type: 'GET',
        dataType: 'JSON',
      //  url: '../TestData/PwResetApplicationList.json',  //后端Url，待改
        url: '/do/querysubmit?type='+type,
        success: function(result1){
            var result=result1.data;
            $('tbody').empty();
            for(let i = 0; i < result.length; i++){
                $('tbody').append(
                    '<tr><td><input class="checkbox" onchange="selectOne(this);" type="checkbox">'
                    + '</td><td>' + result[i].orderId
                    + '</td><td>' + result[i].applicant
                    + '</td><td>' + result[i].applicantTime
                    + '</td><td><button class="btn act-btn" onclick="accept(this);">同意</button>'
                    + '<button class="btn act-btn" onclick="reject(this);">拒绝</button>'
                    + '</td></tr>');
            }
        },
        error: function(){
            alert('获取信息失败，请刷新重试...');
        }
    });
}
/*$(window).on('load', refreshTable);*/
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 切换展示的申请类型
function changeTab(e, type){
    $('#' + displayType + 'Tab').removeClass('a-tab-active');
    $(e).addClass('a-tab-active');
    $('#selectAll').prop('checked', false);
    displayType = type;
    //displayTable(initData[displayType]);
    if(type=='Out'){
        refreshTable(3);
    }
    if(type=='In'){
        refreshTable(2);
    }
    if(type=='Repair'){
        refreshTable(4);
    }
    if(type=='Scrap'){
        refreshTable(5);
    }
}
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region checkbox
$('#selectAll').change(function(){
    if($(this).prop('checked')){
        for(let i = 0; i < $('tbody').children().length; i++){
            $('tbody').children().eq(i).children().eq(0).children().eq(0).prop('checked', true);
            $('tbody').children().eq(i).addClass('tr-selected');
        }
    }else{
        for(let i = 0; i < $('tbody').children().length; i++){
            $('tbody').children().eq(i).children().eq(0).children().eq(0).prop('checked', false);
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
//#region 单个同意、拒绝
function accept(e){
    var type=1;
    if(displayType=='Out'){
        type=3;
    }
    if(displayType=='In'){
        type=2;
    }
    if(displayType=='Repair'){
        type=4;
    }
    if(displayType=='Scrap'){
        type=5;
    }
    debugger;
    //订单编号
    var orderId=$(e).parent().parent().children().eq(1).text();
    //申请人
    var applicant=$(e).parent().parent().children().eq(2).text();

    var transData = {
        'result': 1,
        'type': type,
        orderIds:[parseInt(orderId)]
    }

    //1:同意；2;驳回result
  /*  var transData = {
        'result': 1,
        'type': type
    }*/
    SubmitByAjax(transData, '/do/approval');
}
function reject(e){
   /* var transData = {
        'Type': 'reject',
        'Application': [
            {   
                'UserID': $(e).parent().parent().children().eq(1).text(),
                'Workcell': $(e).parent().parent().children().eq(3).text()
            }
        ]
    }*/
    var type=1;
    if(displayType=='Out'){
        type=3;
    }
    if(displayType=='In'){
        type=2;
    }
    if(displayType=='Repair'){
        type=4;
    }
    if(displayType=='Scrap'){
        type=5;
    }
    debugger;
    //订单编号
    var orderId=$(e).parent().parent().children().eq(1).text();
    //申请人
    var applicant=$(e).parent().parent().children().eq(2).text();

    var transData = {
        'result': 2,
        'type': type,
        orderIds:[parseInt(orderId)]
    }
    SubmitByAjax(transData, '/do/approval');
}
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 批量同意、拒绝
function AddToSelectedApplication(){
    selectedApplication = [];
    for(let i = 0; i < $('tbody').children().length; i++){    //将选中的夹具添加入变量数组
        if($('tbody').children().eq(i).children().eq(0).children().eq(0).prop('checked')){
            selectedApplication.push({
                'orderId': $('tbody').children().eq(i).children().eq(1).text(),
                'applicant': $('tbody').children().eq(i).children().eq(2).text()
            });
        }
    }
}
function SubmitByAjax(data, url){
    $.ajax({
        type: 'POST',
        dataType: 'JSON',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(data),
        url: url,  
        success: function(result){
            if(result.resultCode == 0){
                alert('提交成功！');
                refreshTable(3);
            }else{
               alert("提交失败");
            }
        } 
    });
}
$('#bulkAccept').click(function(){
    debugger;
    AddToSelectedApplication();
    if(selectedApplication.length == 0){
        alert('您当前还未选择任何夹具！');
    }else{
        var type=1;
        if(displayType=='Out'){
            type=3;
        }
        if(displayType=='In'){
            type=2;
        }
        if(displayType=='Repair'){
            type=4;
        }
        if(displayType=='Scrap'){
            type=5;
        }
        var orderId=[];
        for(var i=0;i<selectedApplication.length;i++){
            var o=selectedApplication[i].orderId;//订单编号
            orderId.push(parseInt(o));

        }
        var transData = {
            'type': type,
            'orderIds': orderId,
            'result':1
        }
        SubmitByAjax(transData, '/do/approval');
    }
    
});
$('#bulkReject').click(function(){
    debugger;
    AddToSelectedApplication();
    if(selectedApplication.length == 0){
        alert('您当前还未选择任何夹具！');
    }else{
        var type=1;
        if(displayType=='Out'){
            type=3;
        }
        if(displayType=='In'){
            type=2;
        }
        if(displayType=='Repair'){
            type=4;
        }
        if(displayType=='Scrap'){
            type=5;
        }
        var orderId=[];
        for(var i=0;i<selectedApplication.length;i++){
            var o=selectedApplication[i].orderId;//订单编号
            orderId.push(parseInt(o));

        }
        var transData = {
            'type': type,
            'orderIds': orderId,
            'result':2
        }
        SubmitByAjax(transData, '/do/approval');
    }
});
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 回到顶部悬浮按钮
$(window).on('load', function(){
    // 获取页面可视区域的高度
    var clientHeight = document.documentElement.clientHeight;
    var timer = null;// 定义定时器变量
    var isTop = true;// 是否返回顶部
    // 滚动滚动条时候触发
    $(window).on('scroll', function(){
        // 获取滚动条到顶部高度-返回顶部显示或者隐藏
        var osTop = document.documentElement.scrollTop || document.body.scrollTop;
        if (osTop >= clientHeight / 3) {
            $('#danglingBack').show();
        } else {
            $('#danglingBack').hide();
        }
        // 如果是用户触发滚动条就取消定时器
        if (!isTop) {
            clearInterval(timer);
        }
        isTop = false;
    });
    // 返回顶部按钮点击事件
    $('#danglingBack').click(function(){
        timer = setInterval(function() {
            // 获取滚动条到顶部高度
            var osTop = document.documentElement.scrollTop || document.body.scrollTop;
            var distance = Math.floor(-osTop / 6);
            document.documentElement.scrollTop = document.body.scrollTop = osTop + distance;
            isTop = true;
            if (osTop == 0) {
                clearInterval(timer);
            }
        }, 30);
    });
})
//#endregion