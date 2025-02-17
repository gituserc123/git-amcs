<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>医院填报月度主表管理 - 爱尔医院</title>
    [#include '/WEB-INF/views/common/include_resources.ftl']
</head>
<body>
<div class="searchHead">
    <form id="sbox" class="soform form-enter">
        <input class="hide" type="text" name="statusStr" id="statusStr" value="1">

        <label class="lab-inline">省区：</label>
        <select class="drop drop-sl-v easyui-combobox  w-150" name="province" id="province"  data-options="valueField:'id',textField:'name',clearIcon:true">

        </select>

            <label class="lab-inline">医院：</label>
            <select class="drop drop-sl-v easyui-combobox  w-150" name="hospId" id="hospId"  data-options="valueField:'id',textField:'name',clearIcon:true">
            </select>


        <button type="button" class="btn btn-small btn-primary so-search"  data-opt="{grid:'#gridBox', scope:'#sbox'}">查 询</button>
[#--        <button type="button" class="btn btn-small btn-primary s-export">导 出</button>--]
        <!-- <button type="button" class="btn btn-small btn-primary s-export-detail">导出明细</button> -->
    </form>
</div>
<div class="cont-grid">
    <div id="gridBox"></div>
</div>
</body>
[#include '/WEB-INF/views/common/include_js.ftl']
<script id="formTem" type="text/html">
    <form id="infoForm" class="soform form-validate pad-t20 pad-r20 form-enter"
          data-opt="{beforeCallback:'submitEditForm'}" autocomplete="off">
        <input class="hide" type="text" name="associatedId" id="associatedId">
        <input class="hide" type="text" name="action" id="associatedId" value="9">
        <input class="hide" type="text" name="currentStatus" id="currentStatus" value="9">
        <input class="hide" type="text" name="prevStatus" id="prevStatus" value="5">
        <input class="hide" type="text" name="hospId" id="hospId">
        <div class="row">
            <div class="p12">
                <div class="item-one">
                    <label class="lab-item">沟通内容</label>
                    <textarea class="txta txt-validate required adaptiveTextarea" type="text" id="content"
                              name="content" data-options="required:true" validType="maxlength[400]" placeholder=""></textarea>
                </div>
            </div>
        </div>
        <p class="row-btn center">
            <input type="button" class="btn btn-primary btn-easyFormSubmit" lay-submit name="btnSubmit" value="确定"/>
            <input type="button" class="btn btn-cancel" name="btnCancel" value="取 消"/>
        </p>
    </form>
</script>
<script type="text/javascript">
    function reloadEventData() {

        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
            $('#province').combobox('loadData', rst);
        });
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp?insiId=100001',null,false,false).done(function (rst) {
            $('#hospId').combobox('loadData', rst);
        });
        $('#province').combobox({
            onChange: function(v){
                let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;
                $('#hospId').combobox('reload', url);
            }
        });




    };
    requirejs(["pub"], function () {
        this.reloadEventData();

        var formPop = null;

        $grid.newGrid("#gridBox", {
            tools: [
                [
                [#--    {--]
                [#--    iconCls: "plus_sign",--]
                [#--    text: "初始化月报",--]
                [#--    title: "初始化月报",--]
                [#--    click: function () {--]
                [#--        $ajax.post("${base}/ui/service/biz/amcs/fin/finMonthMain/new",{hospId:'${hospId}'},"是否初始化当月数据，注意，将按目前的统筹区配置初始化月报，初始化后不能撤回，请确认已经完成医院医保统筹区配置！",false).then(res => {--]
                [#--            $('#gridBox').datagrid('reload');--]
                [#--        });--]
                [#--    }--]
                [#--}--]
                ]
            ],
            queryParams:{
                statusStr:'1'
            },
            checkOnSelect: false,
            selectOnCheck: false,
            singleSelect: false,
            ctrlSelect: true,
            fitColumns: false,
            pagination:false,
            columns: [
                [

                    {
                        title: "操作",
                        field: "op",
                        width: 90,
                        formatter: function (v, row, index) {
                            htmlStr=""
                            // if(row.status==1||row.status==9){
                            //     htmlStr+="&nbsp;&nbsp;"+'<span class="s-op s-op-submit " title="提交审核" rel="' + index + '"><svg t="1680750808182" style="top:2px;position: relative" viewBox="128 128 768 768" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4767" width="14" height="14"><path d="M322.142929 293.741129H701.345327c27.122439 0 49.127436-22.004998 49.127437-49.127436s-22.004998-49.127436-49.127437-49.127436H322.142929c-27.122439 0-49.127436 22.004998-49.127437 49.127436s22.004998 49.127436 49.127437 49.127436zM322.142929 480.527736H701.345327c27.122439 0 49.127436-22.004998 49.127437-49.127436s-22.004998-49.127436-49.127437-49.127436H322.142929c-27.122439 0-49.127436 22.004998-49.127437 49.127436s22.004998 49.127436 49.127437 49.127436z" fill="#1296db" p-id="4768"></path><path d="M876.873563 0H147.126437C86.228886 0 36.589705 49.63918 36.589705 110.536732v802.414792c0 60.897551 49.63918 110.536732 110.536732 110.536732h157.105447c28.657671 0 52.197901-23.028486 52.197901-52.197901 0-28.657671-23.028486-52.197901-52.197901-52.197901H147.126437c-3.582209 0-6.652674-3.070465-6.652674-6.652674V110.536732c0-3.582209 3.070465-6.652674 6.652674-6.652674h729.747126c3.582209 0 6.652674 3.070465 6.652674 6.652674v802.414792c0 3.582209-3.070465 6.652674-6.652674 6.652674h-162.222888c-28.657671 0-52.197901 23.028486-52.197901 52.197901 0 28.657671 23.028486 52.197901 52.197901 52.197901h162.222888c60.897551 0 110.536732-49.63918 110.536732-110.536732V110.536732c0-60.897551-49.63918-110.536732-110.536732-110.536732z" fill="#1296db" p-id="4769"></path><path d="M671.664168 710.812594l-124.865567-124.865568c-19.446277-19.446277-50.662669-19.446277-69.597202 0l-124.865567 124.865568c-19.446277 19.446277-19.446277 50.662669 0 69.597201 19.446277 19.446277 50.662669 19.446277 69.597201 0l40.427787-40.427786v231.308346c0 27.122439 22.004998 49.127436 49.127436 49.127436s49.127436-22.004998 49.127436-49.127436v-231.308346l40.427786 40.427786c9.723138 9.723138 22.516742 14.328836 34.798601 14.328836 12.793603 0 25.075462-4.605697 34.798601-14.328836 20.469765-19.446277 20.469765-50.662669 1.023488-69.597201z" fill="#1296db" p-id="4770"></path></svg></span>'
                            // }
                            [@shiro.hasPermission name = "groupMonth:delete"]
                                htmlStr+='<span class="s-op s-op-delete icon-del" title="删除" rel="' + index + '"></span>'
                            [/@shiro.hasPermission]

                            return htmlStr
                        }
                    },
                    {title: "医院名称", field: "hospName", hidden: false, width: 300},
                    {title: "年度", field: "year", hidden: false, width: 100,formatter(v,row,index){
                            return row.createDate.substring(0, 4)
                        }},
                    {title: "月度", field: "month", hidden: false, width: 100,formatter(v,row,index){
                            return row.createDate.substring(5, 7)
                        }},
                    {title: "ID", field: "id", hidden: true, width: 100},
                    // {title: "创建者ID", field: "creator", hidden: false, width: 100},
                    // {title: "修改者ID", field: "modifer", hidden: false, width: 100},
                    // {title: "修改时间", field: "modifyDate", hidden: false, width: 100},
                    // {title: "医院ID", field: "hospId", hidden: false, width: 100},

                    {title: "状态", field: "status", hidden: false, width: 150,formatter(v,row,index){
                            if(v==1){
                                return "上报中"
                            }else if(v==5){
                                return "省区审核"
                            }else if(v==6){
                                return "省区审核完成"
                            }else{
                                return "退回"
                            }
                        }},
                    {title: "创建者", field: "creatorName", hidden: false, width: 100},
                    {title: "创建时间", field: "createDate", hidden: false, width: 150},

                ]
            ],
            onLoadSuccess: function (data) {

                $('.s-op-delete').click(function () {
                    var ix = $(this).attr('rel');
                    var row = data.rows[ix];

                    $ajax.post("${base}/ui/service/biz/amcs/fin/finMonthMain/deleteByMonthId",{monthId:row.id},"是否删除整个月记录？",false).then(data=>{
                        $grid.load('#gridBox',{statusStr:"1"});
                    })
                });
            },
            url: "${base}/ui/service/biz/amcs/fin/finMonthMain/getList",
        });
        window.submitEditForm = function (opt, $form, data) {
            //window.console&&console.log(opt,$form,data);
            $ajax.post({
                url: '${base}/ui/service/biz/amcs/fin/finMonthMain/returnToHos',
                data: data,
                tip: '你确定提交吗？',
                success: function (rst) {
                    $pop.close(formPop);
                    $('#gridBox').datagrid('reload');
                }
            });
            return false;
        }


    });
</script>

</html>
