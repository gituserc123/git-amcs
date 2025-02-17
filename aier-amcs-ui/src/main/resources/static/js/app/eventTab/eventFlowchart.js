define(["vue"], function (Vue) {

    function initVueComponent(paramObj) {

        new Vue({
            el: "#tanglv",
            data() {
                return {
                    adviceTxt: "",
                    paramObj: paramObj || {},
                    list: []
                }
            },
            created() {
                console.log('i am in created.');
                this.query();
            },
            methods: {
                query() {
                    $ajax.post(`${base}/ui/service/biz/amcs/adverse/common/findOpinionList`, this.paramObj).done((res) => {
                        this.list = res.rows;
                    })
                }
            },
            template:`
                <div class="leader-con">
                    <div class="leader-bar-title">
                    </div>
                    <div class="leader-bar-title ">审核记录</div>
                    <div class="leader-advice-con">
                        <div class="leader-item-con" v-for="item in list">
                            <div class="leader-item-left">
                                <span class="leader-item-left-txt">{{ item.modifyName }}</span>
                                <span class="leader-item-info-txt">{{ item.nodeName }}</span>
                            </div>
                            <div class="leader-item-right">
                                <span class="leader-item-right-txt">{{ item.opinion }}</span>
                                <span class="leader-item-info-txt">{{
                                    item.modifyName
                                }}：{{ item.modifyDate }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            `
        })
    }


    var obj = {
        init: function (paramsObj) {
            this.paramsObj = paramsObj;
            this.initDom();
            // this.initChart();
            this.loadData(this.initChart);
            initVueComponent(this.paramsObj.leaderParams);
        },
        initDom: function () {
            var domhtml = "" +
                '<div id="myDiagramDiv" ' +
                'style="width: 100%; height: 400px; position: relative; -webkit-tap-highlight-color: rgba(255, 255, 255, 0); cursor: auto;">' +
                '<canvas tabIndex="0" width="398" height="398" ' +
                'style="position: absolute; top: 0px; left: 0px; z-index: 2; user-select: none; width: 398px; height: 398px; cursor: auto;">' +
                'This text is displayed if your browser does not support the Canvas HTML element.' +
                '</canvas>' +
                '</div>' +
                "";
            $(".tab-flow").append(domhtml);
            $(".tab-flow").append("<div id='tanglv'></div>");

        },
        initChart: function (data) {
            function linkColorConverter(linkdata, elt) {
                var link = elt.part;
                if (!link) return '#00a4ff';
                var f = link.fromNode;
                if (!f || !f.data || !f.data.status) return "#00a4ff";
                // var t = link.toNode;
                // if (!t || !t.data || !t.data.status) return "blue";
                return "#00a4ff";  // when both Link.fromNode.data.critical and Link.toNode.data.critical
            }

            var $_ = go.GraphObject.make; // for conciseness in defining templates
            window.myDiagram = $_(go.Diagram, "myDiagramDiv", // create a Diagram for the DIV HTML element
                {
                    "undoManager.isEnabled": true,// enable undo & redo
                    "allowMove": false,
                    allowHorizontalScroll: false,
                    allowVerticalScroll: false,
                    contentAlignment: go.Spot.Center,
                    layout: $_(go.TreeLayout, // specify a Diagram.layout that arranges trees
                        { angle: 0, layerSpacing: 135 })
                });
            // define a simple Node template
            myDiagram.nodeTemplate =
                $_(go.Node, "Auto", // the Shape will go around the TextBlock
                    // $_(go.Shape, "RoundedRectangle", { strokeWidth: 0, fill: "white" },
                    $_(go.Shape, { figure: "Circle", isPanelMain: true, fill: "white", stroke: '#85c9e9' },
                        // Shape.fill is bound to Node.data.color
                        new go.Binding("fill", "color"),new go.Binding('stroke',"borderColor")),
                    $_(go.TextBlock, { margin: 8, font: "bold 18px sans-serif", stroke: '#305A78', width: 80, textAlign: "center" }, // Specify a margin to add some room around the text
                        // TextBlock.text is bound to Node.data.key
                        new go.Binding("text", "key"),new go.Binding("stroke","textColor"))
                );

            myDiagram.linkTemplate =
                $_(go.Link,
                    { toShortLength: 6, toEndSegmentLength: 20 },
                    $_(go.Shape,
                        { strokeWidth: 2, stroke: "#8d8d8d" }),
                    $_(go.Shape,  // arrowhead
                        { toArrow: "Triangle", fill: '#8d8d8d', stroke: "#8d8d8d", scale: 1.5 }),
                );
            myDiagram.model = new go.GraphLinksModel(data.nodeDataArray, data.linkDataArray);

        },
        loadData: function (callback) {
            console.log(this.paramsObj);
            $ajax.post(this.paramsObj.url, this.paramsObj.urlParams).done(function (res) {
                var data = {
                    class: "GraphLinksModel",
                    nodeDataArray: res.map(item => {
                        item.color = "#aae2ff";
                        if(item.currentNode === 1){
                            item.color = "#FBE7D4";//高亮的背景色
                            item.textColor = "#A55531";//高亮的文字颜色
                            item.borderColor = "#EAC6A3";//高亮的描边颜色
                        }
                        console.log(item);
                        return item;
                    })
                }
                /**
                 * auther:tanglv
                 * des:组装连线数据
                 * 连线数据的对象要求属性 {from:'',to:''}
                 */
                var linkData = [], tmplinko = {};
                for (var i = 0, len = res.length; i < len; i++) {
                    var tmp = res[i];
                    // tmp.color = "lightblue";
                    if (!tmplinko.from) {
                        tmplinko.from = tmp.key;
                        continue;
                    }
                    if (!tmplinko.to) {
                        tmplinko.to = tmp.key;
                        linkData.push(JSON.parse(JSON.stringify(tmplinko)))
                        tmplinko = {};
                        if (i == len - 1) {

                        } else {
                            tmplinko.from = tmp.key;
                        }
                    }
                }
                data.linkDataArray = linkData;
                if (window.myDiagram) {
                    window.myDiagram.model = go.Model.fromJson(data);
                    console.log('reload test');
                } else {
                    callback && callback(data);
                }
            })
        },
    };

    return obj;
});