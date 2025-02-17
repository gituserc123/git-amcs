<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>运营维护 - 爱尔医院</title>
[#include "/WEB-INF/views/common/include_resources.ftl"]
<style type="text/css">
code, pre{font-family:Helvetica Neue,Helvetica,Tahoma,Verdana,Arial,"Microsoft YaHei","SimSun";font-size: 14px;}

.h1-title{font-size: 24px;line-height:30px;text-align: center;padding:25px 0 20px;color:#000;}
.likeTabs{width:auto;}
.likeTabs .tab-last{border-right:0;}
.tabs li a.tabs-inner{padding:0 45px;}
.cont-grid{border-top:1px solid #e3e3e3;}

.tabCont{position: absolute;left:0;right:0;top:157px;bottom:5px;}
.tabContHide{visibility: hidden;}
.cont-grid{overflow: hidden;zoom: 1;}

.table-list-a th{background:#fff4e0;border-color:#dad9d9;}
.table-list-a td{font-size: 14px;padding:8px;font-weight: bold;color:#0366ff;border-color:#dad9d9;}
.table-list-a td.td-num{cursor:hand;cursor:pointer;}
.table-list-a b{color:#ff3e3e;}

.writeArea{position: relative;margin:0 10px 20px 0;}
/* .s-writeEdit{
position: absolute;
top:5px;right:5px;font-size:20px;color:#0797de;
cursor:hand;cursor:pointer;
-webkit-transition:all 0.2s 0s;-moz-transition:all 0.2s 0s;-o-transition:all 0.2s 0s;transition:all 0.2s 0s;}
.s-writeEdit:hover{transform: scale(1.2,1.2);} */
.pre-write{border:0;background:#f3f3f3;padding:15px;color:#333;line-height:1.5em;}
.writeEditWrap{display: none;}
.txta-r{height:400px;font-size: 14px;line-height:1.8em;}

</style>
</head>

<body>
<div class="searchHead bob-line-sub">
    <form id="sbox-0" class="soform form-enter">
        <input id="orgIdB" type="hidden" class="txt" name="orgId" value="112">
        <label class="lab-inline">日期：</label>
        <input class="txt txt-validate inline so-date" type="text" id="month" name="maintainDate" data-opt="{type:'month',maxDate:'${sysdate}'}" value="" />
        <button type="button" class="btn btn-small btn-primary btn-search">查 询</button>
    </form>
</div>

<h1 class="h1-title"><span class="s-year">2019</span>年<span class="s-month">1</span>月度运营情况</h1>
<ul class="tabs likeTabs">
    <li class="tabs-first tabs-selected" rel="0"><a href="#" class="tabs-inner"><span class="tabs-title">发布情况</span></a></li>
    <li rel="1"><a href="#" class="tabs-inner"><span class="tabs-title">线上问题</span></a></li>
    <!--<li rel="2"><a href="#" class="tabs-inner"><span class="tabs-title">运营情况总结</span></a></li>-->
</ul>
<div class="tabCont tabCont-0">
    <div class="pad-20 ">
        <form class="soform form-validate form-a form-enter mar-b10" method="post" action="${base}/ui/sys/sysOperation/saveOrUpdate" data-opt="{form:'.form-a',callback:'afterSubmit'}">
            [@shiro.hasPermission name = "SysOperation:save"]
            <div class="writeArea">
                <!-- <span class="s-writeEdit s-edit-a icon-edit" rel="a"></span> -->
                <pre class="pre-write pre-write-a"></pre>
                <p class="row-btn"><input type="button" class="btn btn-primary s-writeEdit" name="btnSubmit" value="编辑" rel="a" /></p>
            </div>

            <div class="writeEditWrap">
                <textarea class="txta txta-r txta-ra txt-validate" name="publishContent"></textarea>
                <input class="txt-id" name="id" value="" type="hidden"/>
                <input class="txt-month" type="hidden" name="maintainDate" value="" />
                <p class="row-btn">
                    <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="提 交" />
                    <input type="button" class="btn btn-cancelEdit" name="btnCancel" value="取 消" />
                </p>
            </div>
            [/@shiro.hasPermission]
        </form>
    </div>
</div>

<div class="tabCont tabCont-1 tabContHide">
    <table class="table-list-a mar-b10" cellspacing="0" cellpadding="0">
        <tr>
            <th rowspan="2">本月问题数量</th>
            <th colspan="6">问题类型</th>
            <th colspan="3">问题级别</th>
        </tr>
        <tr>
            <th>系统BUG</th>
            <th>需求变更</th>
            <th>配置问题</th>
            <th>操作问题</th>
            <th>业务问题</th>
            <th>待定</th>
            <th>紧急问题</th>
            <th>重要问题</th>
            <th>一般问题</th>
        </tr>
        <tr>
            <td class="td-num" rel=""><span class="s-num totalCount">0</span> <b class="b-spNum unDealCount"></b></td>
            <td class="td-num" rel="typeCode:0"><span class="s-num typeCode0">0</span> <b class="b-spNum unDealTypeCode0"></b></td>
            <td class="td-num" rel="typeCode:1"><span class="s-num typeCode1">0</span> <b class="b-spNum unDealTypeCode1"></b></td>
            <td class="td-num" rel="typeCode:3"><span class="s-num typeCode3">0</span> <b class="b-spNum unDealTypeCode3"></b></td>
            <td class="td-num" rel="typeCode:2"><span class="s-num typeCode2">0</span> <b class="b-spNum unDealTypeCode2"></b></td>
            <td class="td-num" rel="typeCode:4"><span class="s-num typeCode4">0</span> <b class="b-spNum unDealTypeCode4"></b></td>
            <td class="td-num" rel="typeCode:5"><span class="s-num typeCode5">0</span> <b class="b-spNum unDealTypeCode5"></b></td>
            <td class="td-num" rel="problemLevel:0"><span class="s-num level0">0</span> <b class="b-spNum unDealLevel0"></b></td>
            <td class="td-num" rel="problemLevel:1"><span class="s-num level1">0</span> <b class="b-spNum unDealLevel1"></b></td>
            <td class="td-num" rel="problemLevel:2"><span class="s-num level2">0</span> <b class="b-spNum unDealLevel2"></b></td>
        </tr>
    </table>

    <div class="cont-grid">
        <!--<h2 class="h2-title-a"><span class="s-title">领用记录</span></h2>-->
        <div id="gridBox"></div>
    </div>
</div>

<div class="tabCont tabCont-2 tabContHide">

    <div class="pad-20">
        <form class="soform form-validate form-b form-enter mar-b10" method="post" action="${base}/ui/sys/sysOperation/saveOrUpdate" data-opt="{form:'.form-b',callback:'afterSubmit'}">
            [@shiro.hasPermission name = "SysOperation:save"]
            <div class="writeArea">
                <!-- <span class="s-writeEdit s-edit-b icon-edit" rel="b"></span> -->
                <pre class="pre-write pre-write-b"></pre>
                <!-- <input type="button" class="btn btn-primary s-writeEdit" name="btnSubmit" value="编辑" rel="b" /> -->
                <p class="row-btn"><input type="button" class="btn btn-primary s-writeEdit" name="btnSubmit" value="编 辑" rel="b" /></p>
            </div>

            <div class="writeEditWrap">
                <textarea class="txta txta-r txta-rb txt-validate" name="operationContent"></textarea>
                <input class="txt-id" name="id" value="" type="hidden"/>
                <input class="txt-month" type="hidden" name="maintainDate" value="" />
                <p class="row-btn">
                    <input type="button" class="btn btn-primary btn-easyFormSubmit" name="btnSubmit" value="提 交" />
                    <input type="button" class="btn btn-cancelEdit" name="btnCancel" value="取 消" />
                </p>
            </div>
            [/@shiro.hasPermission]
        </form>
    </div>
</div>



</body>

[#include "/WEB-INF/views/common/include_js.ftl"]

<script type="text/javascript">
    requirejs(['pub'],function () {

         $('#month').setDate('${sysdate}','month');
         $('.txt-month').setDate('${sysdate}','month');


        var om = {
            tabIndex : 0,
            nowMonth : $('#month').val(),
            tabMonth : ['','',''],
            defPubTxt : '发布时间：&#10;发布类型：&#10;',
            defSummaryTxt : '暂无总结~',
            tabCE0 : function (){//tab0 发布情况
                var me = this;
                // window.console&&console.log('tab0 '+me.nowMonth);
                $ajax.post('${base}/ui/sys/sysOperation/getSysOperationByMaintainDate',{maintainDate:me.nowMonth}).done(function (rst){
                    if(rst.code=='200'){
                        if(rst.data){
                            if(rst.data.publishContent==''){rst.data.publishContent = me.defPubTxt;}
                            $('.pre-write-a').html(rst.data.publishContent);
                            $('.txt-id').val(rst.data.id);
                            $('.txta-ra').val($('.pre-write-a').html());
                        }else{
                            $('.pre-write-a').html(me.defPubTxt);
                            $('.txt-id').val('');
                            $('.txta-ra').val($('.pre-write-a').html());
                        }
                    }
                });
            },
            tabCE1 : function (){//tab1 线上问题
                var me = this;
                // window.console&&console.log('tab1 '+me.nowMonth);
                $ajax.post('${base}/ui/sys/sysOperation/countOnlineProblem',{maintainDate:me.nowMonth}).done(function (rst){
                    if(rst.code=='200'){
                      if(rst.data){
                          $.each(rst.data,function (k,v){
                              if(k.indexOf('unDeal')>-1){
                                if(v){
                                    $('.'+k).html('('+v+')');
                                }else{
                                    $('.'+k).html('');
                                }
                              }else{
                                  $('.'+k).html(v);
                              }

                          });
                      }else{
                        $('.s-num').html('0');
                        $('.b-spNum').html('');
                      }

                    }
                });
                $('#gridBox').datagrid('load',{maintainDate:me.nowMonth});
            },
            tabCE2 : function (){//tab2 运营情况总结
                var me = this;
                // window.console&&console.log('tab2 '+me.nowMonth);
                $ajax.post('${base}/ui/sys/sysOperation/getSysOperationByMaintainDate',{maintainDate:me.nowMonth}).done(function (rst){
                    if(rst.code=='200'){
                        if(rst.data){
                            if(rst.data.operationContent==''){rst.data.operationContent = me.defSummaryTxt;}
                            $('.pre-write-b').html(rst.data.operationContent);
                            $('.txt-id').val(rst.data.id);
                            $('.txta-rb').val(rst.data.operationContent==me.defSummaryTxt?'':$('.pre-write-b').html());
                        }else{
                            $('.pre-write-b').html(me.defSummaryTxt);
                            $('.txt-id').val('');
                            $('.txta-rb').val('');
                        }
                    }
                });
            },
            reH1 : function (){//更新标题h1
                var me = this;
                var ym = me.nowMonth.split('-');
                $('.s-year').html(ym[0]);
                $('.s-month').html(ym[1]);
            },
            tabChange : function (ix){//切换tab事件
                var me = this;
                me.reH1();
                me.tabMonth[ix] = me.nowMonth;
                me['tabCE'+ix]();
            },
            initGrid : function (){//初始化grid
                $grid.newGrid("#gridBox",{
                    checkOnSelect : false,
                    selectOnCheck : false,
                    singleSelect : false,
                    ctrlSelect : true,
                    pagination : true,
                    fitColumns : false,
                    columns:[[
                        {title:'id',field:'id',width:80,hidden:true},
                        {title:'医院',field:'hospName',width:160,align:'left'},
                        {title:'状态',field:'state',width:60,formatter: function(val,row,index){
                                return ['<span class="red">待确认</span>', '<span class="blue">处理中</span>', '<span class="green">待发布</span>', '<span class="green">已完成</span>', '<span>已关闭</span>'][val];
                            }
                        },
                        {title:'问题级别',field:'problemLevelName',width:80},
                        {title:'标题',field:'title',width:220,align:'left'},
                        {title:'提报人',field:'declarerName',width:80},
                        {title:'问题类型',field:'typeName',width:90},
                        {title:'模块',field:'moduleName',width:120},
                        {title:'填报时间',field:'createDate',width:140},
                        {title:'业务负责人',field:'personLiableName',width:80},
                        {title:'开发负责人',field:'resolverName',width:80},
                        // {title:'问题解决实际时间',field:'solTime',width:140},
                      //  {title:'备注',field:'groupContent',width:120},
                        {title:'处理时长(天)',field:'duringTime',width:90,formatter :function (v,row,index) {
                                var back = '';
                                if(row.resolveDate){
                                    var resolveDate = row.resolveDate.replace(/-/g,"/");
                                    var createDate = row.createDate.replace(/-/g,"/");
                                    var useTime = new Date(resolveDate) - new Date(createDate);
                                    var days = (useTime / 86400000).toFixed(0);
                                    // var hours = ((useTime % 86400000) / 3600000).toFixed(0);
                                    // var minute = (((useTime % 86400000) % 3600000) / 60000).toFixed(0);
                                    if (days > 0) {
                                        back = back + days + "天";
                                    } else {
                                        back = "1天";
                                    }
                                }
                                else{
                                    back = "--";
                                }
                                return back;
                            }},
                        {title: '备注', field: 'groupContent', width: 180, align: 'left', titletip: true}
                    ]],
                    onBeforeLoad : function (opt){
                        if(!opt.maintainDate){
                            return false;
                        }
                    },
                    onLoadSuccess : function (data) {
                    },
                    url:'${base}/ui/sys/sysOperation/getForProblemList',
                    //   height: 370,
                    //   fitHeight : false,
                    offset :-158
                });

            },
            init : function (){//初始化事件
                var me = this;

                me.tabChange(0);//初始化tab0
                me.initGrid(0);//初始化grid

                $('.btn-search').click(function (){//搜索事件
                    var month = $('#month').val();
                    me.nowMonth = month;
                    $('.txt-month').val(month);
                    me.tabChange(me.tabIndex);
                });

                $('.td-num').click(function (){//grid 点击数字事件
                    var type = $(this).attr('rel').split(':');
                    var data = {
                        maintainDate:me.nowMonth
                    };
                    type.length>1&&(data[type[0]] = type[1]);
                    // window.console && console.log(data);
                    $('#gridBox').datagrid('reload',data);
                });

                $('.s-writeEdit').click(function(){//编辑事件
                    var type = $(this).attr('rel');
                    var $p = $(this).parents('form');
                    $p.find('.writeArea').hide();
                    $p.find('.writeEditWrap').show();
                    var txt = $p.find('.pre-write').html();
                    if(type=='b'&&txt==om.defSummaryTxt){//如果是写总结，并且数据为空
                        txt = '';
                    };
                    $p.find('.txta-r').val(txt).focus();
                });

                $('.btn-cancelEdit').click(function (){//取消编辑
                    var $p = $(this).parents('form');
                    $p.find('.writeArea').show();
                    $p.find('.writeEditWrap').hide();
                });
            }
        }

        om.init();//初始化

        var $tabLi = $('.likeTabs li');//tab切换事件

        $tabLi.click(function () {
            var ix = $tabLi.index(this);
            om.tabIndex = $(this).attr('rel');
            $tabLi.removeClass('tabs-selected');
            $(this).addClass('tabs-selected');
            $('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');
            if (om.tabMonth[ix] != om.nowMonth){//变化时执行
                om.tabChange(ix);
            }
            return false;
        });

        window.afterSubmit = function (rst,opt){//表单提交后处理事件
            $form = $(opt.form);
            $form.find('.writeArea').show();
            $form.find('.writeEditWrap').hide();
            $form.find('.txt-id').val(rst.data.id);
            $form.find('.pre-write').html(opt.form==='.form-a'?rst.data.publishContent:rst.data.operationContent);
        }


    });

</script>
</body>

</html>
