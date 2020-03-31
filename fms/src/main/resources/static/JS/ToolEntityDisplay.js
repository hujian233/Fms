////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//定义全局变量
var initData = [];
var searchType = '';
var bulkOperType = '';
var cacheTbodyType = 'Out';
var pageSize = 20;              //一页最多显示16条信息
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 获取定义列表、刷新待申请列表（函数）
$(window).on('load', function () {
    var code = getUrlVars()['code'];
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        // url: '../TestData/ToolEntityList.json',  //后端Url，附加code参数
        url: '/fixture/queryEntity?code=' + code,
        success: function (result) {
            function compare(a, b) {
                if (a.status == '0' && b.status == '0') return 0;
                else if (a.status == '0') return -5;
                else if (b.status == '0') return 5;
                else return a.status.localeCompare(b.status);
            }

            initData = result.sort(compare);    //将实体数据按照状态排序  可用放最前
            displayTable(initData);
        },
        error: function () {
            alert('获取信息失败，请稍后重试...');
        }
    });
    refleshCache();
});

function displayTable(data) {
    debugger;
    if (data.length == 0) {
        $('#definitionTbody').empty();
        return;
    }
    $('#paginationToolEntity').jqPaginator({
        first: '<li class="first"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;"><<</a></li>',
        next: '<li class="next"><a href="javascript:;">>></a></li>',
        last: '<li class="last"><a href="javascript:;">末页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        totalPages: Math.ceil(data.length / pageSize),
        totalCounts: data.length,
        pageSize: pageSize,
        onPageChange: function (num) {
            $('#definitionTbody').empty();
            var begin = (num - 1) * pageSize;
            var n = 1;
            for (var i = begin; i < data.length && i < begin + pageSize; i++) {
                var appendData =
                    '<tr><th>' + n
                    + '</th><td>' + data[i].code
                    + '</td><td>' + data[i].seqId
                    + '</td><td>' + data[i].regDate
                    + '</td><td>' + data[i].usedCount
                    + '</td><td>' + data[i].status
                    + '</td><td><button class="btn act-btn" onclick="getInfo(this);">查看详情</button>';

                switch (data[i].status) {    //根据夹具不同状态设定不同的操作
                    /*  0：已入库（可用） */
                    case 0:
                        appendData += '<button class="btn act-btn" onclick="addToOut(this);">出库</button>'
                            + '<button class="btn act-btn" onclick="addToRepair(this);">报修</button>'
                            + '<button class="btn act-btn" onclick="addToScrap(this);">报废</button></td></tr>';
                        $('#definitionTbody').append(appendData);
                        break;
                    /*  6:已出库 */

                    case '6':
                        appendData += '<button class="btn act-btn" onclick="addToIn(this);">入库</button>'
                            + '<button class="btn act-btn" onclick="addToRepair(this);">报修</button>'
                            + '<button class="btn act-btn" onclick="addToScrap(this);">报废</button></td></tr>';
                        $('#definitionTbody').append(appendData);
                        break;
                    /*  7：已报修 */
                    case '7':
                        appendData += '<button class="btn act-btn" onclick="addToIn(this);">入库</button>'
                            + '<button class="btn act-btn" onclick="addToScrap(this);">报废</button></td></tr>';
                        $('#definitionTbody').append(appendData);
                        break;
                    /*  8:已报废 */
                    case '8':
                        appendData += '</td></tr>';
                        $('#definitionTbody').append(appendData);
                        break;
                    /*  2:待入库 */

                    case '2':
                        appendData += '<button class="btn act-btn" onclick="addToRepair(this);">报修</button>'
                            + '<button class="btn act-btn" onclick="addToScrap(this);">报废</button></td></tr>';
                        $('#definitionTbody').append(appendData);
                        break;


                    /* case '待点检':
                         appendData += '<button class="btn act-btn" onclick="addToRepair(this);">报修</button>'
                             + '<button class="btn act-btn" onclick="addToScrap(this);">报废</button></td></tr>';
                         $('#definitionTbody').append(appendData);
                         break;*/
                    default:
                        $('#definitionTbody').append(appendData);
                }
                n++;   //当前页面序号
            }
        }
    });
}

//查询右上角小框框的显示
function refleshCache() {
    $('#outTbody').empty();
    $('#inTbody').empty();
    $('#repairTbody').empty();
    $('#scrapTbody').empty();
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        //  url: '../TestData/CacheList.json',   //待改  后端URL
        url: '/fixture/querywaitsubmit',
        success: function (result) {
            function addToCacheTbody(tbodyID, len, array) {    //将数据分类归入不同表格
                for (var i = 0; i < len; i++) {
                    $('#' + tbodyID).append('<tr><td>' + array[i].code
                        + '</td><td>' + array[i].seqId
                        + '</td><td><i class="glyphicon glyphicon-remove cache-remove-icon" onclick="remove(this);"></i></td></tr>')
                }
            }

            if (result.length > 0) {
                //出库
                var out = [];
                //入库
                var ins = [];
                //报修
                var Repair = [];
                //报废
                var Scrap = [];
                for (var i = 0; i < result.length; i++) {
                    var status = result[i].status;
                    //待出库
                    if (status == 3) {
                        out.push(result[i]);
                    }
                    //待入库
                    if (status == 2) {
                        ins.push(result[i]);
                    }
                    //代报修
                    if (status == 4) {
                        Repair.push(result[i]);
                    }
                    //待报废
                    if (status == 5) {
                        Scrap.push(result[i]);
                    }
                }
                addToCacheTbody('outTbody', out.length, out);
                addToCacheTbody('inTbody', ins.length, ins);
                addToCacheTbody('repairTbody', Repair.length, Repair);
                addToCacheTbody('scrapTbody', Scrap.length, Scrap);
            }

        },
        error: function () {
            alert('待提交申请中，部分夹具被其他用户操作，系统已自动将其去除');
        }
    });
}

//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 查找
function chooseSearchType(e) {
    $('#searchTypeBtn').text($(e).text());
    searchType = $(e).text();
    $('#paramInput').focus();
}

//页面点击查询按钮
$('#searchBtn').click(function () {
    debugger;
    var param = $('#paramInput').val();
    switch (searchType) {
        case '按使用次数':
            // displayTable(initData.filter((item) = > {return item.usedCount == param})
            displayTable(initData.filter(function (item) {
                    return item.usedCount == param
                })
            )
            ;
            break;
        case '按夹具状态':
            // displayTable(initData.filter((item) = > {return item.status == param})
            displayTable(initData.filter(function (item) {
                    return item.status == param
                })
            )
            ;
            break;
        default:
            $('#paramInput').val('');
            displayTable(initData);
    }
});
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 批量加入申请列表
function submitByAjax(data) {          //url待填
    var code = data[0].code.toString();
    var seqId = data[0].seqId.toString();
    var type = data[0].type;
    $.ajax({
        type: 'get',
        dataType: 'JSON',
        // contentType: 'application/json;charset=UTF-8',
        // data: JSON.stringify(data),
        url: '/do/operation/' + type + "/update" + "?code=" + code + "&seqId=" + seqId,
        // url: '',
        success: function (result) {
            if (result.Status == 'error') {
                alert('操作失败，请稍后重试...');
            } else {
                alert('操作成功！');
                //重新查询数据
                //重新查询数据start
                var code = getUrlVars()['code'];
                // alert("code="+code);
                $.ajax({
                    type: 'GET',
                    dataType: 'JSON',
                    // url: '../TestData/ToolEntityList.json',  //后端Url，附加code参数
                    url: '/fixture/queryEntity?code=' + code,
                    success: function (result) {
                        function compare(a, b) {
                            if (a.status == '0' && b.status == '0') return 0;
                            else if (a.status == '0') return -5;
                            else if (b.status == '0') return 5;
                            else return a.status.localeCompare(b.status);
                        }

                        initData = result.sort(compare);    //将实体数据按照状态排序  可用放最前
                        displayTable(initData);
                        //重新刷新小框数据
                        refleshCache();
                    },
                    error: function () {
                        alert('获取信息失败，请稍后重试...');
                    }
                });
                //重新查询数据end
                refreshTable();
            }
        }
    });
}

function chooseBulkOperType(e) {
    $('#bulkOperTypeBtn').text($(e).text());
    bulkOperType = $(e).text();
    $('#num1Input').focus();
}

$('#bulkOperBtn').click(function () {
    var transData = [];
    var displayedData = $('#definitionTbody').children();
    var num1 = Number.parseInt($('#num1Input').val());
    var num2 = Number.parseInt($('#num2Input').val());
    try {
        if (!(num1 >= 1 && num2 <= pageSize && num1 <= num2 && Number.isInteger(num1)) && Number.isInteger(num2))
            throw '序号格式错误';
        switch (bulkOperType) {
            case '出库':
                for (let i = num1; i < num2; i++) {
                    let state = displayedData.eq(i).children().eq(5).text();
                    if (state == '可用')
                        transData.push({
                            'Code': displayedData.eq(i).children().eq(1).text(),
                            'SeqID': displayedData.eq(i).children().eq(2).text(),
                            'Type': 'Out'
                        });
                    else throw '夹具选择错误';
                }
                break;
            case '入库':
                for (let i = num1; i < num2; i++) {
                    let state = displayedData.eq(i).children().eq(5).text();
                    if (state == '已报修' || state == '已出库')
                        transData.push({
                            'Code': displayedData.eq(i).children().eq(1).text(),
                            'SeqID': displayedData.eq(i).children().eq(2).text(),
                            'Type': 'Out'
                        });
                    else throw '夹具选择错误';
                }
                break;
            case '报废':
                for (let i = num1; i < num2; i++) {
                    let state = displayedData.eq(i).children().eq(5).text();
                    if (state != '已报废')
                        transData.push({
                            'Code': displayedData.eq(i).children().eq(1).text(),
                            'SeqID': displayedData.eq(i).children().eq(2).text(),
                            'Type': 'Out'
                        });
                    else throw '夹具选择错误';
                }
                break;
        }
        submitByAjax(transData);
    } catch (err) {
        alert(err + '，请重新填写...');
    }
})
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 获取夹具定义的详细信息
function getInfo(e) {
    var code = $(e).parent().parent().children().eq(0).text();
    var seqId = $(e).parent().parent().children().eq(1).text();
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        //  url: '../TestData/ToolEntityInfo.json',  //code附在url后  '...?Code=' + code + '&SeqID=' + seqID
        url: '/fixture/queryEntity?code=' + code + "&seqId=" + seqId,
        success: function (result1) {
            var result = result1[0];
            $('#Code').text(result.code);
            $('#SeqID').text(result.seqId);
            $('#RegDate').text(result.regDate);
            $('#UsedCount').text(result.usedCount);
            $('#State').text(result.status);
            $('#BillNo').text(result.billNo);
            $('#StoreHouse').text(result.location);
            /*  $('#LastTestTime').text(result.LastTestTime);
              $('#TotalUsedTime').text(result.TotalUsedTime);*/
            //$('#Image').attr('src', result.Image);
        },
        error: function () {
            alert('获取数据失败，请稍后重试...')
        }
    });

    $('#InfoModal').modal('show');
}

//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region  加入临时申请列表
// 加入出库单  type=3出库操作
function addToOut(e) {
    var code = $(e).parent().parent().children().eq(1).text();
    var seqId = $(e).parent().parent().children().eq(2).text();
    var transData = [];
    transData.push({'code': code, 'seqId': seqId, 'type': 3});
    submitByAjax(transData)

    // refleshCache();
}

//加入入库单  type=1:入库操作
function addToIn(e) {
    var code = $(e).parent().parent().children().eq(1).text();
    var seqId = $(e).parent().parent().children().eq(2).text();
    var transData = [];
    transData.push({'code': code, 'seqId': seqId, 'type': 1});
    submitByAjax(transData)
    //放到后面刷新，此处注释掉
    // refleshCache();
}

// 加入报修单
function addToRepair(e) {
    var code = $(e).parent().parent().children().eq(1).text();
    var seqId = $(e).parent().parent().children().eq(2).text();
    $('#repairCode').val(code);
    $('#repairSeqID').val(seqId);
    $('#RepairModal').modal('show');
}

//预览图片
$('#repairImageInput').change(function () {
    f = document.getElementById('repairImageInput').files[0];
    var fileReader = new FileReader();
    fileReader.readAsDataURL(f);
    fileReader.onload = function () {
        $('#repairImage').attr('src', fileReader.result);
        $('.imageScan').addClass('nodisplay');
    }
});

// 加入报废单  type=5报废操作
function addToScrap(e) {
    var code = $(e).parent().parent().children().eq(1).text();
    var seqId = $(e).parent().parent().children().eq(2).text();
    var transData = [];
    //transData.push({'Code': code, 'SeqID': seqID, 'Type': 'Scrap'});
    transData.push({'code': code, 'seqId': seqId, 'type': 5});
    submitByAjax(transData)
    //放到后面刷新，此处注释掉
    // refleshCache();
}

//报修提交  后面增加的    4：待报修状态
function submitBaoxiu() {
    /* var code = $(e).parent().parent().children().eq(0).text();
     var seqId = $(e).parent().parent().children().eq(1).text();*/
    var code = $("#repairCode").val();
    // alert("code=" + code);
    var seqId = $("#repairSeqID").val();
    // alert("seqId=" + seqId);
    var transData = [];
    //transData.push({'Code': code, 'SeqID': seqID, 'Type': 'Scrap'});
    transData.push({'code': code, 'seqId': seqId, 'type': 4});
    submitByAjax(transData)

//放到后面刷新，此处注释掉
    // refleshCache();

}

//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region  缓存窗
//缓存窗隐藏
$('.cache-icon').click(function () {
    if ($('.cache-content').css('display') == 'none') {
        $('.cache-tab').show();
        $('.cache-content').show();
        $('.cache-title').show();
        $('.cache-box').width(300);
    } else {
        $('.cache-tab').hide();
        $('.cache-content').hide();
        $('.cache-title').hide();
        $('.cache-box').width(56);
    }
});

//缓存窗tab按钮
function hideAllCacheTbody() {
    for (var i = 1; i < 5; i++) {
        $('#cacheTable').children().eq(i).hide();
    }
}

function changeTab(e) {
    if ($(e).text() == '出库') {
        hideAllCacheTbody();
        $('#outTbody').show();
        cacheTbodyType = 'Out';
    } else if ($(e).text() == '入库') {
        hideAllCacheTbody();
        $('#inTbody').show();
        cacheTbodyType = 'In';
    } else if ($(e).text() == '报修') {
        hideAllCacheTbody();
        $('#repairTbody').show();
        cacheTbodyType = 'Repair';
    } else {
        hideAllCacheTbody();
        $('#scrapTbody').show();
        cacheTbodyType = 'Scrap';
    }
    $(e).parent().children().eq(0).removeClass('a-active');
    $(e).parent().children().eq(1).removeClass('a-active');
    $(e).parent().children().eq(2).removeClass('a-active');
    $(e).parent().children().eq(3).removeClass('a-active');
    $(e).addClass('a-active');
}

//缓存窗单个清除按钮
function remove(e) {
    var code = $(e).parent().parent().children().eq(0).text();
    var seqID = $(e).parent().parent().children().eq(1).text();
    var transData = [];
    transData.push({'Code': code, 'SeqID': seqID, 'Type': cacheTbodyType});
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

//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 获取Url查询字符串
function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 滚动条监听，操作窗固定
$(window).scroll(function () {
    if ($(window).scrollTop() > 100)
        $('.oper-box').addClass('oper-box-sticky');
    else
        $('.oper-box').removeClass('oper-box-sticky');
})
//#endregion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//#region 回到顶部悬浮按钮
$(window).on('load', function () {
    // 获取页面可视区域的高度
    var clientHeight = document.documentElement.clientHeight;
    var timer = null;// 定义定时器变量
    var isTop = true;// 是否返回顶部
    // 滚动滚动条时候触发
    $(window).on('scroll', function () {
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
    $('#danglingBack').click(function () {
        timer = setInterval(function () {
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