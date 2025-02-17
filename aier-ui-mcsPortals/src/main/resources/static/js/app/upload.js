define(['myupload'],function () {
  var back = {
    upload : function () {
//      window.console && console.log('upload doing');
      $(function () {

        //上传方式1
        var kupload = $("#uploader").powerWebUpload({
          auto: false,
          fileNumLimit:2,//文件个数
          upOpt :{
            server : '/knowledge/upload.jhtml',
            deleteServer :'/knowledge/deleteFile.jhtml'
            // fileSingleSizeLimit : 20*1024*1024,//默认限制文件大小20M
            // accept :{//接收类型
            //   extensions : 'doc,docx,ppt,pptx,xls,xlsx,pdf,jpg,png,gif,txt'
            // }
          }
          ,beforeUpload : function (file) {
            kupload.options.formData={
              dept : $('#dept').val()
            }
//            window.console && console.log(kupload.options.formData);
            return $('.form-validate').form("validate");//返回表单验证状态
          }
          ,uploadSuccess : function (file,response) {
//            window.console && console.log(file,response);
          },
          uploadFinished : function () {
            if ($('.uploader-list').find('.item').length) {//上传直接提交表单，关闭pop弹窗
//              window.console && console.log('upload finished');
              $pop.closePop({refreshGrid:true});//关掉当前pop
            };
          }
        });
        // kupload.options.formData = {a:1,b:2,c:3};
        // window.console && console.log(kupload);
      });

    //上传方式2
      var fsInfo = {
        name : [],
        path : [],
        fileNameUUID : [],
        size : []
      };
      var kupload2 =$("#uploader2").powerWebUpload({
          auto: true,fileNumLimit:2,
        // fileKey : 'filePath',
          upOpt :{
              server : '/workItemInfo/upload.jhtml',
              deleteServer :'/workItemInfo/deleteFile.jhtml'
          }
        ,beforeUpload : function (file) {
            kupload2.options.formData={
              itemId : $('#itemId').val()
            }
//            window.console && console.log(kupload2.options.formData);
              //return $hook.validate().form();//返回表单验证状态
            }
            ,uploadSuccess : function (file,req) {
//              window.console && console.log(file,req);
              fsInfo.name.push(req.fileName);
              fsInfo.path.push(req.filePath);
              fsInfo.size.push(req.fileSize);
              fsInfo.fileNameUUID.push(req.fileNameUUID);
              $('#fileNames').val(fsInfo.name.join(','));
              $('#filePaths').val(fsInfo.path.join(','));
              $('#fileSizes').val(fsInfo.size.join(','));
              $('#fileNameUUID').val(fsInfo.fileNameUUID.join(','));
            },
            uploadDelSuccess : function(fileKey,file) {//删除成功后
              var arrPos = $.inArray(fileKey,fsInfo.path);
              fsInfo.name.splice(arrPos,1);
              fsInfo.path.splice(arrPos,1);
              fsInfo.size.splice(arrPos,1);
              fsInfo.fileNameUUID.splice(arrPos,1);
//              window.console && console.log(arrPos);
              $('#fileNames').val(fsInfo.name.join(','));
              $('#filePaths').val(fsInfo.path.join(','));
              $('#fileSizes').val(fsInfo.size.join(','));
              $('#fileNameUUID').val(fsInfo.fileNameUUID.join(','));
            },
            uploadFinished : function () {
//              window.console && console.log('upload finished');
              //$pop.closePop({refreshGrid:true});//关掉当前pop
            }
        });

      window.afterSubmitMessage =  function () {
        window.location.reload();
      }



    //上传方式3
        var kupload3 = $(".uploadS").powerWebUpload({
          fileNumLimit:2,//文件个数
          btnStyle : true,
          uploadBtnId : 'btn-upload-1',
          upOpt :{
            server : '/json/upload.js'
            // ,deleteServer :'/knowledge/deleteFile.jhtml'
          }
          ,beforeUpload : function (file) {
          }
          ,uploadSuccess : function (file,response) {
//            window.console && console.log(file,response);
            $('#txt-uploadFlie-1').val(response.fileUrl);
          },
          uploadFinished : function () {
          }
        });


    }
  };
  return back;
});
