
    var showMask = function () {
        var wrap = $(".win-mask");
        $("<div class=\"datagrid-mask\"></div>").css({
            display: "block",
            width: window.width,
            height: window.height
        }).appendTo(wrap);
    };

    var rmMask = function () {
        var wrap = $(".win-mask");
        wrap.find("div.datagrid-mask").remove();
    }

    function downloadExcelProcess(url) {
        // 开启遮罩
        showMask();
        //$pop.alert('文件正在导出，请耐心等待....');
        $pop.msg('文件正在导出，请耐心等待....', 3000, {icon: 2});
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.responseType = "blob"; // 返回类型blob
        // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
        xhr.onload = function () {
            // 请求完成
            if (this.status === 200) {
                var type = xhr.getResponseHeader('Content-Type');
                var blob = new Blob([this.response], {
                    type: type
                });
                var fileName = xhr.getResponseHeader("content-disposition");
                fileName = decodeURI(fileName.split(";")[1].split("filename=")[1].trim('"'));
                if (typeof window.navigator.msSaveBlob !== 'undefined') {
                    // For IE>=IE10
                    window.navigator.msSaveBlob(blob, fileName);
                } else {
                    // For chrome, firefox
                    var URL = window.URL || window.webkitURL;
                    var objectUrl = URL.createObjectURL(blob);
                    if (fileName) {
                        // 创建一个a标签用于下载
                        var a = document.createElement('a');
                        a.download = fileName;
                        a.href = objectUrl;
                        $("body").append(a);
                        a.click();
                        $(a).remove();
                    } else {
                        window.location = objectUrl;
                    }
                }
            } else {
                $pop.alert('导出失败！');
            }
            // 关闭遮罩代码
            rmMask();
        };
        // 发送ajax请求
        xhr.send();
    };
