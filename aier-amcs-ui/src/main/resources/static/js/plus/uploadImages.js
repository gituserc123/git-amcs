define(['WebUploader'],function (WebUploader) {
    /**
     * option 配置项目
     * customType 图片所属类型 例如：角膜地形图 || 其它
     * onDelCallBack 已保存图片，删除后回调方法
     */
    function TwebUploader(option,customType,cbOption){
        var defaultOption = {
            maxFileNum:5,//允许图片组最大个数
            auto:true,//自动上传图片
            outWrap:null,//外部容器
            addBtn:null,//图片组没有一条数据时，上传按钮
            addMoreBtn:null,//有图片时，继续添加按钮
            formData: null,//文件上传请求的参数表，每次发送都会发送此对象中的参数
            serversUrl:'',//图片上传地址
        };
        var defaultCB = {
            onDelCallBack:null,
            onUploadSuccess:null
        };
        this.opt = $.extend(defaultOption,option||{});
        this.cusCB = $.extend(defaultCB,cbOption||{})
        this.fileImages = [];
        this.delImage = function(image){
            if(this.fileImages.length){
                var exist = this.fileImages.findIndex(function(item){
                    return item.id == image.id;
                });
                if(exist>-1){
                   this.fileImages.splice(exist,1);
                }
            }
        }

        this.onDelCallBack = this.cusCB.onDelCallBack;
        this.onUploadSuccess = this.cusCB.onUploadSuccess;
        this.customType = customType;
        this.$wrap = $(this.opt.outWrap);
        this.$queue = $( '<ul class="filelist"></ul>' ).appendTo( this.$wrap.find( '.queueList' ) );// 图片容器
        this.$statusBar = this.$wrap.find( '.statusBar' );// 状态栏，包括进度和控制按钮
        this.$info = this.$statusBar.find( '.info' );// 文件总体选择信息。
        this.$upload = this.$wrap.find( '.uploadBtn' );// 上传按钮
        this.$placeHolder = this.$wrap.find( '.placeholder' );// 没选择文件之前的内容。
        this.$progress = this.$statusBar.find( '.progress' ).hide();
        this.fileCount = 0;// 当前添加的文件总数量
        this.fileSize = 0;// 添加的文件总大小
        this.ratio = window.devicePixelRatio || 1;// 优化retina, 在retina下这个值是2
        // 缩略图大小
        this.thumbnailWidth = 110 * this.ratio;
        this.thumbnailHeight = 110 * this.ratio;
        this.state = 'pedding';//图片当前状态 可能有pedding, ready, uploading, confirm, done.
        this.percentages = {};// 所有文件的进度信息，key为file id

        if(!this.opt.auto){
            this.$wrap.find('.uploadBtn').css('display','block');
        }

        // 判断浏览器是否支持图片的base64
        this.isSupportBase64 = ( function() {
            var data = new Image();
            var support = true;
            data.onload = data.onerror = function() {
                if( this.width != 1 || this.height != 1 ) {
                    support = false;
                }
            }
            data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";//此图片宽高均为 1
            return support;
        } )();

        // 检测是否已经安装flash，检测flash的版本
        this.flashVersion = ( function() {
            var version;
            try {
                version = navigator.plugins[ 'Shockwave Flash' ];
                version = version.description;
            } catch ( ex ) {
                try {
                    version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                        .GetVariable('$version');
                } catch ( ex2 ) {
                    version = '0.0';
                }
            }
            version = version.match( /\d+/g );
            return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
        } )();

        //检查是否支持 Transition css 属性
        this.supportTransition = (function(){
            var s = document.createElement('p').style,
                r = 'transition' in s ||
                    'WebkitTransition' in s ||
                    'MozTransition' in s ||
                    'msTransition' in s ||
                    'OTransition' in s;
            s = null;
            return r;
        })();


        this.uploader = null;// WebUploader实例

        if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {
            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function(container) {
                    window['expressinstallcallback'] = function( state ) {
                        switch(state) {
                            case 'Download.Cancelled':
                                $pop.alert('您取消了更新！');
                                break;
                            case 'Download.Failed':
                                $pop.alert('安装失败');
                                break;
                            default:
                                $pop.alert('安装已成功，请刷新！');
                                break;
                        }
                        delete window['expressinstallcallback'];
                    };
                    var swf = './expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/' +
                        'x-shockwave-flash" data="' +  swf + '" ';
                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }
                    html += 'width="100%" height="100%" style="outline:0">'  +
                        '<param name="movie" value="' + swf + '" />' +
                        '<param name="wmode" value="transparent" />' +
                        '<param name="allowscriptaccess" value="always" />' +
                        '</object>';
                    container.html(html);
                })(this.$wrap);
                // 压根就没有安转。
            } else {
                this.$wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
            }
            return;
        } else if (!WebUploader.Uploader.support()) {
            $pop.alert( 'Web Uploader 不支持您的浏览器！');
            return;
        }

        // 实例化 WebUploader对象
        this.uploader = WebUploader.create({
            auto:this.opt.auto,
            pick: {
                id: this.opt.addBtn,
                label: '点击选择图片'
            },
            formData:this.opt.formData,//文件上传请求的参数表，每次发送都会发送此对象中的参数
            // dnd: '#uploader .queueList',
            // paste: '#uploader',
            swf: '../../dist/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: this.opt.serversUrl,
            // runtimeOrder: 'flash',
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: this.opt.maxFileNum,//限制文件上传个数
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });
        var cur_uploader = this;

        // 拖拽时不接受 js, txt 文件。
        this.uploader.on( 'dndAccept', function( items ) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';
            for ( ; i < len; i++ ) {
                // 如果在列表里面
                if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                    denied = true;
                    break;
                }
            }
            return !denied;
        });

        this.uploader.on('dialogOpen', function() {
            console.log('here');
        });

        // this.uploader.on('filesQueued', function() {
        //     uploader.sort(function( a, b ) {
        //         if ( a.name < b.name )
        //           return -1;
        //         if ( a.name > b.name )
        //           return 1;
        //         return 0;
        //     });
        // });

        // this.uploader.on('ready', function() {
        //     // window.uploader = uploader;
        // });

        // 添加“添加文件”的按钮，
        this.uploader.addButton({
            id: this.opt.addMoreBtn,
            label: '继续添加',
            innerHTML:'<div style="line-height: 24px;">继续添加</div>'
        });

        this.uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            cur_uploader.percentages[ file.id ][ 1 ] = percentage;
            cur_uploader.updateTotalProgress();
        };

        this.uploader.onBeforeFileQueued = function( file ) {
            if(cur_uploader.fileCount >= cur_uploader.opt.maxFileNum){//数量大于最大上传数量，不允许上传
                $pop.alert('最多上传'+cur_uploader.opt.maxFileNum+'张图片信息')
                return false;
            }
        };

        this.uploader.onFileQueued = function( file ) {
            cur_uploader.fileCount++;
            cur_uploader.fileSize += file.size;

            if ( cur_uploader.fileCount === 1 ) {
                cur_uploader.$placeHolder.addClass( 'element-invisible' );
                cur_uploader.$statusBar.show();
            }

            cur_uploader.addFile( file );
            cur_uploader.setState( 'ready' );
            cur_uploader.updateTotalProgress();
        };

        this.uploader.onFileDequeued = function( file ) {
            console.log('文件删除');
            cur_uploader.fileCount--;
            cur_uploader.fileSize -= file.size;

            if ( !cur_uploader.fileCount ) {
                cur_uploader.setState( 'pedding' );
            }

            cur_uploader.removeFile( file );
            cur_uploader.updateTotalProgress();

            //业务逻辑；上传后，未保存，直接删除时，去除将提交的图片信息

            var deleteIndex = -1;
            deleteIndex = cur_uploader.fileImages.findIndex(function(item){
               return item.fileId == file.customId;
            });
            // for(var x in img_map[cur_uploader.customType]){
            //     var obj = img_map[cur_uploader.customType][x];
            //     if(!!file.customId && file.customId == obj.customId){
            //         deleteIndex = x;
            //         break;
            //     }
            // }
            if(deleteIndex!=-1){
                cur_uploader.fileImages.splice(deleteIndex,1);
            }

            !!cur_uploader.onDelCallBack && cur_uploader.onDelCallBack(file);

        };

        //文件上传成功
        this.uploader.onUploadSuccess = function(file ,response ){
            var data = response.data;
            cur_uploader.fileImages.push(data)

            //获取图片类型对应的数组
            // img_map[cur_uploader.customType].push({
            //     "path":data.path,
            //     "url":data.url,
            //     "customId":data.id
            // });
            file.customId = data.id;
            file.remoteUrl = data.url;
            var c = cur_uploader.$queue.find('#smallimg_'+file.id);
            //c.attr("src", data.url);
            //c.attr('data-src',data.url);
            // 通过 AJAX 请求解密图片数据

            !!cur_uploader.onUploadSuccess && cur_uploader.onUploadSuccess(data);
        };

        this.uploader.on( 'all', function( type ) {
            // var stats;
            // switch( type ) {
            //     case 'uploadFinished':
            //         cur_uploader.setState( 'confirm' );
            //         break;
            //     case 'startUpload':
            //         cur_uploader.setState( 'uploading' );
            //         break;
            //     case 'stopUpload':
            //         cur_uploader.setState( 'paused' );
            //         break;
            //
            // }
        });

        this.uploader.onError = function( code ) {
            var msg = '错误：' + code;
            switch (code) {
                case 'Q_EXCEED_NUM_LIMIT':
                    msg = '文件超出最大上传数量';
                    break;
                case 'Q_EXCEED_SIZE_LIMIT':
                    msg = '文件超出最大上传大小';
                    break;
                case 'Q_TYPE_DENIED ':
                    msg = '文件格式不正确';
                    break;
                case 'F_DUPLICATE':
                    msg = '已存在相同文件';
                    break;
            }
            $pop.alert(msg);
        };

        this.$upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( cur_uploader.state === 'ready' ) {
                cur_uploader.uploader.upload();
            } else if ( cur_uploader.state === 'paused' ) {
                cur_uploader.uploader.upload();
            } else if ( cur_uploader.state === 'uploading' ) {
                cur_uploader.uploader.stop();
            }
        });

        this.$info.on( 'click', '.retry', function() {
            cur_uploader.uploader.retry();
        } );

        this.$info.on( 'click', '.ignore', function() {
            $pop.alert( 'todo' );
        } );

        this.$upload.addClass( 'state-' + this.state );
        this.updateTotalProgress();
    }

    TwebUploader.prototype.loadExitsImgs = function( imgs ){
        var cur_uploader = this;
        if(!imgs || imgs.length == 0){
            return;
        }
        cur_uploader.fileImages.push.apply(cur_uploader.fileImages,imgs);
        cur_uploader.setState('ready');
        for(var i = 0,len = imgs.length;i < len;i++){
            (function (tmpImg) {
                tmpImg.rotation = 0;
                var $li = $( '<li id="'+tmpImg.fileId+'">' +
                        '<p class="title">'+tmpImg.fileName+'</p>' +
                        '<p class="imgWrap"></p>'+
                        '<p class="progress"><span></span></p>' +
                        '</li>' ),
                    $btns = $('<div class="file-panel">' +
                        '<span class="cancel">删除</span>' +
                        '</div>').appendTo( $li ),
                    $prgress = $li.find('p.progress span'),
                    $wrap = $li.find( 'p.imgWrap' ),
                    $info = $('<p class="error"></p>');

                var xhr = new XMLHttpRequest();
                xhr.open('POST', base + '/ui/service/biz/amcs/adverse/aeFile/decryptFile', true);
                xhr.setRequestHeader('Content-Type', 'application/json');
                xhr.responseType = 'arraybuffer'; // Set response type

                xhr.onload = function() {
                    if (xhr.status === 200) {
                        var mimeType = xhr.getResponseHeader('Content-Type');
                        var blob = new Blob([xhr.response], { type: mimeType });
                        var url = URL.createObjectURL(blob);

                        // 设置图片的 src 属性
                        var img = $('<img class="spotlight" data-src="' + url + '" src="' + url + '" width="150" height="150">');
                        $wrap.empty().append(img);
                        $prgress.hide().width(0);
                        $li.append('<span class="success"></span>');
                        $li.on('mouseenter', function() {
                            $btns.stop().animate({ height: 30 });
                        });

                        $li.on('mouseleave', function() {
                            $btns.stop().animate({ height: 0 });
                        });

                        $btns.on('click', 'span', function() {
                            var index = $(this).index(),
                                deg;
                            switch (index) {
                                case 0:
                                    var $li = $('#' + tmpImg.fileId);
                                    delete cur_uploader.percentages[tmpImg.fileId];
                                    cur_uploader.updateTotalProgress();
                                    $li.off().find('.file-panel').off().end().remove();
                                    cur_uploader.fileCount--;

                                    var deleteIndex = -1;
                                    deleteIndex = cur_uploader.fileImages.findIndex(function(item) {
                                        return (item.fileId === tmpImg.fileId);
                                    });
                                    if (deleteIndex != -1) {
                                        cur_uploader.fileImages.splice(deleteIndex, 1);
                                    }

                                    !!cur_uploader.onDelCallBack && cur_uploader.onDelCallBack(this, tmpImg);
                                    return;
                                case 1:
                                    tmpImg.rotation += 90;
                                    break;
                                case 2:
                                    tmpImg.rotation -= 90;
                                    break;
                            }

                            if (cur_uploader.supportTransition) {
                                deg = 'rotate(' + tmpImg.rotation + 'deg)';
                                $wrap.css({
                                    '-webkit-transform': deg,
                                    '-mos-transform': deg,
                                    '-o-transform': deg,
                                    'transform': deg
                                });
                            } else {
                                $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((tmpImg.rotation / 90) % 4 + 4) % 4) + ')');
                            }
                        });
                        $li.appendTo(cur_uploader.$queue);
                        cur_uploader.fileCount++;
                        if (cur_uploader.fileCount === 1) {
                            cur_uploader.$placeHolder.addClass('element-invisible');
                            cur_uploader.$statusBar.show();
                        }
                        cur_uploader.updateTotalProgress();
                    } else {
                        console.error('Failed to decrypt image:', xhr.statusText);
                    }
                };

                xhr.onerror = function() {
                    console.error('Request failed');
                };

                xhr.send(JSON.stringify(tmpImg.url));

            })(imgs[i]);
        }


        /*
        var $li = $( '<li id="ttt">' +
            '<p class="title">ttt</p>' +
            '<p class="imgWrap"></p>'+
            '<p class="progress"><span></span></p>' +
            '</li>' ),

            $btns = $('<div class="file-panel">' +
                '<span class="cancel">删除</span>' +
                '<span class="rotateRight">向右旋转</span>' +
                '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
            $prgress = $li.find('p.progress span'),
            $wrap = $li.find( 'p.imgWrap' ),
            $info = $('<p class="error"></p>');

        var img = $('<img src="https://aier-picture-1259589318.cos.ap-chengdu.myqcloud.com/100630100630/12323232232/12323232232/test-中文.jpg">');
        $wrap.empty().append( img );
        $prgress.hide().width(0);
        $li.append( '<span class="success"></span>' );
        $li.appendTo( this.$queue );

        cur_uploader.fileCount++;
        //cur_uploader.fileSize += file.size;

        if ( cur_uploader.fileCount === 1 ) {
            cur_uploader.$placeHolder.addClass( 'element-invisible' );
            cur_uploader.$statusBar.show();
        }

        // cur_uploader.addFile( file );
        cur_uploader.setState( 'ready' );
        cur_uploader.updateTotalProgress();
        */
    };

    // 当有文件添加进来时执行，负责view的创建
    TwebUploader.prototype.addFile = function( file ) {
        var cur_uploader = this;
        var $li = $( '<li id="' + file.id + '">' +
                '<p class="title">' + file.name + '</p>' +
                '<p class="imgWrap"></p>'+
                '<p class="progress"><span></span></p>' +
                '</li>' ),

            // $btns = $('<div class="file-panel">' +
            //     '<span class="cancel">删除</span>' +
            //     '<span class="rotateRight">向右旋转</span>' +
            //     '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
            $btns = $('<div class="file-panel">' +
                '<span class="cancel">删除</span>' +
                '</div>').appendTo( $li ),
            $prgress = $li.find('p.progress span'),
            $wrap = $li.find( 'p.imgWrap' ),
            $info = $('<p class="error"></p>'),

            showError = function( code ) {
                switch( code ) {
                    case 'exceed_size':
                        text = '文件大小超出';
                        break;

                    case 'interrupt':
                        text = '上传暂停';
                        break;

                    default:
                        text = '上传失败，请重试';
                        break;
                }

                $info.text( text ).appendTo( $li );
            };

        if ( file.getStatus() === 'invalid' ) {
            showError( file.statusText );
        } else {
            // @todo lazyload
            $wrap.text( '预览中' );
            this.uploader.makeThumb( file, function( error, src ) {
                var img;

                if ( error ) {
                    $wrap.text( '不能预览' );
                    return;
                }

                if( cur_uploader.isSupportBase64 ) {
                    cur_uploader.tmpImgSrc = src;
                    img = $('<img class="spotlight" id="smallimg_'+file.id+'" src="'+src+'">');
                    $wrap.empty().append( img );
                } else {
                    $.ajax('../../server/preview.php', {
                        method: 'POST',
                        data: src,
                        dataType:'json'
                    }).done(function( response ) {
                        if (response.result) {
                            img = $('<img src="'+response.result+'">');
                            $wrap.empty().append( img );
                        } else {
                            $wrap.text("预览出错");
                        }
                    });
                }
            }, this.thumbnailWidth, this.thumbnailHeight );

            this.percentages[ file.id ] = [ file.size, 0 ];
            file.rotation = 0;
        }

        file.on('statuschange', function( cur, prev ) {
            if ( prev === 'progress' ) {
                $prgress.hide().width(0);
            } else if ( prev === 'queued' ) {
                console.log('上传完毕啦');
                // $li.off( 'mouseenter mouseleave' );
                // $btns.remove();
            }

            // 成功
            if ( cur === 'error' || cur === 'invalid' ) {
                console.log( file.statusText );
                showError( file.statusText );
                cur_uploader.percentages[ file.id ][ 1 ] = 1;
            } else if ( cur === 'interrupt' ) {
                showError( 'interrupt' );
            } else if ( cur === 'queued' ) {
                cur_uploader.$info.remove();
                $prgress.css('display', 'block');
                cur_uploader.percentages[ file.id ][ 1 ] = 0;
            } else if ( cur === 'progress' ) {
                cur_uploader.$info.remove();
                $prgress.css('display', 'block');
            } else if ( cur === 'complete' ) {
                $prgress.hide().width(0);
                $li.append( '<span class="success"></span>' );
            }

            $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
        });

        $li.on( 'mouseenter', function() {
            $btns.stop().animate({height: 30});
        });

        $li.on( 'mouseleave', function() {
            $btns.stop().animate({height: 0});
        });
        // $li.on( 'click', function() {
        //     $pop.iframePop({
        //         title:"图片预览",
        //         content:file.remoteUrl,
        //         area:["100%","100%"]
        //     });
        // });

        $btns.on( 'click', 'span', function() {
            var index = $(this).index(),
                deg;

            switch ( index ) {
                case 0:
                    cur_uploader.uploader.removeFile( file );
                    return;
                case 1:
                    file.rotation += 90;
                    break;
                case 2:
                    file.rotation -= 90;
                    break;
            }

            if ( cur_uploader.supportTransition ) {
                deg = 'rotate(' + file.rotation + 'deg)';
                $wrap.css({
                    '-webkit-transform': deg,
                    '-mos-transform': deg,
                    '-o-transform': deg,
                    'transform': deg
                });
            } else {
                $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                // use jquery animate to rotation
                // $({
                //     rotation: rotation
                // }).animate({
                //     rotation: file.rotation
                // }, {
                //     easing: 'linear',
                //     step: function( now ) {
                //         now = now * Math.PI / 180;

                //         var cos = Math.cos( now ),
                //             sin = Math.sin( now );

                //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                //     }
                // });
            }


        });

        $li.appendTo( this.$queue );
    }

    // 负责view的销毁
    TwebUploader.prototype.removeFile = function( file ) {
        var $li = $('#'+file.id);

        delete this.percentages[ file.id ];
        this.updateTotalProgress();
        $li.off().find('.file-panel').off().end().remove();
    }

    TwebUploader.prototype.updateTotalProgress = function() {
        var loaded = 0,
            total = 0,
            spans = this.$progress.children(),
            percent;

        $.each( this.percentages, function( k, v ) {
            total += v[ 0 ];
            loaded += v[ 0 ] * v[ 1 ];
        } );

        percent = total ? loaded / total : 0;


        spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
        spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
        this.updateStatus();
    }

    TwebUploader.prototype.updateStatus = function() {
        var text = '', stats;

        if ( this.state === 'ready' ) {
            text = '选中' + this.fileCount + '张图片，共' +
                WebUploader.formatSize( this.fileSize ) + '。';
        } else if ( this.state === 'confirm' ) {
            stats = this.uploader.getStats();
            if ( stats.uploadFailNum ) {
                text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                    stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
            }

        } else {
            stats = this.uploader.getStats();
            text = '共' + this.fileCount + '张（' +
                WebUploader.formatSize( this.fileSize )  +
                '），已上传' + stats.successNum + '张';

            if ( stats.uploadFailNum ) {
                text += '，失败' + stats.uploadFailNum + '张';
            }
        }
        if(!!text){
            // this.$info.html( text );
        }
    };

    TwebUploader.prototype.setState = function( val ) {
        var file, stats;

        if ( val === this.state ) {
            return;
        }

        this.$upload.removeClass( 'state-' + this.state );
        this.$upload.addClass( 'state-' + val );
        this.state = val;

        switch ( this.state ) {
            case 'pedding':
                this.$placeHolder.removeClass( 'element-invisible' );
                this.$queue.hide();
                this.$statusBar.addClass( 'element-invisible' );
                this.uploader.refresh();
                break;

            case 'ready':
                this.$placeHolder.addClass( 'element-invisible' );
                $(this.$wrap.find(this.opt.addMoreBtn)).removeClass( 'element-invisible');
                this.$queue.show();
                this.$statusBar.removeClass('element-invisible');
                this.uploader.refresh();
                break;

            case 'uploading':
                $(this.$wrap.find(this.opt.addMoreBtn)).addClass( 'element-invisible' );
                this.$progress.show();
                this.$upload.text( '暂停上传' );
                break;

            case 'paused':
                this.$progress.show();
                this.$upload.text( '继续上传' );
                break;

            case 'confirm':
                this.$progress.hide();
                $(this.$wrap.find(this.opt.addMoreBtn)).removeClass( 'element-invisible' );
                this.$upload.text( '开始上传' );

                stats = this.uploader.getStats();
                if ( stats.successNum && !stats.uploadFailNum ) {
                    this.setState( 'finish' );
                    return;
                }
                break;
            case 'finish':
                stats = this.uploader.getStats();
                if ( stats.successNum ) {
                    $pop.msg.success('上传成功',{time: 800});
                } else {
                    // 没有成功的图片，重设
                    state = 'done';
                    location.reload();
                }
                break;
        }

        this.updateStatus();
    };
    //点击缩略图图查看大图
    TwebUploader.initView = function() {
	    (function() {
	        var util = {
	            css: function(elem, obj) {
	                for (var i in obj) {
	                    elem.style[i] = obj[i];
	                }
	            },
	            addEvent: (function(window, undefined) {
	                var _eventCompat = function(event) {
	                    var type = event.type;
	                    if (type == 'DOMMouseScroll' || type == 'mousewheel') {
	                        event.delta = (event.wheelDelta) ? event.wheelDelta / 120 : -(event.detail || 0) / 3;
	                    }
	                    if (event.srcElement && !event.target) {
	                        event.target = event.srcElement;
	                    }
	                    if (!event.preventDefault && event.returnValue !== undefined) {
	                        event.preventDefault = function() {
	                            event.returnValue = false;
	                        };
	                    }
	                    return event;
	                };
	                if (window.addEventListener) {
	                    return function(el, type, fn, capture) {
	                        if (type === "mousewheel" && document.mozFullScreen !== undefined) {
	                            type = "DOMMouseScroll";
	                        }
	                        el.addEventListener(type, function(event) {
	                            fn.call(this, _eventCompat(event));
	                        }, capture || false);
	                    };
	                } else if (window.attachEvent) {
	                    return function(el, type, fn, capture) {
	                        el.attachEvent("on" + type, function(event) {
	                            event = event || window.event;
	                            fn.call(el, _eventCompat(event));
	                        });
	                    };
	                }
	                return function() {};
	            })(window)
	        };
	
	        function Preview(opt) {
	            var _this = this;
	            if (!opt.imgWrap || opt.imgWrap == "") {
	                alert("请填写图片容器id");
	                return;
	            }
	            this.Opt = {
	                imgWrap: '',
	                beforeCreat: function() {},
	                afterCreat: function() {}
	            };
	            for (var i in this.Opt) {
	                if (opt[i]) this.Opt[i] = opt[i];
	            }
	            this.elemPreviewContain = document.getElementsByTagName("body")[0];
	            this.elemContain = document.getElementById(this.Opt.imgWrap);
	            this.elemPreviewImg = null;
	            this.elemPreviewClose = null;
	            this.elemPreviewCloseBj = null;
	            this.imgBg = null;
	            this.moveObj = null
	            this.init();
	            this.LEFT = this.elemPreviewContain.offsetWidth / 2;
	            this.TOP = this.elemPreviewContain.offsetHeight / 2;
	            this.onOff = true;
	            this.startX = 0;
	            this.startY = 0;
	            this.moveX = 0;
	            this.moveY = 0;
	            this._WIDTH = 0;
	            this._HEIGHT = 0;
	            this.WIDTH = 0;
	            this.HEIGHT = 0;
	        }
	        Preview.VERSION = '1.0.0';
	        var randomNum = new Date().getTime();
	        Preview.initParams = {
	            bgClass: "img_bgmask"
	        };
	        Preview.prototype = {
	            constructor: Preview,
	            init: function() {
	                var _this = this;
	                this.imgBg = document.createElement("div");
	                this.imgBg.className = 'img_bgmask';
	                util.css(this.imgBg, {
	                    position: "fixed",
	                    left: 0,
	                    top: 0,
	                    width: '100%',
	                    height: "100%",
	                    fontSize: 0,
	                    background: 'rgba(0,0,0,.6)',
	                    display: "none"
	                });
	                this.imgBg.innerHTML = '<div class="popurNode" style="position: absolute;left:0;top:0;width:100%;height:100%;z-index:1"></div><span style="position: absolute;top: 50px;right:50px;z-index: 10;height: 48px;width: 48px;background:red;cursor:pointer;border-radius:50%;" class="close_bgmask"></span><div style="position:absolute;left:50%;top:50%;" class="view_img_wrap"><img style="width:100%;height:100%;position: relative;z-index: -1;" src="" alt=""></div>';
	                this.elemPreviewContain.appendChild(this.imgBg);
	                this.elemPreviewImg = this.imgBg.getElementsByTagName("img")[0];
	                this.moveObj = this.elemPreviewImg.parentNode;
	                this.elemPreviewClose = this.imgBg.getElementsByTagName("span")[0];
	                this.elemPreviewCloseBj = this.imgBg.querySelector(".popurNode");
	                this.elemContain.addEventListener("click", preview, false);
	
	                function preview(e) {
	                    e = e || window.event;
	                    e.stopPropagation();
	                    var target = e.target;
	                    var tag = target.tagName.toLowerCase();
	                    if (tag !== 'img') return;
	                    _this.elemPreviewImg.src = target.src;
	                    util.css(_this.imgBg, {
	                        display: "block"
	                    });
	                    _this._WIDTH = _this.WIDTH = _this.elemPreviewImg.offsetWidth;
	                    _this._HEIGHT = _this.HEIGHT = _this.elemPreviewImg.offsetHeight;
	                    util.css(_this.moveObj, {
	                        width: _this.WIDTH + "px",
	                        marginLeft: -_this.WIDTH / 2 + "px",
	                        left: '50%',
	                        top: '20%'
	                    })
	                }
	                this.moveObj.addEventListener("mousedown", mousedown, false);
	
	                function mousedown(e) {
	                    e = e || window.event;
	                    e.stopPropagation();
	                    _this.onOff = true;
	                    _this.startX = e.pageX;
	                    _this.startY = e.pageY;
	                    document.addEventListener("mousemove", mousemove, false);
	
	                    function mousemove(ev) {
	                        _this.onOff = false;
	                        _this.moveX = ev.pageX - _this.startX + _this.LEFT;
	                        _this.moveY = ev.pageY - _this.startY + _this.TOP;
	                        util.css(_this.moveObj, {
	                            "left": _this.moveX + "px",
	                            "top": _this.moveY + "px"
	                        });
	                        ev.preventDefault();
	                    }
	                    document.addEventListener("mouseup", mouseup, false);
	
	                    function mouseup(ev) {
	                        document.removeEventListener("mousemove", mousemove, false);
	                        document.removeEventListener("mouseup", mouseup, false);
	                        if (_this.onOff) return false;
	                        _this.LEFT = _this.moveX;
	                        _this.TOP = _this.moveY;
	                    }
	                    e.preventDefault();
	                }
	                this.elemPreviewClose.onclick = function() {
	                    _this.previewClose();
	                };
	                this.elemPreviewCloseBj.onclick = function() {
	                    _this.previewClose();
	                };
	                util.addEvent(this.imgBg, "mousewheel", function(event) {
	                    this.WIDTH = _this.elemPreviewImg.offsetWidth;
	                    if (event.delta > 0) {
	                        util.css(_this.moveObj, {
	                            width: (this.WIDTH + 20) + "px"
	                        });
	                    } else {
	                        util.css(_this.moveObj, {
	                            width: (this.WIDTH - 20) + "px"
	                        });
	                    }
	                    this.HEIGHT = _this.elemPreviewImg.offsetHeight;
	                    util.css(_this.moveObj, {
	                        marginLeft: -this.WIDTH / 2 + "px"
	                    });
	                });
	            },
	            previewClose: function() {
	                this.LEFT = this.elemPreviewContain.offsetWidth / 2;
	                this.TOP = this.elemPreviewContain.offsetHeight / 2;
	                util.css(this.moveObj, {
	                    width: this._WIDTH + "px"
	                });
	                util.css(this.imgBg, {
	                    display: "none"
	                });
	            }
	        };
	        window.Preview = Preview;
	    })();
	
	    var preview = new Preview({
	        imgWrap: 'wrap'
	    });
	}
    return TwebUploader;
});

