define(["easygridEdit"],function ($e) {

    var obj = {
        init:function(paramsObj){
            this.paramsObj = paramsObj;
            this.initDom();
            this.initDataGrid();
        },
        initDom : function () {
            var dom = "<div class='tab3tableup'>" +
                "<div id='table_up'></div>" +
                "</div>";
            $(".tab-report").append(dom);
        },
        initDataGrid:function(){
            var $this = this;
            $grid.newGrid("#table_up", {
                url:$this.paramsObj.url || null,
                queryParams:$this.paramsObj.urlParams || null,
                selectOnCheck: false,
                checkOnSelect: false,
                rownumbers: false,
                remoteSort:false,
                sortName:'createDate',
                pagination: false,
                // height: 220,
                columns: [[
                    {
                        title: '操作',
                        field: 'op',
                        width: 30,
                        formatter: function (v, row, index) {
                            let opStr = '';
                            opStr = '<span class="s-op s-op-edit icon-eye_open pad-r15 pad-l10" title="查看" rel="' + index + '"></span>';
                            opStr = opStr + '<span class="s-op s-op-fanruan icon-printer" title="报表" rel="' + index + '"></span>';
                            return opStr;
                        }
                    },
                    {
                        field: "group", title: "", hidden: false, width: 20, formatter: function (v, row, index) {
                            return '<span class="s-rowGroup"></span>'
                        }
                    },
                    {field: "node", title: "node", hidden:true},
                    {field: "eventCode", title: "eventCode", hidden:true},
                    {field: "id", title: "事件ID", width: 60},
                    {field: "eventName", title: "事件名称", width: 120, align: "center"},
                    {field: "reportTimes", title: "上报次数", width: 60, align: "center"},
                    {field: "isPrimary", title: "是否为主事件", width: 60, align: "center"},
                    {field: "creatorName", title: "上报人", width: 60, align: "center"},
                    {field: "createDate", title: "上报日期", width: 60, align: "center"},
                    {field: "eventDate", title: "发生日期", width: 60, align: "center"},
                    {field: "masterId", title: "主事件ID", width: 60},
                    {field: "prevId", title: "前置事件ID", width: 60},

                ]],
                onLoadSuccess: function (data) {
                    $(".tab3tableup").on("click", ".icon-eye_open", function(e) {
						let pageType = $this.paramsObj.urlParams.pageType;
						let isReview = $this.paramsObj.urlParams.isReview;
						//let showOperate = $this.paramsObj.urlParams.showOperate;
                        e.stopPropagation();
                        let basicId = $("#table_up").datagrid("getRows")[$(this).attr("rel")].id;
                        let eventCode = $("#table_up").datagrid("getRows")[$(this).attr("rel")].eventCode;
                        let node = $("#table_up").datagrid("getRows")[$(this).attr("rel")].node;
                        //如果节点大于1则showOperate为false,否则为true
                        let showOperate = true;
                        if(node>1){
                            showOperate = false;
                        }
                        let url = base+'/ui/service/biz/amcs/adverse/common/indexPage?eventCode='+eventCode;
                        url += '&id='+basicId+'&page_type='+pageType + '&is_review=' + isReview ;
                        url += '&showOperate=' + showOperate;
                        location.href = url;
					    
                    });
                    $e.initMergeRows({
                        grid : '#table_up',//grid
                        groupIds : 'masterId',//成组的标识
                        // data : {},//如果有data，则直接使用data
                        hideCheckbox : true,
                        groupKeys : null,//成组key
                        strArr : null,//需要合并的字段，为数组
                        callback : null//返回事件
                    });
                    $('.s-op-fanruan').click(function () {
                        var ix = $(this).attr('rel');
                        var row = data.rows[ix];
                        if(row.id != ''){
                            $pop.newTabWindow(row.eventName, base + '/fr?url='+row.reportType+'.cpt&common&op=view&basicid=' + row.id + '&prev_id=' + row.prevId);
                        }
                    });
                },
            });
        },
        loadData:function(){
            var $this = this;
            if($this.paramsObj.url){
                $("#table_up").datagrid("reload");
            }
        }
    };

    return obj;
});

