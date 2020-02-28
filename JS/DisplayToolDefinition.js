////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//获取定义列表
$(window).on('load', function(){
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        url: '../TestData/ToolDefinitionList.json',  //后端Url，待改
        success: function(data){
            for(var i = 0; i < data.length; i++){
                $('tbody').append('<tr><td>' + data[i]['Code']
                + '</td><td>' + data[i]['Name']
                + '</td><td>' + data[i]['Family']
                + '</td><td>' + data[i]['Model']
                + '</td><td>' + data[i]['PartNo']
                +'</td><td><button class="btn act-btn" onclick="getInfo(this);">查看详情</button>'
                + '<button class="btn act-btn" onclick="getEntity(this);">查看实体</button></td></tr>');
            }
        },
        error: function(){
            alert('获取信息失败，请稍后重试...');
        }
    })
});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//获取夹具定义的详细信息
function getInfo(e){
    var code = $(e).parent().parent().children().eq(0).text();
    $.ajax({
        type: "GET",
        dataType: "JSON",
        url: "../TestData/ToolDefinitionInfo.json",  //code附在url后  "...?code=" + code
        success: function(data){
            
            $('#Code').text(data.Code);
            $('#Name').text(data.Name);
            $('#Family').text(data.Family);
            $('#Model').text(data.Model);
            $('#PartNo').text(data.PartNo);
            $('#UPL').text(data.UPL);
            $('#UsedFor').text(data.UsedFor);
            $('#PMPeriod').text(data.PMPeriod);
            $('#PMContent').text(data.PMContent);
            $('#OwnerID').text(data.OwnerID);
            $('#OwnerName').text(data.OwnerName);
            $('#RecOn').text(data.RecOn);
            $('#RecorderID').text(data.RecorderID);
            $('#RecorderName').text(data.RecorderName);
            $('#EditOn').text(data.EditOn);
            $('#EditorID').text(data.EditorID);
            $('#EditorName').text(data.EditorName);
            $('#Workcell').text(data.Workcell);
        },
        error: function(){
            alert('获取数据失败，请稍后重试...')
        }
    });


    $('#InfoModal').modal('show');
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//获取夹具实体列表
function getEntity(e){
    var code = $(e).parent().parent().children().eq(0).text();
    window.location = '../HTML/DisplayToolEntity.html?code=' + code;
}