define(["easygridEdit","readCard","btnExt","btnSearch"] ,function($e,$r){

    var requestFlow = function (ajaxFn,promiseObj) {
        // 请求队列
        this.req = ajaxFn || function () { throw "请求队列"; };
        this.reqId = -1;
        this.reqList = [];
        this.promiseObj = promiseObj;
    }
    requestFlow.prototype.send = function() {
        this.addReqListener();
    }
    requestFlow.prototype.addReqListener = function(fn) {
        var rid = ++this.reqId;
        var promise = this.req();
        var self = this;
        // promise.fail(onFailure);
        // promise.always(onAlways);
        this.reqList.push({ rid : rid ,status : false,promise : promise });
        promise.done(function (res) {
            // console.log(rid +" 次请求成功返回");
            self.onSuccess(res,rid);
        });
    }
    requestFlow.prototype.onSuccess = function(res,rid ) {
        var reqlist = this.reqList;
        reqlist[rid].status = true;
        var self = this;
        var allSuccess = true;
        var len = this.reqList.length;
        for(var i in reqlist ) {
            if(reqlist[i].status == false ){
                // console.info("第" + i + "次状态请求状态还是false");
                allSuccess = false;
            }
        }
        if(len == rid -1 ) {
            this.promiseObj.onSuccess(res);
        }else if(allSuccess) {
            this.reqList[len -1].promise.done(function (res) {
                self.promiseObj.onSuccess(res);
            });
        }
    }

    // requestFlow.prototype.promise = function (obj) {
    //     this.promise = obj;
    // }


    var back = {
        base: "",
        pagePath:"",
        patientId:"",
        searchBtn:null,
        listenReadIDCard : function () {},
        listenReadTreatmentCard : function () {},
        userFormLoadData : function(data){//data初始化用户信息
            if(data.birthday){data.birthday = data.birthday.split(' ')[0];}
            //data.costType = data.costType?data.costType:'1';
            $('.form-userInfo').form('load',data);
            //$('#costType').combobox('setValue',data.costType);

            // if(data.birthday&&$('#birthday').length){$('#birthday').val(data.birthday);}
            // if(data.sex&&$('#sex').length){$('#sex').combobox('setValue',data.sex);}
        },
        getAccountBalanceByPid : function(pid){//根据pid获取账号accountBalance(余额)
            new requestFlow(function () {
              return $ajax.post(b.base + '/ui/outp/patientAccount/getAccountByPid', {patientId: pid });
            },{
              onSuccess : function (res) {
                $("#accountBalance").val(res.data.accountBalance);
              }
            });
        },
        onPatIdChange: function(){//触发院内提示检查
            var me = this;
            //1 觸發院内提示检查
            $('#grid-l-prompt').datagrid('reload', {patientId: me.patientId});
            $('#grid-l-prompt').datagrid({ onLoadSuccess : function () {
                var  rowsData = $('#grid-l-prompt').datagrid('getRows');
                if(rowsData.length > 0) {
                  $(".icon-exclamation").removeClass("hide");
                }else {
                  $(".icon-exclamation").addClass("hide");
                }
              }
            });
        },
        hasPatId : function(){//是否有patID处理
            var me = this;
            if(!me.patientId) {
                $("#tabsA").find(".btn").attr("disabled","disabled");
            } else {
                $("#tabsA").find(".btn").removeAttr("disabled");
                me.onPatIdChange();
            }
        },
        initTab: function(patientInfo){//根据病人信息初始化tab中用户信息
            var me = this;
            $('#tabsA').tabs('select', 0);
            me.userFormLoadData(patientInfo);
            var birthday =  patientInfo.birthday.substring(0,10);
            $("#age").val($T.getAgeByBir(birthday));
            $("#birthday").val(birthday);
            me.patientId = patientInfo.id;
            me.getAccountBalanceByPid(me.patientId);
            me.hasPatId();
        },
        init : function (base,pagePath) {//加载事件
            var me = this;
            var b = back;
            if(base != null)  b.base = base;

            if(pagePath != null)  b.pagePath = pagePath;
            // 如果 patientId 为空则显示遮罩
            me.hasPatId();
            //$('.form-userInfo').form('load',  { name : "test1" ,sex : 2, "tel1" : 1234567});


            // return;
            // $('.form-userInfo').form('validate'); // test

            var promptEditInitData = {
                patientId: b.patientId,
                hospital: '长沙爱尔眼科医院',
                inputer: '王天'
            };
            var specialInfoD = null;
      var specialInfoTypeD = null;
    //var idCardId = null;//用来暂存身份证物理ID
            var idCardInfo = null;//用来暂存身份证号码、姓名、性别等等各种信息
            b.patientId = $('#pid').val();
            // 待注釋
            var tabPtId = {
                tabsA: [b.patientId, '', '', '', '', '', '']
            };
            var kinsfolkAppelArr = null;
            var certTypeArr = null;
            var idCard = null;
            var idCard_Type=null;

            var dict = {};
        	var paramDict = {paraType : ['special_info','special_info_type','relation','document_type']};
        	// request flow 测试
            // var testReq = new requestFlow(function () {
             //    return $ajax.post(b.base + '/ui/sys/dict/getMoreList',paramDict);
            // },{
             //    onSuccess : function (rst) {
             //        console.log(rst);
             //        dict = rst.data;
             //        specialInfoD = dict.special_info;
             //        specialInfoTypeD = dict.special_info_type;
             //        kinsfolkAppelArr = dict.relation;
             //        certTypeArr = dict.document_type;
             //    }
            // });
            // //test
             // var n = 0;
             // var int = setInterval(function () {
             //     n++;
             //     if(n > 30) return window.clearInterval(int);
             //     testReq.send();
             // },10);

            $ajax.postSync(b.base + '/ui/sys/dict/getMoreList',paramDict).done(function (rst) {
                dict = rst.data;
                specialInfoD = dict.special_info;
                specialInfoTypeD = dict.special_info_type;
                kinsfolkAppelArr = dict.relation;
                certTypeArr = dict.document_type;
            });
            // $ajax.postSync(b.base + '/ui/sys/dict/getMoreList',paramDict,false,false).done(function (rst) {
            // 	dict = rst.data;
            // 	specialInfoD = dict.special_info;
            // 	specialInfoTypeD = dict.special_info_type;
            // 	kinsfolkAppelArr = dict.relation;
            // 	certTypeArr = dict.document_type;
            // });


            //用户联系人信息 begin
            var contactEditInitData = {
                patientId: b.patientId
            };

            $(function () {
                //只建档
                $("#onlyBookbuilding").click(function () {

                    var userInfo = $('.form-userInfo').sovals();
                    var formValidate = $(".form-userInfo").form('validate');
                    if(!formValidate) return;
                    if(b.patientId) return $pop.alert( userInfo.name + '已存在档案信息无需再建档');
                    $.ajax({
                        url : b.base+ "/ui/outp/patientCard/qrcodeCardNo",
                        type: "POST",
                        data: userInfo,
                        success : function (res) {
                            //console.log(res);
                            onlyBookbuildingDone(res.data);
                        },
                        error : function (err) {
                            $pop.err(err);
                        }
                    });

                });

                // 条码
                $("#bookbuilding").click(function () {
                    bookbuilding();
                });

                var openQRcodeInput = function () {
                    var pop = null;
                    pop = layer.open({
                        type : 1,
                        title : '请手动输入条码号码',
                        zIndex :2,
                        content : $('.popOpTemBox'),
                        area : ['450px','200px'],
                        success : function () {
                            $('.popOpTemBox').addClass('popOpShow');
                            $('#qrcodeCardNo').val("").focus();
                            		
                        },
                        end : function () {
                            $('.popOpTemBox').removeClass('popOpShow');
                        }
                    });
                    // bind event

                    (function () {
                        $('#qrcodeCardNo_Submit').unbind("click");
                        //event
                        // 发送  建档发卡请求
                        $('#qrcodeCardNo_Submit').click(function () {

                        	var userInfo = $('.form-userInfo').sovals();
                        	var formValidate = $("#templForm").form('validate');
                            if(!formValidate) return;
                            var qrcodeCardNo = $("#qrcodeCardNo").val();
                            userInfo.qrcodeCardNo = qrcodeCardNo;
                            //console.log(userInfo,1111);
                                $.ajax({
                                    url : b.base+ "/ui/outp/patientCard/qrcodeCardNo",
                                    type: "POST",
                                    data: userInfo,
                                    success : function (res) {
                                    	//console.log(res);
                                        bookbuildingDone(res.data);
                                        $("#qrcodeCardNo").val("");
                                    },
                                    error : function (err) {
                                        $pop.err(err);
                                    }
                                });
                                // test
                                layer.close(pop);
                        });
                        $('#qrcodeCardNo_Cancel').click(function () {
                            layer.close(pop);
                            $("#qrcodeCardNo").val("");

                        });

                    })();

                    return pop;
                }



                //条码
                var bookbuilding =function () {

                    //患者信息 填写
                    if (!$('.form-userInfo').form('validate')) {
                        //如果信息不完整，直接提示并退出
                        $pop.alert('发新卡请正确填写必填信息！', null, {offset: ['22%', '26%']});
                        return;
                    }
                    // 弹出二维码输入框
                    openQRcodeInput();

                    // 后台发起 建档请求 form-userInfo
                    // promise = $.ajax();
                    //  后台逻辑1 允许建档 建档同时发卡逻辑   前台 提示发卡成功

                    // promise.done;
                    // 后台逻辑2  同名患者  --> 返回同名患者集合 --> 请选择一个患者  ---》 前台条件 都不是
                    // grid.loadData(list);


                }

                //条码后续事件
                var bookbuildingDone = function (res) {
                    if(res.status == '3') {
                        //患者建档成功
                    	$pop.msg("发卡建档成功！");   
                    	b.initTab(res.list);
                    } else if(res.status == '2'){
                        //  返回同名患者集合 --> 请选择一个患者  ---》 前台条件 都不是
                        $('#getInfoGrid2').datagrid('loadData', res.list);
                        $('.new-pop').addClass('show-pop');
                    }else if(res.status == '1'){
                    	//检索出卡号对应的患者信息，则提示卡号存在，不再后续处理
                    	$pop.err("卡号为："+res.qrcodeCardNo+"的卡，已存在发卡记录，请更换卡号！");
                    }
                    // bind event
                    ;(function () {
                        // if(bookbuildingDoneInit) return;
                        // bookbuildingDoneInit = true;

                        $('.btn-bookbuildingOldUser').unbind("click");
                        $(".btn-confirmAllNot").unbind("click");
                        $(".btn-bookbuildingcancel").unbind("click");
                        var qrcodeCardNo = $("#qrcodeCardNo").val();
                        // 选中以有用户
                        $(".btn-bookbuildingOldUser").show();
                        $(".btn-bookbuildingOldUser").click(function () {
                        	//console.log(res);
                        	//选择已有患者，进行发卡
                        	//检索建档发卡时， 是否输入了手动建档的卡号。  如果卡号为空则仅弹出提示信息，不进行后续操作； 若不为空则进行发卡操作
                        	if(!res.qrcodeCardNo){
                        		$pop.msg('未输入卡号，无法后续！');
                        	}else{
	                            var userInfo = $('#getInfoGrid2').datagrid('getSelected');
	                            userInfo.qrcodeCardNo = res.qrcodeCardNo;
	                            if (userInfo) {
	                                $ajax.post(b.base + '/ui/outp/patientCard/qrcodeCardNoSendCard', userInfo).done(function (rst) {//系统返回201提示为用户发卡成功{"code":"201","msg":"为该用户发卡成功！"}
	                                    if (rst.code === '200' || rst.code === '201') {
	                                        $pop.msg('发卡成功！');
                                            b.initTab(userInfo);
	                                    }
	                                }).error(function (err) {
                                        $pop.err(err);
                                    });
	                            } else {
	                                $pop.msg('请选择用户行！');
	                            }
	                            $('.new-pop').removeClass('show-pop');

                        	}
                        });
                        // 以上用户全不是，给用户直接发卡
                        $(".btn-confirmAllNot").click(function () {
                            // 以上用户都不是重新建档发卡
                        	var userInfo = $('.form-userInfo').sovals();
                        	userInfo.id = null;
                        	userInfo.qrcodeCardNo = qrcodeCardNo;
                            $ajax.post(b.base + '/ui/outp/patientCard/qrcodeCardNoCreateArchives', userInfo).done(function (rst) {//系统返回201提示为用户发卡成功{"code":"201","msg":"为该用户发卡成功！"}
                                if(rst.code === '200' || rst.code === '201'){
                                    $pop.msg('建档成功！');
                                    b.initTab(rst.data.list);

                                }
                            }).error(function (err) {
                                $pop.err(err);
                            });
                            $('.new-pop').removeClass('show-pop');

                        });
                        $(".btn-bookbuildingcancel").click(function () {
                            $('.new-pop').removeClass('show-pop');
                        })

                    })();

                }
                 

                //建档按钮事件
                var onlyBookbuildingDone = function (res) {
                    if(res.status == '3') {
                        //患者建档成功 ( 这里不会进入这个条件分支 无卡号)
                        $pop.msg("建档成功！");
                    } else if(res.status == '2'){
                        //  返回同名患者集合 --> 请选择一个患者  ---》 前台条件 都不是
                        $('#getInfoGrid2').datagrid('loadData', res.list);
                        $('.new-pop').addClass('show-pop');
                    }else if(res.status == '1'){
                        //检索出卡号对应的患者信息，则提示卡号存在，不再后续处理 ( 这里不会进入这个条件分支 无卡号)
                        $pop.err("卡号为："+res.qrcodeCardNo+"的卡，已存在发卡记录，请更换卡号！");
                    }
                    // bind event
                    ;(function () {
                        // if(bookbuildingDoneInit) return;
                        // bookbuildingDoneInit = true;

                        $('.btn-bookbuildingOldUser').unbind("click");
                        $(".btn-confirmAllNot").unbind("click");
                        $(".btn-bookbuildingcancel").unbind("click");
                        var qrcodeCardNo = $("#qrcodeCardNo").val();
                        // 选中以有用户

                        //
                        $(".btn-bookbuildingOldUser").val("确 认");
                        $(".btn-bookbuildingOldUser").click(function () {
                            var userInfo = $('#getInfoGrid2').datagrid('getSelected');
                            if(!userInfo) return   $pop.msg('请选择用户行！');
                            b.initTab(userInfo);
                            $('.new-pop').removeClass('show-pop');
                        });
                        // 以上用户全不是，给用户直接发卡
                        $(".btn-confirmAllNot").val("以上都不是,为新患者建档");
                        $(".btn-confirmAllNot").click(function () {
                            // 以上用户都不是重新建档发卡
                            var userInfo = $('.form-userInfo').sovals();
                            userInfo.id = null;
                            $ajax.post(b.base + '/ui/outp/patientCard/qrcodeCardNoCreateArchives', userInfo).done(function (rst) {//系统返回201提示为用户发卡成功{"code":"201","msg":"为该用户发卡成功！"}
                                if(rst.code === '200' || rst.code === '201'){
                                    $pop.msg('建档成功！');
                                    b.initTab(rst.data.list);
                                }
                            }).error(function (err) {
                                $pop.err(err);
                            });
                            $('.new-pop').removeClass('show-pop');
                        });
                        $(".btn-bookbuildingcancel").click(function () {
                            $('.new-pop').removeClass('show-pop');
                        })

                    })();

                }





                // 读 就诊卡 按钮 JS 的调用逻辑

                    // 避免无按钮
                    var btn = $btnExt.get("readCard");
                    $r.readCardInit();//插入读卡ocx html
                    btn && btn.el.on("click",function () {
                        $r.readMacNumber(function(cardInfo){
                            //if(!cardNumber) return alert("卡号不存在");
                            // alert("cardNumber="+cardNumber);
                          //1为m1卡标志，2为二代证标志，3为社保卡标志，4为其他卡标志
                          var cardTypeArr =['','m1卡','二代身份证','社保卡','其他卡']
                            var cardType = cardInfo.type;
                            var cardNumber = cardInfo.no;
                            idCard = cardInfo.no;
                            idCard_Type =cardInfo.type;
                          // alert('类型：'+cardTypeArr[cardType]+'，ID:'+cardNumber);
                            $.ajax({
                                url : b.base+ "/ui/outp/patientCard/readVisitCard",
                                type: "POST",
                                data: { cardNumber : cardNumber },
                                success : function (res) {
                                    readTreatmentCard(res,cardType);
                                },
                                error : function (err) {
                                    readTreatmentCard(err,cardType);
                                }
                            });
                        });

                        //
                    });
                // btn.req(readTreatmentCard);

                var btn2 = $btnExt.get("readCard2");
              //  btn2 && btn2.req(readSocialCard);


                var btn3 = $btnExt.get("readCard3");
                btn3 && btn3.el.on("click",function () {
                	 
                	
                    $r.readIdCard(function (userInfo) {
                       // $('#cardType').combobox('setValue', '2');
                       // window.console &&console.log( $('#cardType').combobox('getValue'));
                        //清空
                        $('.form-userInfo').form('reset');
                        $('#cardType').combobox('setValue', '1');
                        $('#pid').val('');

                        //  for (k in userInfo) {
                         //   alert(k+','+userInfo[k]);
                        //  };
                        //默认cardType 为2 -身份证
                        var cardSex="1";
                        if(userInfo.sex =='女'){
                            cardSex="2";
                        }
                        idCard = userInfo.cardSN;
                        idCard_Type= 2;
                        var rs = $.extend({cardType:2},{idCard:userInfo.cardSN},{name:userInfo.name},{idNumber:userInfo.cardCode},
                                {birthday:userInfo.birthday},{contactsAddr:userInfo.address},{sex:cardSex});
                        $ajax.post(b.base+ '/ui/outp/patientCard/readIdCard',rs).done(readIdCard);
                        
                        //特别 入院登记 获取患者信息的  
                        window.InpRegloadForm && window.InpRegloadForm(userInfo);
                    });
                });


                //用户信息查询及表单
                var $cardNo = $('#cardNo');
                var $cardType = $('#cardType');
                // var idcardRule = 'idCardNo["birthday,sex,age"]';
                function cardAddValidate() {
                        var cardType = $('#cardType').combobox('getValue');
                        if (cardType === '1') {
                            $cardNo.validatebox('addRule', 'idCardNo[""]');
                        } else {
                            $cardNo.validatebox('removeRule', 'idCardNo[""]');
                            // $cardNo.validatebox('disableValidation');
                        };
                }

	            setTimeout(function () {
	                cardAddValidate();
	            }, 200);

                $('#cardType').combobox({
                    onChange: function (newV, oldV) {
                        cardAddValidate();
                        if(newV == 1) { 
                        	 var val = $("#cardNo").val();
                        	 isIdCardNo(val,"birthday,sex,age");
                        }
                    }
                });
                $('#cardNo').blur(function () {
                	 
                   var val = $(this).val();
                  // window.console && console.log(val);
                   if($('#cardType').val() == 1 ){ 
                	   isIdCardNo(val,"birthday,sex,age");
                   } 
                });

                // 查询患者个人信息  搜索选择控件调用
                // http://localhost:9090/static/js/plus/pub.js
                var searchBtn = $btnSearch.get("search-grid",true);
                b.searchBtn =searchBtn;
                searchBtn.onLoad(function (d) {
                  // window.console &&console.log("搜索患者信息返回数据：" + d);
                }).onSelect(function (idx, row) {
                    b.initTab(row);
                });
                //
                function openPaymentLayerOrsetCheck (rst,cb) {

                    var userInfo = $('.form-userInfo').sovals();
                    var url = window.location.href;
                    if(url.indexOf("ui/outp/patientCard/cardManage") >= 0) {
                        // 1 判断outp016值是否大于0，大于0，则弹出支付界面，
                        if(rst.data.outp016 > 0 ) {
 
                            var payUrl =b.base +  '/ui/trans/expenses/pay?moduleNumber=9&totalPay='+rst.data.outp016+'&patientId='+ (rst.data.id || userInfo.id)+'&cardNumber='+idCard;
                      
                            $pop.iframePop({
                                title : '支付',
                                content :payUrl,
                                area : ['600px',rst.data.windowHeight+'px'],
                                end : function(){
                                    cb();
                                }
                            });
                            return;
                        }
                        // 2 完成支付
                    }else {
                        //发新诊疗卡时，将挂号界面的是否收取押金费用复选框选中
                    	if(Number($("#chargeDeposit").val())){
                    		$("#isChargeDeposit").attr("checked", true);
	                        $("#isChargeDeposit2").attr("checked", true);
                    	}
                        cb();
                    }
                }


                // 读就诊卡 之后的逻辑
                function readTreatmentCard(rst,cardType) {

                    //用carId发送请求到服务器端
                    if (rst.code === '200' || rst.code === '201') {
                        var data = rst.data;
                        var state = data.state;
                        if (state == '1') {
                            //刷卡在数据库中找到用户信息，即读旧卡
                            //$('.form-userInfo').form('load', data.list);
                            // b.userFormLoadData(data.list);
                            // var birthday = data.list.birthday.substring(0,10);
                            // $("#age").val($T.getAgeByBir(birthday));
                            // $("#birthday").val(birthday);
                            // b.patientId = data.list.id;
                            // b.hasPatId();
                            // b.getAccountBalanceByPid(b.patientId);
                            // 读到患者卡号 显示到页面中
                            b.initTab(data.list);
                            b.listenReadTreatmentCard(data.list);
                            return;//加载完信息退出操作
                        }else{
                            //window.console &&console.log(rst,cardType);
                            //非旧卡， 但卡类型为身份证
                            if(cardType == '2'){
                                 //如果信息不完整，直接提示并退出
                                $pop.alert('该卡未登记，且卡类型为身份证，请直接使用身份证读卡机登记信息!', null, {offset: ['22%', '26%']});
                                return;
                            }
                            if(cardType == '3'){
                             //如果信息不完整，直接提示并退出
                               $pop.alert('该卡未登记，但不支持该医保卡进行发卡操作!', null, {offset: ['22%', '26%']});
                               return;
                            }
                            if(cardType == '4'){
                                 //如果信息不完整，直接提示并退出
                               $pop.alert('未知的卡类型 ，不支持该卡发卡操作!', null, {offset: ['22%', '26%']});
                               return;
                            }
                            //非旧卡,卡类型不为身份证
                            if (!$('.form-userInfo').form('validate')) {
                                //如果信息不完整，直接提示并退出
                                $pop.alert('该卡未登记，发新卡请正确填写必填信息！', null, {offset: ['22%', '26%']});
                                return;
                            }
                            var userInfo = $('.form-userInfo').sovals();
                            //window.console &&console.log(userInfo.id   + " --- " + b.patientId);
                            idCard = userInfo.idCard = rst.data.idCard;//读卡器读出的卡号
                            $ajax.post(b.base + '/ui/outp/patientInfo/isSendCard', userInfo, '是否为该用户发新卡？', false, false, true).done(function (rst) {//用carId发送请求到服务器端
                            if (rst.code === '200' || rst.code === '201') {
                                var data = rst.data;
                                var state = data.state;
                                if(b.patientId){
                                	//就诊卡读发卡时，若页面患者信息本身就是通过搜索框检索出来的， 再点击就诊卡发卡，则按老卡用户发卡步骤操作
                                	if (userInfo) {
                                        userInfo.idCard = idCard;//把新卡卡号传递过去
                                        userInfo.cardType =idCard_Type;
                                        //window.console &&console.log(userInfo.id   + " --- " + b.patientId);
                                        $ajax.post(b.base + '/ui/outp/patientCard/sendCard', userInfo).done(function (rst) {//系统返回201提示为用户发卡成功{"code":"201","msg":"为该用户发卡成功！"}
                                            if (rst.code === '200' || rst.code === '201') {
                                                //$('.form-userInfo').form('load', userInfo);
                                            	b.userFormLoadData(userInfo);
                                                b.patientId = userInfo.id;
                                                b.hasPatId();
                                                hideInfoConfirmBox();

                                                // 弹出 支付 或者 勾选 押金
                                                openPaymentLayerOrsetCheck(rst,function () {
                                                    $pop.msg('发卡成功！');
                                                });
                                            }
                                        });
                                        return ;
                                	}
                                }
                                if (state == '1') {//用户在数据库里有信息档案
                                    if (data.list.length > 0) {
                                        $('.searchHasConfirm').addClass('patientInfoSearchshow');
                                        // 患者列表
                                        $('#getInfoGrid').datagrid('loadData', data.list);
                                    }
                                }
                                if (state == '2') {//用户在数据里无信息档案
                                    //$('#idCard').val(idCard);
                                	userInfo.cardType =cardType;
                                    $ajax.post(b.base + '/ui/outp/patientInfo/createArchives', userInfo).done(function (nrst) {
                                        //系统返回201提示为用户发卡成功,并返回b.patientId {"code":"201","msg":"为该用户发卡成功！","list":{patientId:'1245555'}}
                                        if (nrst.code === '200' || nrst.code === '201') {
                                        	//window.console &&console.log(data,nrst);
                                            b.patientId = nrst.data.id;
                                            $('#pid').val(b.patientId);
                                            b.hasPatId();
                                            // 弹出 支付 或者 勾选 押金
                                            openPaymentLayerOrsetCheck(nrst,function () {
                                                $pop.msg('发卡成功！');
                                            });
                                        }
                                    });
                                }

                            }
                        });
                       }
                    }
                }

                // 读社保卡后的 逻辑
                function readSocialCard(rst) {
                    if (rst.code === '200' || rst.code === '201') {
                        var data = rst.data;
                        var state = data.state;
                        data.list.idCard = data.idCard;
                        if (state == '1') {//唯一匹配到数据
                            //$('.form-userInfo').form('load', data.list);
                            b.userFormLoadData(data.list);
                            // cardAddValidate();
                            b.patientId = data.list.id;
                            b.hasPatId();
                        }
                        if (state == '2') {//匹配到多条
                            //  search  grid box
                            $('.patientInfoSearchBox').addClass('patientInfoSearchshow');
                            searchBtn.$gridbox.datagrid('loadData', data.list);
                        }
                        if (state == '3') {//没有读取到完全匹配的信息
                            // cardAddValidate();
                            //$('.form-userInfo').form('load', data.list);
                            b.userFormLoadData(data.list);
                            $pop.confirm('是否为该患者建档并将身份证绑定为就诊卡？', function () {//确定
                                data.list = JSON.stringify(data.list);
                                //$ajax.post(b.base + '/ui/outp/patientInfo/createArchives',JSON.stringify(data),null,true).done(function (rst) {
                                $ajax.post(b.base + '/ui/outp/patientInfo/createArchives', data).done(function (rst) {
                                    if (rst.code === '200' || rst.code === '201') {
                                        //提交成功后手动操作区域，code为201时，界面自动提示
                                        b.patientId = rst.data.id;
                                        b.hasPatId();
                                    }
                                });
                                return true;
                            }, function () {//取消
                                return true;
                            });
                        }

                    }
                }

                // 读身份证 后逻辑 依赖 searchIdcardConfirm
                function readIdCard(rst) {
                    //读取身份证物理id
                    if (rst.code === '200' || rst.code === '201') {
                        var data = rst.data;
                        var state = data.state;
                        if (state == '1') {//物理ID查到有有用户信息，即已存此身份证卡，返回完整用户信息 {"code":"200","msg":"","list":{id:'1245555',idCard :11212,documentType:1,idNumber: '10214198601140328',sex:'',birthday:'',....}}
                            //$('.form-userInfo').form('load', data.list);
                            b.userFormLoadData(data.list);
                            var birthday = data.list.birthday.substring(0,10);
                            $("#age").val($T.getAgeByBir(birthday));
                            $("#birthday").val(birthday);
                            $("#accountBalance").val(data.account.accountBalance);
                            // cardAddValidate();
                            b.patientId = data.list.id;
                            b.getAccountBalanceByPid(b.patientId);
                            b.hasPatId();
                            b.listenReadIDCard(data.list);
                            return;
                        }
                        if (state == '2') {//身份证号码或姓名查询返回多条信息记录，则显示在弹出的grid中，供操作者选择确认
                            idCardInfo = data._entity;
                            idCardInfo.idCard = rst.data.idCard;
                            //idCardId = rst.data.idCard;
                            $('.searchIdcardConfirm').addClass('patientInfoSearchshow');
                            // 患者列表
                            $('#idCardGrid').datagrid('loadData', data.list);
                            return;
                        }
                        if (state == '3') {//物理ID、身份证号码、姓名在数据库里均无记录，此时界面上用户必填信息已填写
                            idCardInfo = data.list;//{"code":"200","state":4,msg":"","data":{name:'张三',idCard :11212,documentType:1,idNumber: '10214198601140328',sex:1,birthday:'2010-01-12'}}
                            idCardInfo.idCard = rst.data.idCard;
                            //window.console &&console.log(idCardInfo);
                        	//window.console &&console.log(idCardInfo.idCard);
                            //idCardId = rst.data.idCard;
                            addNewIdcard();
                        }

                    }
                }


                // tab 控制逻辑 依赖 tabChangeEvent  tabPtId
                $('#tabsA').tabs({
                    onSelect: function (title, index) {
                        var  tabId = "tabsA";
                        var tab = $('#' + tabId).tabs('getSelected');
                        var index = $('#' + tabId).tabs('getTabIndex', tab);
                        if (b.patientId !== tabPtId[tabId][index]) {
                            tabPtId[tabId][index] = b.patientId;
                            tabChangeEvent[tabId][index]();
                        };

                        // 发卡始终刷新 因为存在PID 不变的情况
                        if(index  == 2  ) {
                            tabChangeEvent[tabId][index]();
                        }
                    }
                });



                // 初始化 体测记录表

                $grid.newGrid("#examination-grid",{
                    fitParent: true,
                    pagination : false,
                    fitColumns : false,
                    fit: true,
                    columns:[[
                        {title:'操作',field:'op',width:70, rowspan :2 ,formatter: function (v,row,index) {
                            return '<span class="s-op s-op-del icon-del" title="删除"></span>';
                        }},
                        {title:'记录时间', align :"center",field:'modifyDate' ,rowspan :2 ,align :'center' ,width:140  },
                        {title:'远视力',  field:'id3', colspan:4, align :'center' },
                        {title:'近视力',  field:'id2', colspan:2, align :'center',width:100},
                        {title:'矫正视力',  field:'id4', colspan:2, align :'center' },
                        {title:'眼压mmHg',field:'id5',  colspan:2,align :'center' },
                        {title:'',field:'id6',  colspan:4,align :'center',hidden:true }
                    ],[
                        {title:'左眼',field:'osDistVision',width:70 },
                        {title:'说明',field:'osDistVisionRemark',width:100 },
                        {title:'右眼',field:'odDistVision',width:70 },
                        {title:'说明',field:'odDistVisionRemark',width:100 },

                        {title:'左眼',field:'osNearVision',width:70 },
                        {title:'右眼',field:'odNearVision',width:70 },

                        {title:'左眼',field:'osCorrVision',width:70},
                        {title:'右眼',field:'odCorrVision',width:70},
                        {title:'左眼',field:'osIop',width:70},
                        {title:'右眼',field:'odIop',width:70},
                        {title:'id',field:'id',hidden:true},
                        {title:'patientId',field:'patientId',hidden:true},
                        {title:'patientName',field:'patientName',hidden:true},
                        {title:'regNumber',field:'regNumber',hidden:true}
                    ]],

                    onDblClickRow : function (index,row) {
                    },
                    onBeforeLoad : function(data){
                        // debugger;
                        var patId =  $('#pid').val();
                        if(patId==null || patId==''){
                            return false;
                        }
                    },
                    onSelect : function (i,row) {
                        $("#examination-form").form('load',row);
                    },
                    onLoadSuccess:function(data){
                    },
                    queryParams : {
                        patientId :  $('#pid').val()
                    },
                    url:  b.base + '/ui/outp/nurse/getPhysicExamListByPatientId',
                    offset :-78
                });







                // 依赖 patientId
                var tabChangeEvent = {
                    tabsA: [//tab切换事件集合
                        function basePatientInfoChange() {//基础信息
//                        window.console && console.log('basePatientInfoChange run');
                        },
                        function examinationChange () {
                            $("#examination-grid").datagrid('reload',{patientId: b.patientId});
                        },
                        function patientCardChange() {//就诊卡信息
                            $('#grid-l-card').datagrid('reload', {patientId: b.patientId});
//                        window.console && console.log('patientCardChange run');
                        },
                        function contactInfoChange() {//联系人信息
//                        window.console && console.log('contactInfoChange run');
                            $('#grid-l-contact').datagrid('reload', {patientId: b.patientId});
                        },
                        function integralInfoChange() {//积分信息
                        },
                        function a() {//医保信息
                            $ajax.post(b.base + '/ui/outp/patientMedicalInsure/findByPatientId', {patientId: b.patientId}).done(function (rst) {
                                if (rst.code === '200') {
                                    try{
                                    rst.data.servantSign = ['是', '否'][rst.data.servantSign];
                                    for( var a in rst.data ) {
                                        $("#" + a ).text(rst.data[a]);
                                    }
                                    } catch (e) {
                                        console.error(e);
                                    }
                                    // $('.form-insurance').form('load', rst.data);
                                }
                            });
                        },
                        function advancePayChange() {
                            //预交金信息
                            $('#grid-l-money').datagrid('reload', {patientId: b.patientId});
                        },
                        function hosInChange() {
                            //院内信息
                            $('#grid-l-prompt').datagrid('reload', {patientId: b.patientId});
                        }
                    ]
                }



                //tab 中 grid 的初始化
                // grid-l-card 依赖
               window.windowHeight = null;
                $grid.newGrid("#grid-l-card", {
                    fit: true,
                    pagination: false,
                    // fitColumns : false,
                    columns: [[
                    	{title: 'id', field: 'id', hidden: true},
                        {title: '患者ID', field: 'patientId', hidden: true},
                        {title:'操作' ,field:'opt', width:80 ,formatter: function (value,row,index) {
                            return '<span class="s-op op-lock icon-lock" title="挂失" style="font-size:16px;"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="s-op s-op-run op-unlock icon-unlock_alt" style="font-size:16px;top:1px;" title="解挂"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="s-op op-refund icon-credit_card" title="退卡"></span>';
                         }, hidden : b.pagePath !== "cardMgr" },
                        {title: '类型', field: 'cardTypeName', width: 80},
                        {title: '卡号', field: 'cardNumber', width: 80},
						{title: '押金', field: 'cardDeposit', width: 80},
						{title: '状态', field: 'cardStateName', width: 80},
						{title: 'cardState', field: 'cardState', width: 80, hidden: true},
                        //{title: '发卡机构', field: 'hospName', width: 80},
                        {title: '发卡时间', field: 'issuingDate', width: 140},
                        {title: '发卡人', field: 'issuingOperatorName', width: 80},
                        {title: '备注', field: 'remarks', width: 80}
                    ]],
                    onBeforeLoad: function (param) {
                        if (!param.patientId) {
                            return false;
                        }
                    },
                    onLoadSuccess: function (data) {
                    	window.windowHeight = data.windowHeight;
                    },
                    url: b.base + '/ui/outp/patientCard/list',
                    height: 'auto'
                    // ,offset :-55
                });
                // useage 若干处 当前tab 页面点击等
                $grid.newGrid("#grid-l-contact", {
                    fit: true,
                    //checkOnSelect : false,
                    //selectOnCheck : false,
                    //singleSelect : false,
                    //ctrlSelect : true,
                    pagination: false,
                    fitColumns: false,
                    columns: [[
                        {title: 'id', field: 'id', width: 50, hidden: true},
                        {title: 'creator', field: 'creator', width: 50, hidden: true},
                        {
                            title: '操作', field: 'op', width: 70, formatter: function (v, row, index) {
                            return '<span class="s-op s-op-del icon-del" title="删除"></span>'
                            //'<span class="s-op s-op-up icon-arrow-up" title="向上"></span> <span class="s-op s-op-down icon-arrow-down" title="向下"></span>'
                        }
                        },
                        {title: '患者', field: 'patientId', width: 50, hidden: true},
                        {
                            title: '患者关系', field: 'relation', width: 150, formatter: function (v, row, index) {

                            var rval = '';
                            $.each(kinsfolkAppelArr, function () {
                                if (this.valueCode == v) {
                                    rval = this.valueDesc;
                                    return false;
                                }
                            });
                            return rval;
                        }, editor: {
                            type: 'combobox', options: {
                                valueField: 'valueCode',
                                textField: 'valueDesc',
                                limitToList: true,
                                reversed: true,
                                data: kinsfolkAppelArr,
                                //url : b.base + '/ui/sys/dict/getList?paraType=relation',
                                missingMessage: '请选择患者关系',
                                required: true
                            }
                        }
                        },
                        {title: '联系人姓名', field: 'name', width: 80, editor: {type: 'validatebox', options: {required: true,validType:'maxlength[30]'}}},
                        {
                            title: '证件类型', field: 'certType', width: 110, formatter: function (v, row, index) {
                            var rval = '';
                            $.each(certTypeArr, function () {
                                if (this.valueCode == v) {
                                    rval = this.valueDesc;
                                    return false;
                                }
                            });
                            return rval;
                        }, editor: {
                            type: 'combobox', options: {
                                valueField: 'valueCode',
                                textField: 'valueDesc',
                                limitToList: true,
                                reversed: true,
                                data: certTypeArr,
                                //url : b.base + '/ui/sys/dict/getList?paraType=document_type',
                                onSelect: function (record) {
                                    var $code = $e.getTxt($(this), 'certNumber');
                                    if (record.valueCode == '1') {
                                        $code.validatebox('addRule', 'idCardNo[""]');
                                    } else {
                                        $code.validatebox('removeRule', 'idCardNo[""]');
                                    };
                                },
                                missingMessage: '请选择证件类型',
                                required: true
                            }
                        }
                        },
                        {title: '证件号码', field: 'certNumber', width: 150, editor: {type: 'diy', options: {required: true,validType:'maxlength[25]'}}},
                        {title: '联系电话', field: 'tel', width: 110, editor: {type: 'validatebox', options: {required: true,validType:'maxlength[15]'}}},
                        {title: '登记人', field: 'creatorName', width: 80},
                        {title: '登记时间', field: 'createDate', width: 130},
                        {title: '备注', field: 'remarks', width: 320, align: 'left', titletip:true,  editor: {type: 'validatebox' , options: { validType:'maxlength[50]'  } }}
                    ]],
                    onClickCell: function (index, field, value) {
                        contactEditInitData.patientId = b.patientId;
                        $e.editRow({
                            grid: '#grid-l-contact',
                            index: index,
                            cellField: field,
                            focusField: 'relation',
                            canAdd: true,
                            initData: contactEditInitData
                        });
                    },
                    onBeforeLoad: function (param) {
                        if (!param.patientId) {
                            return false;
                        }
                    },
                    onLoadSuccess: function () {
                    },
                    url: b.base + '/ui/outp/patientContacts/list',
                    height: 'auto'
                    // ,offset :-55
                });

                // #grid-l-money 初始化
                // 依赖 useage
                $grid.newGrid("#grid-l-money", {
                    fit: true,
                    pagination: false,
                    // fitColumns : false,
                    columns: [[
                        {title: '交易时间', field: 'transTime', width: 80},
                        {title: '交易类型', field: 'transChangeTypeName', width: 80},
                        {title: '交易单号', field: 'transNumber', width: 80},
                        {title: '交易场景', field: 'transSceneName', width: 80},
                        {title: '交易金额', field: 'transAmount', width: 80},
                        {title: '余额', field: 'transBalance', width: 80}
                        //{title: '创建医院', field: 'orgCode', width: 80}
                    ]],
                    onLoadSuccess: function () {
                    },
                    onBeforeLoad: function (param) {
                        if (!param.patientId) {
                            return false;
                        }
                    },
                    url: b.base + '/ui/trans/prepayment/getByPatientId',
                    height: 'auto'
                    // offset :0
                });

                //  院内信息 grid-l-prompt 初始化  依赖 specialInfoD  icon-del 删除
                $grid.newGrid("#grid-l-prompt", {
                    fit: true,
                    pagination: false,
                    // fitColumns : false,
                    columns: [[
                        {title: 'id', field: 'id', width: 50, hidden: true},
                        {title: 'modifer', field: 'modifer', width: 50, hidden: true},
                        {title: 'orgCode', field: 'orgCode', width: 50, hidden: true},
                        {
                            title: '操作', field: 'op', width: 70, formatter: function (v, row, index) {
                            return '<span class="s-op s-op-del icon-del" title="删除"></span>'
                            //'<span class="s-op s-op-up icon-arrow-up" title="向上"></span> <span class="s-op s-op-down icon-arrow-down" title="向下"></span>'
							}
                        },{
                            title: '提示类型', field: 'specialInfoType', width: 120, formatter: function (v, row, index) {
                                var rval = '';
                                $.each(specialInfoTypeD, function () {
                                    if (this.valueCode == v) {
                                        rval = this.valueDesc;
                                        return false;
                                    }
                                });
                                return rval;
                            }, editor: {
                                type: 'combobox', options: {
                                    valueField: 'valueCode',
                                    textField: 'valueDesc',
                                    limitToList: true,
                                    reversed: true,
                                    data: specialInfoTypeD,
                                    //url : b.base + '/ui/sys/dict/getListForComb?tag=special_info_type',
                                    missingMessage: '请选择提示类型',
                                    required: true
                                }
                            }
                        },{
                            title: '提示内容', field: 'specialInfo', width: 120, formatter: function (v, row, index) {
                                var rval = '';
                                $.each(specialInfoD, function () {
                                    if (this.valueCode == v) {
                                        rval = this.valueDesc;
                                        return false;
                                    }
                                });
                                return rval;
                            }, editor: {
                                type: 'combobox', options: {
                                    valueField: 'valueCode',
                                    textField: 'valueDesc',
                                    limitToList: true,
                                    reversed: true,
                                    data: specialInfoD,
                                    //url : b.base + '/ui/sys/dict/getListForComb?tag=special_info_type',
                                    missingMessage: '请选择提示内容',
                                    required: true
                                }
                            }
                        },
                        {title: '备注', field: 'remarks', width: 200, editor: {type: 'validatebox' , options: { validType:'maxlength[50]'  } }},
                        {title: '录入医院', field: 'hospName', width: 120},
                        {title: '录入人', field: 'modiferName', width: 80},
                        {title: '录入时间', field: 'modifyDate', width: 110}
                    ]],
                    onClickCell: function (index, field, value) {
                        promptEditInitData.patientId = b.patientId;
                        $e.editRow({
                            grid: '#grid-l-prompt',
                            index: index,
                            cellField: field,
                            focusField: 'content',
                            canAdd: true,
                            initData: promptEditInitData
                        });
                    },
                    onBeforeLoad: function (param) {
                        if (!param.patientId) {
                            return false;
                        }
                    },
                    onLoadSuccess: function () {
                    },
                    url: b.base + '/ui/outp/patientSpecialWarn/list',
                    height: 'auto'
                    // ,offset :-55
                });





                // #grid-l-contact   联系人 保存按钮
                $('.btn-contact-save').click(function () {
                    $e.ifEndEdit(function () {//判断是否可以结束编辑状态
                        // var allData = $e.getGridData('#grid-l-contact');//获取grid所有行的值
                        var updateData = $('#grid-l-contact').datagrid('getChanges', 'updated');//获取grid编辑的行值
                        var insertData = $('#grid-l-contact').datagrid('getChanges', 'inserted');//获取grid插入的值
                        var changetData = updateData.concat(insertData);
                        if (changetData.length) {//只获取变化值，回传changetData
                            $ajax.post(b.base + '/ui/outp/patientContacts/save', JSON.stringify(changetData), true, true).done(function (rst) {
                                if (rst.code === '200' || rst.code === '201') {
                                    $('#grid-l-contact').datagrid('reload');
                                };
                            });
                        } else {
                            $pop.msg('请填写完整联系人信息！');
                        };
                    }, '#grid-l-contact');

                });
                //  #grid-l-contact   联系人 删除按钮
                $('.userContactGridBox').on('click', '.s-op-del', function () {//删除单条
                    var row = $('#grid-l-contact').datagrid('getSelected');
                    var ix = $('#grid-l-contact').datagrid('getRowIndex', row);
                    if (row.id) {
                        $ajax.post(b.base + '/ui/outp/patientContacts/delete', {id: row.id}, '你确定删除此记录吗？').done(function (rst) {
                            if (rst.code === '200' || rst.code === '201') {
                                $('#grid-l-contact').datagrid('deleteRow', ix);
                            };
                        });
                    } else {
                        $('#grid-l-contact').datagrid('deleteRow', ix);
                    }
                    ;
                    return false;
                });
                //  #grid-l-contact   联系人 新增按钮
                $('.btn-contact-add').click(function () {
                    contactEditInitData.patientId = b.patientId;
                    contactEditInitData.certType = 1;
                    $e.addNewRow({
                        grid: '#grid-l-contact',
                        focusField: 'kinsfolkAppel',
                        initData: contactEditInitData
                    });
                });


                // 读卡 回写医保信息
                $('.btn-updateInsurance').click(function () {//医保卡更新操作
                    $ajax.post(b.base + '/ui/outp/patientMedicalInsure/readMedicareCard').done(function (rst) {
                        if (rst.code === '200') {
                            $('.form-insurance').form('load', rst.data);
                            $ajax.post('json/true.js', rst.data).done(function (rst) {

                            });
                        }
                    })
                });

                // 院内信息 新增
                $('.btn-prompt-add').click(function () {
                    promptEditInitData.patientId = b.patientId;
                    $e.addNewRow({
                        grid: '#grid-l-prompt',
                        focusField: 'content',
                        initData: promptEditInitData
                    });
                });
                // 院内信息 删除
                $('.userPromptGridBox').on('click', '.s-op-del', function () {
                    //删除单条
                    var row = $('#grid-l-prompt').datagrid('getSelected');
                    var ix = $('#grid-l-prompt').datagrid('getRowIndex', row);
                    if (row.id) {
                        $ajax.post(b.base + '/ui/outp/patientSpecialWarn/delete', {id: row.id}, '你确定删除此记录吗？').done(function (rst) {
                            // window.console && console.log(rst);
                            if (rst.code === '200' || rst.code === '201') {
                                $('#grid-l-prompt').datagrid('deleteRow', ix);
                            }
                            ;
                        });
                    } else {
                        $('#grid-l-prompt').datagrid('deleteRow', ix);
                    }
                    ;
                    return false;
                });
                //院内信息  保存
                $('.btn-prompt-save').click(function () {
                    $e.ifEndEdit(function () {//判断是否可以结束编辑状态
                        var updateData = $('#grid-l-prompt').datagrid('getChanges', 'updated');//获取grid编辑的行值
                        var insertData = $('#grid-l-prompt').datagrid('getChanges', 'inserted');//获取grid插入的值
                        var changetData = updateData.concat(insertData);
                        // window.console && console.log(updateData, insertData, changetData);
                        if (changetData.length) {//只获取变化值，回传changetData
                            $ajax.post(b.base + '/ui/outp/patientSpecialWarn/save', JSON.stringify(changetData), true, true).done(function (rst) {
                                // window.console && console.log(rst);
                                if (rst.code === '200' || rst.code === '201') {
                                    $('#grid-l-prompt').datagrid('reload');
                                }
                                ;
                            });
                        } else {
                            $pop.msg('请填写院内提示！');
                        }
                        ;
                    }, '#grid-l-prompt');

                });






                // 相关弹出 窗口的 逻辑
                // 初始化



                $grid.newGrid("#getInfoGrid", {
                    // fit: true,
                    pagination: false,
                    // fitColumns : false,
                    columns: [[
                        {title: 'id', field: 'id', width: 80, hidden: true},
                        {title: '姓名', field: 'name', width: 60},
                        {title: '性别', field: 'sexName', width: 50},
                        {title: '出生日期', field: 'birthday', width: 80},
                        {title: '联系电话', field: 'tel1', width: 100},
                        {title: '身份证号', field: 'idNumber', width: 180},
                        {title: '联系地址', field: 'contactsAddr', width: 180, align: 'left'}
                    ]],
                    onLoadSuccess: function () {
                    },
                    //url:'json/userInfo.js',
                    // height: 'auto'
                    height: 324
                    // ,offset :-55
                });

                // 设置用户信息表单某些 初始值
                setTimeout(function () {
                    $('#cardType').combobox('setValue', '1');
                }, 200);
                function hideInfoConfirmBox () {
                    $('.searchHasConfirm').removeClass('patientInfoSearchshow');
                }
               // 多用户选则事件  确定是老用户
                $('.btn-confirmOldUser').click(function () {
                    //为老用户发卡
                    var userInfo = $('#getInfoGrid').datagrid('getSelected');
                    if (userInfo) {
                        userInfo.idCard = idCard;//把新卡卡号传递过去
                        userInfo.cardType =idCard_Type;
                        $ajax.post(b.base + '/ui/outp/patientCard/sendCard', userInfo).done(function (rst) {//系统返回201提示为用户发卡成功{"code":"201","msg":"为该用户发卡成功！"}
                            if (rst.code === '200' || rst.code === '201') {
                                //$('.form-userInfo').form('load', userInfo);
                                b.userFormLoadData(userInfo);
                                b.patientId = userInfo.id;
                                b.hasPatId();
                                hideInfoConfirmBox();
                                openPaymentLayerOrsetCheck(rst,function () {
                                    $pop.msg('发卡成功！');
                                });
                            }
                        });
                    } else {
                        $pop.msg('请选择用户行！');
                    }
                });

                $('.btn-confirmNewUser').click(function () {
                    //为新用户发卡
                    var userInfo = $('.form-userInfo').sovals();
                    userInfo.idCard = idCard;//把新卡卡号传递过去
                    userInfo.cardType =idCard_Type;
                   // console.log(userInfo);
                    $ajax.post(b.base + '/ui/outp/patientInfo/createArchives', userInfo).done(function (rst) {//系统返回201提示为用户发卡成功,并返回patientId {"code":"201","msg":"为该用户发卡成功！","list":{patientId:'1245555'}}
                        if (rst.code === '200' || rst.code === '201') {
                            b.patientId = rst.data.id;
                        	//patientId = rst.list.id;
                            b.hasPatId();
                            $('#pid').val(b.patientId);
                            hideInfoConfirmBox();
                            openPaymentLayerOrsetCheck(rst,function () {
                                $pop.msg('发卡成功！');
                            });
                        }
                    });
                });


                $('.btn-colseConfirmPop').click(function () {//关闭不发卡
                	 
                    hideInfoConfirmBox();
                });


                $grid.newGrid("#getInfoGrid", {
                    // fit: true,
                    pagination: false,
                    // fitColumns : false,
                    columns: [[
                        {title: 'id', field: 'id', width: 80, hidden: true},
                        {title: '姓名', field: 'name', width: 70},
                        {title: '性别', field: 'sexName', width: 60},
                        {title: '出生日期', field: 'birthday', width: 150},
                        {title: '联系电话', field: 'tel1', width: 110},
                        {title: '身份证号', field: 'idNumber', width: 200},
                        {title: '联系地址', field: 'contactsAddr', width: 220, align: 'left'}
                    ]],
                    onLoadSuccess: function () {
                    },
                    //url:'json/userInfo.js',
                    // height: 'auto'
                    fitHeight:false,
                    height: 324
                    // ,offset :-55
                });
                if($("#getInfoGrid2").length >0 ) {
                   $("#getInfoGrid2").datagrid({
                       // fit: true,
                       pagination: false,
                       // fitColumns : false,
                       columns: [[
                           {title: 'id', field: 'id', width: 80, hidden: true},
                           {title: '姓名', field: 'name', width: 70},
                           {title: '性别', field: 'sexName', width: 60},
                           {title: '出生日期', field: 'birthday', width: 150},
                           {title: '联系电话', field: 'tel1', width: 110},
                           {title: '身份证号', field: 'idNumber', width: 200},
                           {title: '联系地址', field: 'contactsAddr', width: 220, align: 'left'}
                       ]],
                       onLoadSuccess: function () {
                       },
                       //url:'json/userInfo.js',
                       // height: 'auto'
                       height: 350
                       // ,offset :-55
                   });

                }


                //读取身份证处理---------------------begin
                $grid.newGrid("#idCardGrid", {
                    // fit: true,
                    pagination: false,
                    fitHeight :false,
                    // fitColumns : false,
                    columns: [[
                        {title: 'id', field: 'id', width: 80, hidden: true},
                        {title: '姓名', field: 'name', width: 60},
                        {title: '性别', field: 'sexName', width: 50},
                        {title: '出生日期', field: 'birthday', width: 80},
                        {title: '联系电话', field: 'tel1', width: 100},
                        {title: '身份证号', field: 'idNumber', width: 180},
                        {title: '余额', field: ' ', width: 50},
                        {title: '联系地址', field: 'contactsAddr', width: 180, align: 'left'},

                    ]],
                    onLoadSuccess: function () {
                    },
                    //url:'json/userInfo.js',
                    // height: 'auto'
                    height: 324
                    // ,offset :-55
                });

                function hideIdcardConfirmBox() {
                    $('.searchIdcardConfirm').removeClass('patientInfoSearchshow');
                }

                $('.btn-idcardConfirm').click(function () {
                    var userInfo = $('#idCardGrid').datagrid('getSelected');
                    if (userInfo) {
                        //$('.form-userInfo').form('load', userInfo);
                        b.userFormLoadData(userInfo);

                        //userInfo.cardId = cardId;//把新卡卡号传递过去
                        b.patientId = userInfo.id;
                        b.hasPatId();
                        hideIdcardConfirmBox();
                        //window.console && console.log(idCard);
                        //userInfo.idCard = idCardInfo.idCard;
                        userInfo.idCard = idCard;
                        userInfo.cardType =idCard_Type;
                        $ajax.post(b.base + '/ui/outp/patientCard/sendCard', userInfo, '是否将此身份证绑定为患者的就诊卡？').done(function (rst) {//将身份证物理ID传到服务器，对应患者资料，保存成一条新的卡记录，系统返回201提示为用户绑定成功,(并返回idCard) {"code":"201","msg":"绑定身份证成功！","list":{idCard :11212}}
                            if (rst.code === '200' || rst.code === '201') {
                                $('#idCard').val(idCard);
                                b.patientId = userInfo.id;
                                b.hasPatId();
                                hideIdcardConfirmBox();
                                //$('.form-userInfo').form('load', {idCard: idCard});//将idCard load到表单中
                            }
                        });
                    } else {
                        $pop.msg('请选择用户行！');
                    }
                });

                $('.btn-colseIdcardConfirm').click(function () {
                    hideIdcardConfirmBox();
                    addNewIdcard();
                });
                // 新增身份证
                function addNewIdcard() {
                    var userI = {
                        name: idCardInfo.name,
                        document_type: idCardInfo.document_type,
                        idNumber: idCardInfo.idNumber,
                        sex: idCardInfo.sex,
                        birthday: idCardInfo.birthday,
                        contactsAddr:idCardInfo.contactsAddr
                    }
                    //$('.form-userInfo').form('load', userI);//
                    b.userFormLoadData(userI);
                    var birthday = userI.birthday.substring(0,10);
                    $("#age").val($T.getAgeByBir(birthday));
                    $("#birthday").val(birthday);
                   // if (!$('.form-userInfo').form('validate')) {//如果信息不完整，直接提示并退出
                   //     $pop.msg('身份证卡未登记，请填写正确信息后建档！', null, {offset: ['22%', '26%']});
                   //     return;
                  //  }

                    var userInfo = $('.form-userInfo').sovals();
                    //userInfo.idCard = idCardInfo.idCard;
                    userInfo.idCard = idCard;
                    userInfo.cardType =idCard_Type;
                    $ajax.post(b.base + '/ui/outp/patientInfo/createArchives', userInfo, '是否用此身份证为患者 '+userInfo.name+' 建档并绑定就诊卡？').done(function (rst) {//{"code":"201","msg":"为该用户发卡成功！","list":{b.patientId:'1245555',idCard :11212}}
                        if (rst.code === '200' || rst.code === '201') {
                            b.patientId = rst.data.id;
                            //$('.form-userInfo').form('load', data.list);//将idCard load到表单中
                            b.userFormLoadData(rst.data);
                            b.hasPatId();
                            $pop.msg('发卡成功！');
                        }
                    });
                }


                $('#birthday').blur(function(){//根据生日计算年龄
                    var bir = $(this).val();
                  $('#age').val($T.getAgeByBir(bir));
                });

                window.beforeSendPInfo = function () {
                    var pid = $('#pid').val();
                    if (!pid) {
                        $pop.alert('该患者未建档，请先建档发卡！');
                    } else {
                        return true;
                    }
                }
                window.afterSendPInfo = function (rst) {
                    if (rst.code == '200' || rst.code == '201') {
                        $('#pid').val(rst.data.id);
                        b.patientId = rst.data.id;
                        b.hasPatId();
                    }
                }
                $('.btn-clearUserF').click(function () {
                    $('.form-userInfo').form('reset');
                    $('#cardType').combobox('setValue', '1');
                    // 保留 内部清空按钮 保留 pid
                    $('#pid').val(b.patientId);

                });

            });

        },
        clear: function () {
            //1.清空 基本信息表
            var b = back;
            b.patientId = null;
            $('#pid').val("");
            b.searchBtn.ipt.val("");
            tabPtId = {
                tabsA: ['', '', '', '', '', '', '']
            };
            $('.form-userInfo').form('clear');
            $('#grid-l-card').datagrid("loadData",{ total: 0, rows: [] });
            $('#examination-grid').datagrid("loadData",{ total: 0, rows: [] });
            $('#grid-l-contact').datagrid("loadData",{ total: 0, rows: [] });
            $('#grid-l-money').datagrid("loadData",{ total: 0, rows: [] });
            $('#grid-l-prompt').datagrid("loadData",{ total: 0, rows: [] });




        }

    }
    return back;
});

