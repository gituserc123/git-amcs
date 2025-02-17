define(["uploadImages"],function (uploadImages) {
    var typeArr = [{name:"电子病历",tag:"emr"},{name:"眼前节照相",tag:"front"},{name:"眼部B超",tag:"b"},{name:"其它相关比例附件",tag:"other"}];
    var fsInfo = {
        name : [],
        path : [],
        fileNameUUID : [],
        size : [],
        url:[]
    };
    let otherTypes = [];
    var basicId = null;
    var obj = {
        init:function(paramsObj){
            this.paramsObj = paramsObj;
            var $this = this;
            console.log("paramsObj",paramsObj);
            console.log(paramsObj.pagetType)
            if(paramsObj.pagetType == 5){
				typeArr = [{name:"眼前节照相",tag:"front"},{name:"眼部B超",tag:"b"},{name:"其它相关比例附件",tag:"other"}];
			}
            basicId = paramsObj.other.id;
            if(paramsObj.urlObj){//根据id查询以往的数据
                $ajax.post(paramsObj.urlObj.url,paramsObj.urlObj.urlParam).done(function (res) {
                    $this.loadData(res)
                });
            }
            this.initDom();
            this.initComponent();
            this.initUploadFile();
        },
        initDom : function () {
            var domStr = "<div id=\"wrap\">";
            typeArr.forEach(function(item){
                if(item.tag == "emr"){
                    var tmpStr = `<h2 class="h2-title-a">
                                <span class="s-title">${item.name}</span>
                            </h2>
                            <div style="border: 2px solid #e6e6e6;padding: 10px;margin: 10px;">
                              <div id="div_emr" style="text-align: center;border:2px dashed #e6e6e6;padding:10px">
                                    <span>暂无相关信息</span>
                              </div>      
                            </div>
                            `;
                    domStr += tmpStr;
                }else if(item.tag!='other'){
                    var tmpStr = `<h2 class="h2-title-a">
                                <span class="s-title">${item.name}</span>
                            </h2>
                            <div class="wrapper">
                                <div class="container">
                                    <div id="uploader-${item.tag}" class="GE-uploader">
                                        <div class="queueList">
                                            <div id="dndArea" class="placeholder">
                                                <div id="filePicker-${item.tag}" name="pickers"></div>
                                            </div>
                                        </div>
                                        <div class="statusBar" style="display:none;">
                                            <div class="progress">
                                                <span class="text">0%</span>
                                                <span class="percentage"></span>
                                            </div><div class="info"></div>
                                            <div class="btns" style="top: 0;">
                                                <div id="filePicker2-${item.tag}"></div>
                                                <div class="uploadBtn" style="display: none;">开始上传</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>`;
                    domStr += tmpStr;
                }else if(item.tag == 'other'){
                    var tmpStr = `<h2 class="h2-title-a">
                                <span class="s-title">${item.name}</span>
                            </h2>
                            <div style="border: 2px solid #e6e6e6;padding: 10px;margin: 10px;">
                              <div id="uploader2" style="text-align: center;border:2px dashed #e6e6e6;padding:10px"></div>      
                            </div>
                            `;
                    domStr += tmpStr;
                }
            });
            domStr+="</div>";
            $(".tab-img").append(domStr);
            $(".tab-img").append("<div style='text-align: center;'><button class=\"btn btn-large btn-primary img-subbtn\" type='button'>保 存</button></div>");

        },
        initUploadFile:function(){

          var kupload2 =$("#uploader2").powerWebUpload({
              auto: true,//自动上传
              fileNumLimit:10,
              serverDelete:false,
              upOpt :{
                  serverDelete:false,
                  server : base + '/ui/service/biz/amcs/adverse/aeFile/upload',//上传请求地址
                  // deleteServer :'/workItemInfo/deleteFile.jhtml',//删除请求地址,
                  accept :{//接收类型限制
                        extensions: '',
                        mimeTypes: '*/*'//限制文件类型用mimeTypes
                   }
              }
            ,beforeUpload : function (file) {
                kupload2.options.formData={//添加上传数据
                  itemId : $('#itemId').val()
                }
              }
              ,uploadSuccess : function (file,req) {
                  window.console && console.log(file,req);
                  var htmlStr = "<a href='"+req.data.url+"' style='cursor: pointer;' target='_blank'>"+file.name+"</a>"
                  $("#uploader2"+file.id).find("span.webuploadinfo").html(htmlStr);

                  var data = {basicId:basicId};
                    data.fileId = req.data.id;
                    data.tag = 'other';
                    data.id = null;
                    data.filesize = req.data.filesize;
                    data.url = req.data.url;
                    data.filename = req.data.fileName;
                    otherTypes.push(data);


                  // fsInfo.name.push(req.data.fileName);
                  // fsInfo.path.push(req.data.url);
                  // fsInfo.size.push(req.data.fileSize);
                  // fsInfo.fileNameUUID.push(req.data.id);
                  // fsInfo.url.push(req.data.url);

                  // c.basicId = basicId;
                  // c.fileId = c.id;
                  // c.tag = item.tag;
                  // c.id = null;

                  // $('#fileNames').val(fsInfo.name.join(','));
                  // $('#filePaths').val(fsInfo.path.join(','));
                  // $('#fileSizes').val(fsInfo.size.join(','));
                  // $('#fileNameUUID').val(fsInfo.fileNameUUID.join(','));//将上传成功返回的数据信息组合成字符串放入输入框
              },
              uploadDelSuccess : function(fileKey,file) {//删除成功后

                  // var arrPos = $.inArray(fileKey,fsInfo.path);
                  // fsInfo.name.splice(arrPos,1);
                  // fsInfo.path.splice(arrPos,1);
                  // fsInfo.size.splice(arrPos,1);
                  // fsInfo.fileNameUUID.splice(arrPos,1);
                  // window.console && console.log(arrPos);
                  // $('#fileNames').val(fsInfo.name.join(','));
                  // $('#filePaths').val(fsInfo.path.join(','));
                  // $('#fileSizes').val(fsInfo.size.join(','));
                  // $('#fileNameUUID').val(fsInfo.fileNameUUID.join(','));//从已有上传数据信息里去掉删除成功的数据信息
              },
              uploadFinished : function () {
                  window.console && console.log('upload finished');
                  //$util.closePop({refreshGrid:true});//关掉当前pop
              }
            });
        },
        initComponent:function(){
            typeArr.forEach(function(item){
                if(item.tag!='other'){
                    var uploadObj = new uploadImages({outWrap:'#uploader-'+item.tag,
                        addBtn:'#filePicker-'+item.tag,
                        addMoreBtn:'#filePicker2-'+item.tag,
                        formData:{'name':'test'},
                        maxFileNum:10,
                        serversUrl:`${base}/ui/service/biz/amcs/adverse/aeFile/upload`
                    },item.tag,{onDelCallBack:function (dom, obj) {
                            console.log(dom,obj);
                            // if(!!obj){
                            //     var tmp = typeArr.find(function(c){
                            //         return c.tag == obj.tag;
                            //     });
                            //     console.log(tmp.uploadObj);
                            //     tmp.uploadObj.delImage(obj);
                            // }
                        },onUploadSuccess:function(data){
                            console.log(typeArr);

                        }});

                    item.uploadObj = uploadObj;
                }
            });
            uploadImages.initView();
            $(".img-subbtn").on('click',function(){
				if(basicId == null){
					$pop.alert('请先保存事件明细');
     				return;
				}
				var upload = [];
				typeArr.forEach(function(item){
					console.log(item);
                    if(item.tag!='other'){
                        item.uploadObj.fileImages.forEach(function(c){
                            // if(c.tag == null){
                                c.basicId = basicId;
                                c.fileId = c.id;
                                c.tag = item.tag;
                                c.id = null;
                                upload.push(c);
                            // }
                        })
                    }
                });
				// 保存附件信息
                console.log('保存信息来了');
                console.log(upload);
                console.log(fsInfo)
                fsInfo.name.forEach(function(item,index){
                    var data = {basicId:basicId};
                    data.fileId = fsInfo.fileNameUUID[index];
                    data.tag = 'other';
                    data.id = null;
                    data.filesize = fsInfo.size[index];
                    data.url = fsInfo.url[index];
                    data.filename = fsInfo.name[index];
                    upload.push(data);
                });

                console.log(otherTypes);
                otherTypes.forEach(function(item){
                    if($('#uploader2').find("a[href='"+item.url+"']").length){
                        item.id = null;
                        upload.push(item);
                    }
                })
                console.log(upload);

				$ajax.post(`${base}/ui/service/biz/amcs/adverse/aeFile/save`, {"attachments": upload,basicId:basicId}, {jsonData:true, tip:false}).done(function(rst){
			        if (rst.code === '200' || rst.code === '201') {
			            $pop.success('保存成功!');
			        }
				});
            })
        },
        loadData:function(data){
            let items = data.rows;
            //----
            // items.push({"tag":"emr","url":"/aemro/ui/outp/docView/getOutpDoc?regNumber=MZ202211230003&outpRegistSource=2&templateType=1&hospId=9999"})
            //----
            let imgArr = [[],[],[]];

            $('#uploader2').on('click','.webuploadDelbtn',function(){
                   console.log('i am here')
                   let tmpId = $(this).data('info');
                   if(tmpId){
                       $("#"+tmpId).remove();
                   }
            });

            let fileUpload = $("#uploader2").find("#Uploadthelist");

            items.forEach(function(item){
                item.fileid = item.id;
                item.fileId = item.fileid;
                item.fileName = item.filename;
                if(item.tag == 'emr'){
                    var str = "<span id='viewemr' style='background-color: #eeeeee;padding: 5px 10px;display: inline-block;margin: 5px;border: 1px solid #dddddd;cursor: pointer;'>点击查看</span>"
                    $("#div_emr").html(str);
                    $("#viewemr").click(function(){
                        $pop.newTabWindow('电子病历',item.url);
                    });
                }else if(item.tag!='other'){
                    var index = typeArr.findIndex(function(i){
                        return item.tag == i.tag;
                    });
                    if(index != -1){
                        imgArr[index].push(item)
                    }
                }else if(item.tag == 'other'){//其它附件，特殊处理
                    item.basicId = basicId;
                    otherTypes.push(item);

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', base + '/ui/service/biz/amcs/adverse/aeFile/decryptFile', true);
                    xhr.setRequestHeader('Content-Type', 'application/json');
                    xhr.responseType = 'arraybuffer'; // Set response type

                    xhr.onload = function() {
                        if (xhr.status === 200) {
                            let mimeType = xhr.getResponseHeader('Content-Type');
                            let blob = new Blob([xhr.response], { type: mimeType });
                            let url = URL.createObjectURL(blob);
                            let htmlstr = `<div class="item" id="${item.fileId}">
                    <span class="webuploadinfo">
                        <a href="${url}" style='cursor: pointer;' target='_blank'>${item.filename}</a>
                    </span>
                    <div class="webuploadDelbtn" data-info="${item.fileId}">删除</div>
                </div>`;

                            fileUpload.append(htmlstr);
                        }
                    };

                    xhr.onerror = function() {
                        console.error('Request failed');
                    };

                    xhr.send(JSON.stringify(item.url));
                }
            });
            for(var index = 0; index <3; index ++){
				typeArr[index].uploadObj.loadExitsImgs(imgArr[index]);
			}
            
        },
        getData:function(){
            return typeArr;
        }
    };

    return obj;
});

