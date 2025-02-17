define(['pub'],function (){

  /*

  * 通用导出excel函数
  *  excel: (
  *   data: Record<string,string|number>[], //导出数据
  *   cols: gridCol[], //导出对应的列 ，有 field,title, hidden?
  *   fileName: string //导出文件名
  *  ) => excel下载文件
  *
  *
  * 根据 grid 导出excel
  * gridToExcel(opt)
  * opt : {
  *     grid: '#gridBox', // grid id，默认为 #gridBox
  *     fileName : '下载文件', //导出文件名，默认为 下载文件
  *     cols : null, // 导出列，同 excel方法 cols
  *     data : null //导出数据 ，同 excel方法 data
  * }
  * */

  function base64 (content) {
    return window.btoa(unescape(encodeURIComponent(content)));
  }

  var tools = {
    excel : function (data,cols,fileName){
      //导出前要将json转成table格式
      var str = '<tr>';
      $.each(cols,function (i,v){
        str += '<td>'+v.title+'</td>';
      });
      str += '</tr>';

      $.each(data,function (i,v){
        str += '<tr>';
        $.each(cols,function (j,w){
          var txt = (typeof(v[w.field]) === 'undefined' || v[w.field] === null)?'':v[w.field];
          str += '<td style="mso-number-format:\'\@\';">'+txt+'</td>';
        });
        str += '</tr>';
      });

      var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'><head><meta charset='UTF-8'><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>";
      excelFile += str;
      excelFile += "</table></body></html>";

      var a = document.createElement("a");
      // var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
      // a.href = link;
      var blob = new Blob([excelFile]);
      a.href = URL.createObjectURL(blob);
      a.download = (fileName || '文件下载') + ".xls";
      a.click();
    },
    gridToExcel : function (opt){
      var  o = $.extend({
        grid: '#gridBox',
        fileName : '下载文件',
        cols : null,
        data : null
      },opt || {});

      var $grid = $(o.grid);
      var data = o.data,cols = o.cols;
      if($grid){
        (!data) && (data = $grid.datagrid('getRows'));
        (!cols) && (cols = $grid.datagrid('options').columns[0]);
        var newCols = [];
        $.each(cols,function (i,v){
          (!v.hidden) && newCols.push(v);
        });
        this.excel(data,newCols,o.fileName);
      }else{
        $pop.msg('没有设置正确的gridId！');
      }
    }
  }

  return tools;

});