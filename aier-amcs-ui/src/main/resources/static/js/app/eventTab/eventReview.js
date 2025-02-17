define(["vue"], function (Vue) {
    console.log('Vue', Vue);

    function initVueComponent(paramObj) {

        return new Vue({
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
                    <div class="leader-bar-title ">点评记录</div>
                    <div class="leader-advice-con">
                        <div class="leader-item-con" v-for="item in list">
                            <div class="leader-item-left">
                                <span class="leader-item-left-txt">{{ item.nodeName }}</span>
                            </div>
                            <div class="leader-item-right">
                                <span class="leader-item-right-txt">{{ item.opinion }}</span>
                                <span class="leader-item-info-txt">点评时间：{{ item.modifyDate }}</span>
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
            
            this.obj = initVueComponent(this.paramsObj);
        },
        reload:function(){
			this.obj.query();
		},
        initDom: function () {
            $(".tab-review").append("<div id='tanglv'></div>");

        },
        loadData: function (callback) {
            
        },
    };

    return obj;
});