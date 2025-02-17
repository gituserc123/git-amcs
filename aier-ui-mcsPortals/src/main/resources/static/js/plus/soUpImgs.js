;(function (factory) {
  if (typeof define === "function" && define.amd) {
      // AMD模式
      define([ "jquery" ], factory);
  } else {
      // 全局模式
      factory(jQuery);
  }
}(function ($) {


      //重置imgWrap
      function resetImgWrap(thumbWrap){
        $(thumbWrap).html('<div class="noResult">暂无图片...</div>');
      }

      // 批量渲染图片 和按钮事件
      function renderImgs($btn, $upload, $imgInputWrap, opt, maxS, files){
        var results = [];
        $.each(files, async function (i, v){
          renderImg(opt, maxS, results, v,  i == files.length-1, function (){
            // console.log(results);
            if (opt.autoSetValue) {
              let inputsHtml = '', imgsHtml = '';
              results.forEach(item => {
                inputsHtml += '<input type="hidden" name="'+ opt.inputName +'" value="'+ item.base64 +'"/>';
                imgsHtml += '<img class="imgThumb" src="'+ item.base64 +'" alt=""/>';
              });
              $imgInputWrap.html(inputsHtml);
              $(opt.thumbWrap).html(imgsHtml);
            }

            $upload.next('.btn-uploadImg').remove();
            opt.sureback && opt.sureback(results);
            var $clearBtn = $('<span class="btn btn-warning btn-uploadImg btn-small" style="margin-left:5px;">清除</span>');
            $btn.after($clearBtn);
            $upload.remove();
            $clearBtn.click(function () {
              if (opt.autoSetValue) {
                $(opt.thumbWrap).html('');
                $imgInputWrap.html('');
                resetImgWrap($imgInputWrap);
                $(this).remove();
              } else {
                opt.clearFn(results);
              }
            });

          });
        });
      }

      // 渲染图片
      function renderImg(opt, maxS, results, file,  needBack, callback){
        var fileSize = Math.ceil(file.size / 1024);
        if (fileSize > maxS) {
          $pop.msg('文件大于' + opt.maxSize + '，无法上传！');
          return;
          // return -1;
        }
        // console.log('file 走过这里',file);

        var reader = new FileReader();
        reader.onload = function () {
          var img = new Image();
          img.src = reader.result;
          img.onload = function () {
            var w = img.width,
                h = img.height;
            var canvas = document.createElement('canvas');
            var ctx = canvas.getContext('2d');
            $(canvas).attr({
              width: w,
              height: h
            });
            ctx.drawImage(img, 0, 0, w, h);
            var base64 = canvas.toDataURL('image/jpeg', 0.5);
            var result = {
              url: window.URL.createObjectURL(file),
              base64: base64,
              clearBase64: base64.substr(base64.indexOf(',') + 1),
              suffix: base64.substring(base64.indexOf('/') + 1, base64.indexOf(';'))
            };
            // console.log(result);
            results.push(result);
            if(needBack && callback){
              // console.log(results);
              setTimeout(() => {
                callback();
              });
            }
          }
        }
        reader.readAsDataURL(file);

      }

  $.fn.soUpImgs = function (opt) {
    var o = $.extend({
      thumbWrap: null, //缩略图对象
      input: null, //输入框对象
      multiple: false, //上传多个图片
      autoSetValue: true, //是否直接设置thumb和input值
      accept: '.gif,.jpg,.jpeg,.bmp,.png', //可以接受的格式
      inputName: 'upImg',
      maxSize: '2m', //默认最大2m
      blankPic: '/static/images/base/blankPic.png', //默认无图时，thum图片
      clearFn: function (result) {}, //点击清除按钮
      sureback: function (result) {} //上传并设置完成后执行事件
    }, opt || $T.data(this) || {});

    var $t = $(this);
      // $thumbWrap = $(o.thumbWrap),
      // $input = $(o.input);
    var radom = Math.round(new Date() / 1000);

    //算出用来比较的maxsize，以k为单位
    var sizeSet = o.maxSize.match(/^(\d+)(k|m|K|M)$/);
    if (!sizeSet) {
      $pop.msg('控件最大上传大小设置不正确！');
      return;
    }
    var maxS = sizeSet[1];
    var maxU = sizeSet[2] ? sizeSet[2].toLowerCase() : 'k';
    if (maxU == 'm') {
      maxS = maxS * 1024
    };
    resetImgWrap(o.thumbWrap);
    var $imgInputWrap = $('<div class="upImgInputWrap"></div>');
    $t.after($imgInputWrap);


    $t.click(function () {
      var $upload = $('<input id="txt-upload' + radom + '" style="display:none;" accept="' + o.accept + '" type="file" '+ (o.multiple? 'multiple': '') +' />');
      $t.after($upload);
      $upload.change(function () {
        // console.log(this.files);
        renderImgs($t, $upload, $imgInputWrap, o, maxS, this.files);
        return false;
      });
      $upload.click();
    });

    return $t;
  };
})
);
