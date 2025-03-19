define(["vue"], function (Vue) {

    function initVueComponent(paramObj,opinionUrl) {
        new Vue({
            el: "#tanglv",
            data() {
                return {
                    adviceTxt: "",
                    paramObj: paramObj || {},
                    opinionUrl: opinionUrl || "",
                    list: []
                }
            },
            created() {
                this.query();
            },
            methods: {
                query() {
                    $ajax.post(this.opinionUrl, this.paramObj).done((res) => {
                        this.list = res;
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
                                <span class="leader-item-left-txt">{{ item.creatorName }}</span>
                                <span class="leader-item-info-txt">{{ item.nodeName }}</span>
                            </div>
                            <div class="leader-item-right">
                                <span class="leader-item-right-txt">{{ item.opinion }}</span>
                                <span class="leader-item-info-txt">{{
                                    item.creatorName
                                }}：{{ item.createDate }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            `
        })
    };

    var obj = {
        init: function (paramsObj,opinionUrl) {
            this.paramsObj = paramsObj;
            this.opinionUrl = opinionUrl;
            this.initDom();
            initVueComponent(this.paramsObj,this.opinionUrl);
        },
        initDom: function () {
            $(".tab-flow").append("<div id='tanglv'></div>");
        },
    };

    return obj;
});