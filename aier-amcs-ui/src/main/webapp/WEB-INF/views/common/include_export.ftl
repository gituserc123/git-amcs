<script type="text/javascript" src="${ctx}/static/js/xlsx.full.min.js"></script>
<script type='text/javascript'>
    //打印和导出
    (function($){
        function getRows(target){
            var state = $(target).data('datagrid');
            if (state.filterSource){
                return state.filterSource.rows;
            } else {
                return state.data.rows;
            }
        }
        function getFooterRows(target){
            var state = $(target).data('datagrid');
            return state.data.footer || [];
        }
        function toHtml(target, rows, footer, caption){
            rows = rows || getRows(target);
            rows = rows.concat(footer||getFooterRows(target));
            var dg = $(target);
            var data = ['<table border="1" rull="all" style="border-collapse:collapse">'];
            var fields = dg.datagrid('getColumnFields',true).concat(dg.datagrid('getColumnFields',false));
            var trStyle = 'height:32px';
            var tdStyle0 = 'vertical-align:middle;padding:0 4px';
            if (caption){
                data.push('<caption>'+caption+'</caption>');
            }
            data.push('<tr style="'+trStyle+'">');
            for(var i=0; i<fields.length; i++){
                var col = dg.datagrid('getColumnOption', fields[i]);
                var tdStyle = tdStyle0 + ';width:'+col.boxWidth+'px;';
                tdStyle += ';text-align:'+(col.halign||col.align||'');
                data.push('<td style="'+tdStyle+'">'+col.title+'</td>');
            }
            data.push('</tr>');
            $.map(rows, function(row){
                data.push('<tr style="'+trStyle+'">');
                for(var i=0; i<fields.length; i++){
                    var field = fields[i];
                    var col   = dg.datagrid('getColumnOption', field);
                    var value = row[field];
                    if (value == undefined){
                        value = '';
                    }
                    var tdStyle = tdStyle0;
                    tdStyle += ';text-align:'+(col.align||'');
                    data.push(
                        '<td style="'+tdStyle+'">'+value+'</td>'
                    );
                }
                data.push('</tr>');
            });
            data.push('</table>');
            return data.join('');
        }

        function toArray(target, rows){
            rows = rows || getRows(target);
            var dg = $(target);
            var fields = dg.datagrid('getColumnFields',true).concat(dg.datagrid('getColumnFields',false));
            var data = [];
            var r = [];
            for(var i=0; i<fields.length; i++){
                var col = dg.datagrid('getColumnOption', fields[i]);
                r.push(col.title);
            }
            data.push(r);
            $.map(rows, function(row){
                var r = [];
                for(var i=0; i<fields.length; i++){
                    r.push(row[fields[i]]);
                }
                data.push(r);
            });
            return data;
        }

        function print(target, param){
            var title = null;
            var rows = null;
            var footer = null;
            var caption = null;
            if (typeof param == 'string'){
                title = param;
            } else {
                title = param['title'];
                rows = param['rows'];
                footer = param['footer'];
                caption = param['caption'];
            }
            var newWindow = window.open('', '', 'width=800, height=500');
            var document = newWindow.document.open();
            var content =
                '<!doctype html>' +
                '<html>' +
                '<head>' +
                '<meta charset="utf-8">' +
                '<title>'+title+'</title>' +
                '</head>' +
                '<body>' + toHtml(target, rows, footer, caption) + '</body>' +
                '</html>';
            document.write(content);
            document.close();
            newWindow.print();
        }

        function b64toBlob(data){
            var sliceSize = 512;
            var chars = atob(data);
            var byteArrays = [];
            for(var offset=0; offset<chars.length; offset+=sliceSize){
                var slice = chars.slice(offset, offset+sliceSize);
                var byteNumbers = new Array(slice.length);
                for(var i=0; i<slice.length; i++){
                    byteNumbers[i] = slice.charCodeAt(i);
                }
                var byteArray = new Uint8Array(byteNumbers);
                byteArrays.push(byteArray);
            }
            return new Blob(byteArrays, {
                type: ''
            });
        }

        function toExcel(target, param){
            debugger;
            var filename = null;
            var rows = null;
            var footer = null;
            var caption = null;
            var worksheet = 'Worksheet';
            if (typeof param == 'string'){
                filename = param;
            } else {
                filename = param['filename'];
                rows = param['rows'];
                footer = param['footer'];
                caption = param['caption'];
                worksheet = param['worksheet'] || 'Worksheet';
            }
            var dg = $(target);
            var uri = 'data:application/vnd.ms-excel;base64,'
                , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body>{table}</body></html>'
                , base64 = function (s) { return window.btoa(unescape(encodeURIComponent(s))) }
                , format = function (s, c) { return s.replace(/{(\w+)}/g, function (m, p) { return c[p]; }) }

            var table = toHtml(target, rows, footer, caption);
            var ctx = { worksheet: worksheet, table: table };
            var data = base64(format(template, ctx));
            if (window.navigator.msSaveBlob){
                var blob = b64toBlob(data);
                window.navigator.msSaveBlob(blob, filename);
            } else {
                var alink = $('<a style="display:none"></a>').appendTo('body');
                alink[0].href = uri + data;
                alink[0].download = filename;
                alink[0].click();
                alink.remove();
            }
        }

        $.extend($.fn.datagrid.methods, {
            toHtml: function(jq, rows){
                return toHtml(jq[0], rows);
            },
            toArray: function(jq, rows){
                return toArray(jq[0], rows);
            },
            toExcel: function(jq, param){
                return jq.each(function(){
                    toExcel(this, param);
                });
            },
            print: function(jq, param){
                return jq.each(function(){
                    print(this, param);
                });
            }
        });
    })(jQuery);








    //导出功能函数

    function exportcalcdetail() {
        var wopts = {
            bookType: 'xlsx',
            bookSST: false,
            type: 'binary'
        };
        var workBook = {
            SheetNames: ['Sheet1'],
            Sheets: {},
            Props: {}
        };
        function json2Excel() {
            //待展示的数据，可能是从后台返回的json数据或者是自己定义的object fapztable
            var dataList = layui.treeGrid.cache['fapztable'].data.list;
            //var dataexport = JSON.parse(JSON.stringify(dataList));
            var  dataexport =  new Array();
            for (var  i=0; i< dataList.length; i++) {
                var ObjOneThis = dataList[i];
                var ObjOne={};
                //字段信息
                ObjOne.unitclass=ObjOneThis.unitclass;
                ObjOne.UNITCODE=ObjOneThis.UNITCODE;
                ObjOne.UNITNAME=ObjOneThis.UNITNAME;
                dataexport.push(ObjOne);
            }

            //展示的顺序，把data中对象的属性按照你想要的顺序排放就可以了
            var header = ["unitclass", "UNITCODE","UNITNAME"];
            //展示的名称
            var headerDisplay = {unitclass:"分摊类别", UNITCODE:"核算单元代码", UNITNAME:"核算单元名称"};
            //将表头放到原始数据里面去，要保证表头在数组的最前面
            var newData = [headerDisplay, ...dataexport];

            //加了一句skipHeader:true，这样就会忽略原来的表头
            workBook.Sheets['Sheet1'] = XLSX.utils.json_to_sheet(newData, {header:header, skipHeader:true});

            //1、XLSX.utils.json_to_sheet(data) 接收一个对象数组并返回一个基于对象关键字自动生成的“标题”的工作表，
            // 默认的列顺序由使用Object.keys的字段的第一次出现确定
            //2、将数据放入对象workBook的Sheets中等待输出
            //workBook.Sheets['Sheet1'] = XLSX.utils.json_to_sheet(dataSource)

            //3、XLSX.write() 开始编写Excel表格
            //4、changeData() 将数据处理成需要输出的格式
            //saveAs(new Blob([changeData(XLSX.write(workBook, wopts))], {type: 'application/octet-stream'}))
            openDownloadDialog(new Blob([changeData(XLSX.write(workBook, wopts))], {type: 'application/octet-stream'}),'文件名.xlsx');
        }

        function openDownloadDialog(url, saveName) {
            if (typeof url == 'object' && url instanceof Blob) {
                url = URL.createObjectURL(url); // 创建blob地址
            }
            var aLink = document.createElement('a');
            aLink.href = url;
            aLink.download = saveName || ''; // HTML5新增的属性，指定保存文件名，可以不要后缀，注意，file:///模式下不会生效
            var event;
            if (window.MouseEvent) event = new MouseEvent('click');
            else {
                event = document.createEvent('MouseEvents');
                event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
            }
            aLink.dispatchEvent(event);
        }

        function changeData(s) {

            //如果存在ArrayBuffer对象(es6) 最好采用该对象
            if (typeof ArrayBuffer !== 'undefined') {

                //1、创建一个字节长度为s.length的内存区域
                var buf = new ArrayBuffer(s.length);

                //2、创建一个指向buf的Unit8视图，开始于字节0，直到缓冲区的末尾
                var view = new Uint8Array(buf);

                //3、返回指定位置的字符的Unicode编码
                for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
                return buf;

            } else {
                var buf = new Array(s.length);
                for (var i = 0; i != s.length; ++i) buf[i] = s.charCodeAt(i) & 0xFF;
                return buf;
            }
        }
        json2Excel();
    }


</script>