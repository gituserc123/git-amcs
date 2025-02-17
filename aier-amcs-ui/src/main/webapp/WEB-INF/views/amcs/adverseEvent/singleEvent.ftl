<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
  <style>
      .likeTabs {
          width: auto;
      }

      .h2-title-a {
          color: #333333;
      }

      .fix-save-btn{width: 30px;
          padding: 12px 6px;
          white-space: inherit;
          border-bottom-right-radius: 0;
          border-bottom-left-radius: 0;
          border-top-right-radius: 0;}
      .fix-stash-btn{width: 30px;
          padding: 12px 6px;
          white-space: inherit;
          border-top-left-radius: 0;
          border-top-right-radius: 0;
          border-bottom-right-radius: 0;}
  </style>
</head>
<body>
<div id="op-div" style="position: fixed;
    z-index: 1;
    top: 50%;
    right: 0;
    display: flex;
    flex-direction: column;">
  <button type="button" class="btn btn-large btn-primary fix-save-btn" >保存</button>
  <button type="button" class="btn btn-large btn-warning fix-stash-btn" >暂存</button>
</div>
<ul class="tabs likeTabs">
  <li class="tabs-first tabs-selected" rel="0">
    <a href="#" class="tabs-inner"><span class="tabs-title">事件明细</span></a>
  </li>
  <li rel="1">
    <a href="#" class="tabs-inner"><span class="tabs-title">流程状态</span></a>
  </li>
  <li rel="2">
    <a href="#" class="tabs-inner"><span class="tabs-title">多次上报</span></a>
  </li>
</ul>
<div class="tabCont tabCont-0">
  <form class="soform main-form form-enter pad-10 solab-l">
    <h2 class="h2-title-a">
      <span class="s-title">视力与眼压</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">

    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">医院名称：</label>
          <input class="txt txt-validate" type="text" name="view" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">患者本次事件第：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">事件名称：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">亚专科：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">发生日期：</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">日期类型：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">事件分类1级：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">发生地点：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">发生时段：</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">事件分类Ⅱ级：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">发现者：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">是否构成纠纷：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>

    <h2 class="h2-title-a">
      <span class="s-title">处理结果</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">事件处理进展：</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">赔偿金额：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">减免金额：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="p12">
          <span class="fl">
              <label class="lab-inline bold w-80">事件处理方式</label>
          </span>
        <span class="fl">
              <label class="lab-val lab-inline">
                  <input type="checkbox" class="chk checkbox-outRecepi" name="strabismus"/>解释
              </label>
              <label class="lab-val lab-inline">
                  <input type="checkbox" class="chk checkbox-outRecepi" name="presbyopia"/>赔偿
              </label>
              <label class="lab-val lab-inline">
                  <input type="checkbox" class="chk checkbox-outRecepi" name="nystagmus"/>减免
              </label>
              <label class="lab-val lab-inline">
                  <input type="checkbox" class="chk checkbox-outRecepi" name="nystagmus"/>其它
              </label>
          </span>
      </div>
    </div>


    <h2 class="h2-title-a">
      <span class="s-title">患者信息</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">患者姓名：</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">病案号：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">性别：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">年龄：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">出生日期：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>


    <h2 class="h2-title-a">
      <span class="s-title">事件经过和疾病诊断</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">入院时间：</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">门诊诊断：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">住院诊断：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">入院视力VOD：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">入院视力VOS：</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">入院眼压OD：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">入院眼压OS：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">出院时间</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">不良事件发生阶段</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">治疗事件：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p12">
        <div class="item-one">
          <label class="lab-item">拟施治疗</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p12">
        <div class="item-one">
          <label class="lab-item">实施治疗</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">出院视力VOD：</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">出院眼压VOS：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">出院眼压OD：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">出院眼压OS</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">当前视力VOD：</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">当前眼压VOS：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">当前眼压OD：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">当前眼压OS</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p12">
        <div class="item-one">
          <label class="lab-item">目前情况</label>
          <textarea name="avisSolution" class="txta txt-validate validatebox-text" style="resize: none;" cols="10"
                    rows="10" style="width: 100%"> </textarea>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p12">
        <div class="item-one">
          <label class="lab-item">患者及家属诉求</label>
          <textarea name="avisSolution" class="txta txt-validate validatebox-text" style="resize: none;" cols="10"
                    rows="10" style="width: 100%"> </textarea>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p12">
        <div class="item-one">
          <label class="lab-item">事件经过描述</label>
          <textarea name="avisSolution" class="txta txt-validate validatebox-text" style="resize: none;" cols="10"
                    rows="10" style="width: 100%"> </textarea>
        </div>
      </div>
    </div>

    <h2 class="h2-title-a">
      <span class="s-title">当事人员</span>
    </h2>
    <hr class="mar-l10 mar-r10 mar-t0 mar-b20" style="border-color:#b2def4">
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">姓名：</label>
          <input class="txt txt-validate" type="text" name="userName" placeholder=""
          />
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">性别：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">年龄：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">专业技术职称：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">学历：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">类别：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
      <div class="p3">
        <div class="item-one">
          <label class="lab-item">在职情况：</label>
          <input id="nameChinese" class="txt txt-validate" type="text" name="name"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p12">
        <div class="item-one">
          <label class="lab-item">原因分析</label>
          <textarea name="avisSolution" class="txta txt-validate validatebox-text" style="resize: none;" cols="10"
                    rows="10" style="width: 100%"> </textarea>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p12">
        <div class="item-one">
          <label class="lab-item">科室整改意见及结果</label>
          <textarea name="avisSolution" class="txta txt-validate validatebox-text" style="resize: none;" cols="10"
                    rows="10" style="width: 100%"> </textarea>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="p12">
        <div class="item-one">
          <label class="lab-item">质量委员会整改意见</label>
          <textarea name="avisSolution" class="txta txt-validate validatebox-text" style="resize: none;" cols="10"
                    rows="10" style="width: 100%"> </textarea>
        </div>
      </div>
    </div>
  </form>
</div>
<div class="tabCont tabCont-1 tabContHide">
  <div id="myDiagramDiv"
       style="width: 100%; height: 400px; position: relative; -webkit-tap-highlight-color: rgba(255, 255, 255, 0); cursor: auto;">
    <canvas tabindex="0" width="398" height="398"
            style="position: absolute; top: 0px; left: 0px; z-index: 2; user-select: none; width: 398px; height: 398px; cursor: auto;">
      This text is displayed if your browser does not support the Canvas HTML element.
    </canvas>
  </div>
  <div>
    <button type="button" class="btn-gojs">载入数据</button>
    <button type="button" class="btn-gojs-test">获取数据</button>
  </div>
</div>
<div class="tabCont tabCont-2 tabContHide">
  <div class="cont-grid cont-table-goods">
    <div id="table_up"></div>
  </div>
</div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(["easygridEdit", 'gojs', 'pub'], function ($e) {
      // 针对病历外的 复制和拷贝
      $(window.document.body).bind({
//            copy: function (e) {//copy事件
//                var s = window.getSelection();
//                var clipboardData = window.clipboardData; //for IE
//                if (!clipboardData) { // for chrome
//                    clipboardData = e.originalEvent.clipboardData;
//                }
//                // 加密
//                s = escape(s);
//                clipboardData.setData('Text', "不允许拷贝到外部<<<<<" + s + "<<<<<" + patientId);
//                return false;
//            },
        paste: function (e) {
          var clipboardData = window.clipboardData; // IE
          if (!clipboardData) { //chrome
            clipboardData =  e.originalEvent.clipboardData
          }
          var copyStr = clipboardData.getData('Text');

          if(copyStr.indexOf('<<<<<')>-1){//如果拷贝的是病历内容
            var strArr = copyStr.split("<<<<<");
            var copyPid = strArr[2];
            var str = unescape(strArr[1]);
            str = str.replace(/[\[\]\{\}]/g,"");
            // console.log(patientId)
            if(patientId == copyPid){//只可以拷贝患者自己的病历内容
              if(str){//有内容才粘贴
                $(e.target).val(str);
              }
            }else{
              $pop.msg('非本人病历内容，不允许拷贝！');
            }
            return false;
          }

        }
      });


        /*
        保存按钮事件
         */
        $(".fix-save-btn").click(function(){
         console.log('save');
        })

        /*
        暂存按钮事件
         */
        $(".fix-stash-btn").click(function(){
            console.log('stash');
        })

        var tabIndex = 0;
        var tabInit = ['', ''];
        var tabInitE = [function one() {
            tabInit[0] = true;
            console.log('111');
        }, function two() {
            window.curflowchartid = "";
            window.existflowchartid = "";
            if(!window.flowchartid ){//第一次，或者切换id后，需要重新请求流程状态数据并渲染
                window.flowchartid = '123'
                init();
            }else if(window.curflowchartid!== window.existflowchartid){
                window.existflowchartid = window.curflowchartid;
                loadFlowStatus(window.existflowchartid);
            }
            console.log('222');
            // tabInit[1] = true;
            $(".btn-gojs-test").click(function(){
                console.log(window.myDiagram.model.toJson());
            });
            $(".btn-gojs").click(function(){
                loadFlowStatus();
            })

            function init() {
                function linkColorConverter(linkdata, elt) {
                    var link = elt.part;
                    if (!link) return blue;
                    var f = link.fromNode;
                    if (!f || !f.data || !f.data.status) return "blue";
                    // var t = link.toNode;
                    // if (!t || !t.data || !t.data.status) return "blue";
                    return "pink";  // when both Link.fromNode.data.critical and Link.toNode.data.critical
                }

                var $_ = go.GraphObject.make; // for conciseness in defining templates
                myDiagram = $_(go.Diagram, "myDiagramDiv", // create a Diagram for the DIV HTML element
                    {
                        "undoManager.isEnabled": true ,// enable undo & redo
                        layout:$_(go.TreeLayout, // specify a Diagram.layout that arranges trees
                            { angle: 0, layerSpacing: 135 })
                    });
                // define a simple Node template
                myDiagram.nodeTemplate =
                    $_(go.Node, "Auto", // the Shape will go around the TextBlock
                        $_(go.Shape, "RoundedRectangle", { strokeWidth: 0, fill: "white" },
                            // Shape.fill is bound to Node.data.color
                            new go.Binding("fill", "color")),
                        $_(go.TextBlock, { margin: 8, font: "bold 14px sans-serif", stroke: '#333' }, // Specify a margin to add some room around the text
                            // TextBlock.text is bound to Node.data.key
                            new go.Binding("text", "key"))
                    );

                myDiagram.linkTemplate =
                    $_(go.Link,
                        { toShortLength: 6, toEndSegmentLength: 20 },
                        $_(go.Shape,
                            { strokeWidth: 4 },
                            new go.Binding("stroke", "", linkColorConverter)),
                        $_(go.Shape,  // arrowhead
                            { toArrow: "Triangle", stroke: null, scale: 1.5 },
                            new go.Binding("fill", "", linkColorConverter))
                    );
                // but use the default Link template, by not setting Diagram.linkTemplate
                // create the model data that will be represented by Nodes and Links
                myDiagram.model = new go.GraphLinksModel(
                    [
                        { key: "Alpha", color: "lightblue" ,status:true},
                        { key: "Beta", color: "orange" ,status:false},
                        { key: "Gamma", color: "lightgreen",status:true },
                        { key: "Delta", color: "pink" }
                    ],
                    [
                        { from: "Alpha", to: "Beta" },
                        { from: "Beta", to: "Gamma" },
                        { from: "Gamma", to: "Delta" },
                    ]);
            };
        }, function three() {
            tabInit[2] = true;
            console.log('333');
            $grid.newGrid("#table_up", {
                selectOnCheck: false,
                checkOnSelect: false,
                rownumbers: false,
                pagination: false,
                // height: 220,
                columns: [[
                    {
                        title: '操作',
                        field: 'op',
                        width: 30,
                        formatter: function (v, row, index) {
                            return '<span class="s-op s-op-edit icon-eye_open pad-r15 pad-l10" title="查看" rel="' + index + '"></span>';
                        }
                    },
                    {
                        field: "group", title: "", hidden: false, width: 50, formatter: function (v, row, index) {
                            // if(COMBO_PRICE_LEN < 2){
                            //     return '<span class="s-rowGroup-single"></span>'
                            // }else{
                            //     return '<span class="s-rowGroup"></span>'
                            // }
                            return '<span class="s-rowGroup"></span>'
                        }
                    },
                    {field: "view", title: "省区", width: 60},
                    {field: "retailPrice", title: "医院", width: 60, align: "center"},
                    {field: "1", title: "事件名称", width: 60, align: "center"},
                    {field: "2", title: "上报人", width: 60, align: "center"},
                    {field: "3", title: "上报日期", width: 60, align: "center"},
                    {field: "4", title: "发生日期", width: 60, align: "center"},
                    {field: "5", title: "退回日期", width: 60, align: "center"},
                    {field: "6", title: "间隔天数", width: 60, align: "center"},
                    {field: "7", title: "退回节点", width: 60, align: "center"},
                    {field: "8", title: "审核状态", width: 60, align: "center"},
                    {field: "9", title: "状态", width: 60, align: "center"},
                ]],
                onLoadSuccess: function () {
                    $(".cont-grid").on("click", ".icon-eye_open", function(e) {
                        e.stopPropagation();
                        var rowData = $("#table_up").datagrid("getRows")[$(this).attr("rel")];
                        //切换到事件明细页签，并加载数据
                        goDetailTab(rowData);
                    });

                    $e.mergeRowsCells({
                        grid: '#table_up',//grid
                        strArr: [''],//需要合并的字段，为数组
                        data: $("#table_up").datagrid("getRows").filter(function (v) {
                            return !!v.group;
                        }),
                        callback: function () {
                            console.log('合并了。')
                        },//返回事件
                        groupIds: 'group',
                        hideCheckbox: true,
                        needMsg: true
                    });
                },
            });

            $("#table_up").datagrid('loadData', [{"view": '湖南省'}, {"view": '湖北省', group: true}, {
                "view": '江西省',
                group: true
            }, {"view": '河北省',group: true}, {"view": '河北'}])
        }];

        window.$tabLi = $('.likeTabs li');
        $tabLi.click(function () {
            var ix = $tabLi.index(this);
            if(ix!== 0){
                $("#op-div").hide();
            }else {
                $("#op-div").show();
            }
            if(!$(this).hasClass('tabs-selected')){
                tabIndex = $(this).attr('rel');
                $tabLi.removeClass('tabs-selected');
                $(this).addClass('tabs-selected');
                $('.tabCont').addClass('tabContHide').eq(ix).removeClass('tabContHide');
                if (!tabInit[ix]) {//初始化
                    tabInitE[ix]();
                }
            }
            return false;
        });

        /**
         * 重新加载 流程状态数据
         */
        function loadFlowStatus(){
            $ajax.post("https://www.fastmock.site/mock/96e9e3d91ad2035e4e8758666068f4da/map/gojs").done(function(res){
                var data = {
                    class:"GraphLinksModel",
                    nodeDataArray:res.lists
                }
                var linkData = [],tmplinko={};
                for (var i =0,len = res.lists.length;i<len;i++){
                    var tmp = res.lists[i];
                    tmp.color = "lightblue";
                    if(!tmplinko.from){
                        tmplinko.from = tmp.key;
                        continue;
                    }
                    if(!tmplinko.to){
                        tmplinko.to = tmp.key;
                        linkData.push(JSON.parse(JSON.stringify(tmplinko)))
                        tmplinko = {};
                        if(i == len -1){

                        }else{
                            tmplinko.from = tmp.key;
                        }
                    }
                }
                data.linkDataArray = linkData;
                if(window.myDiagram){
                    window.myDiagram.model = go.Model.fromJson(data);
                }
            })
        }

        /**
         * 进入事件明细页面并重载数据
         * @param data
         */
        function goDetailTab(data){
            console.log(data)
            window.$tabLi.first().click();
            $(".main-form").form('load',data)
        }
    });
</script>
</html>