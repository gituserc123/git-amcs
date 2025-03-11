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
                this.query();
            },
            methods: {
                query() {
                    $ajax.post(`${base}/ui/amcs/law/baseui/getLawOpinionList`, this.paramObj).done((res) => {
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
        init: function (paramsObj) {
            this.paramsObj = paramsObj;
            this.initDom();
            initVueComponent(this.paramsObj);
        },
        initDom: function () {
            $(".tab-flow").append("<div id='tanglv'></div>");
        },
    };

    return obj;
});