////////////////////////////////////////////////////////////////////////////////////////////////////////
//上传文件

$('#upload').change(function(){
    var reader = new FileReader();
    var file = $('#upload')[0].files[0];
    reader.onload = function(){
        var data = this.result;
        var wb = XLSX.read(data,{type:'binary'}) //利用XLSX解析二进制文件为xlsx对象
        var j_data = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]) //利用XLSX把wb第一个sheet转换成JSON对象
        //var str_data = eval(j_data);

        if($('table').children().length > 0){  //清空表格
            $('table').children().remove();
        }
        
        $('table').append("<thead><tr></tr></thead>");
        var col = 0;  //列数
        for(var th in j_data[0]){  //设置表头
            $("thead").children('tr').append("<th>" + th + "</th>");
            col++
        }

        $('table').append("<tbody></tbody>");
        for(var i = 0; i < j_data.length; i++){ 
            $('tbody').append('<tr></tr>');
            for(var td in j_data[i]){
                $('tbody').children('tr').eq(i).append('<td>' + j_data[i][td] + '</td>');
            }
        }
    }
    reader.readAsBinaryString(file);
});


//formData.append('files', $('#upload')[0].files[0]) ;
