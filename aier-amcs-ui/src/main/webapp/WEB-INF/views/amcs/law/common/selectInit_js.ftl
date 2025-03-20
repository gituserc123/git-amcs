    function reloadReactData() {
        [#if empType==1]
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getProvince',null,false,false).done(function (rst) {
            $('#province').combobox('loadData', rst);
        });
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp?insiId=100001',null,false,false).done(function (rst) {
            $('#instId').combobox('loadData', rst);
        });
        $('#province').combobox({
            onChange: function(v){
                let url = base + '/ui/amcs/adverse/event/query/getHosp?insiId=' + v;
                $('#instId').combobox('reload', url);
            }
        });
        [/#if]
        [#if empType==2]
        var paramInsi = {insiId:'${instId}'};
        $ajax.postSync('${base}/ui/amcs/adverse/event/query/getHosp',paramInsi,false,false).done(function (rst) {
            $('#instId').combobox('loadData', rst);
        });
        [/#if]
    };