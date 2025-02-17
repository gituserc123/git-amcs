<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
    [#include "/WEB-INF/views/common/include_resources.ftl"]
  <style>
    .cus-panel{
        display: flex;
        flex-wrap: wrap;
        padding: 20px;
    }
    .panel-item{
        padding:8px 30px;
        font-size: 14px;
        border:1px solid #59abf4;
        margin-right: 10px;
        cursor: pointer;
        margin-bottom: 20px;
    }
    .panel-item:hover{
        background-color: #59abf4;
        color: #FFFFFF;
        transition: all 0.2s;
    }
    .noReady{
      background-color: #bdbdbd;
      color: #FFFFFF;
      transition: all 0.2s;
    }
  </style>
</head>
<body>
  <div class="events_wrap pad-15">
[#--    <h2 class="h2-title-a">--]
[#--      <span class="s-title pad-l20" style="color: #333333;font-size: 16px;">医疗类</span>--]
[#--    </h2>--]
[#--    <hr class="mar-l10 mar-r10 mar-t0 mar-b0" style="border-color:#b2def4">--]
[#--    <div class="medical-panel cus-panel"></div>--]

[#--    <h2 class="h2-title-a">--]
[#--      <span class="s-title pad-l20" style="color: #333333;font-size: 16px;">护理</span>--]
[#--    </h2>--]
[#--    <hr class="mar-l10 mar-r10 mar-t0 mar-b0" style="border-color:#b2def4">--]
[#--    <div class="nursing-panel cus-panel"></div>--]

[#--    <h2 class="h2-title-a">--]
[#--      <span class="s-title pad-l20" style="color: #333333;font-size: 16px;">医院感染类</span>--]
[#--    </h2>--]
[#--    <hr class="mar-l10 mar-r10 mar-t0 mar-b0" style="border-color:#b2def4">--]
[#--    <div class="infected-panel cus-panel"></div>--]
  </div>
</body>
[#include "/WEB-INF/views/common/include_js.ftl"]
<script type="text/javascript">
    requirejs(['lodash','pub'], function (_) {

      var eventData;
      $ajax.post({
        url:"${base}/ui/amcs/adverse/eventConfig/getAll",
        sync:true,
        callback :function (rst){
          eventData=rst.rows;
        }
      })
      var groupData=_.groupBy(eventData,function(o){return o.eventType})

        // var arr_me = [{"title":"门诊患者不良事件","url":"/ui/service/biz/amcs/adverse/aeOutp/getAeOutp"},[{"title":"时间","url":"https://baidu.com"}]]
        // var arr_no = [{"title":"护理类","url":"https://baidu.com"},[{"title":"时间","url":"https://baidu.com"}]]
        // var arr_in = [{"title":"感染类","url":"https://baidu.com"},[{"title":"时间","url":"https://baidu.com"}]]


      for(var k  in groupData){
        var htmlStr = "";
        var arr=groupData[k]
        var typeStr = '<h2 class="h2-title-a"> <span class="s-title pad-l20" style="color: #333333;font-size: 16px;">'+k+'</span> </h2> <hr class="mar-l10 mar-r10 mar-t0 mar-b0" style="border-color:#b2def4"> <div class="medical-panel cus-panel">'
        arr.forEach(function(item){
          var s=item.REPORTING?"":"noReady"
            var tmpStr = "<div class='panel-item "+s+"' data-option='${base}/"+item.eventUrl+"'>"+item.eventName+"</div>";
            htmlStr += tmpStr;
        });
        typeStr+=htmlStr;
        typeStr+='</div>'
        $('.events_wrap').append(typeStr)
      }
        // function generatePanel(selectEle,arr){
        //         var htmlStr = "";
        //         if(!!arr && !!arr.length){
        //             arr.forEach(function(item){
        //                 var tmpStr = "<div class='panel-item' data-option='"+item.url+"'>"+item.title+"</div>";
        //                 htmlStr += tmpStr;
        //             });
        //         }
        //         if(!!htmlStr){
        //            $(selectEle).append(htmlStr);
        //         }
        // }
        // generatePanel(".medical-panel",arr_me);
        // generatePanel(".nursing-panel",arr_no);
        // generatePanel(".infected-panel",arr_in);

        $(".cus-panel").on('click','.panel-item',function(e){
            debugger;
            e.preventDefault();
            e.stopPropagation();
            console.log($(this).text());
            console.log($(this).data('option'))
            if($(this).hasClass('noReady')){
              $pop.alert("事件尚未开放")
              return
            }
            let url = $(this).data('option') + '&patientNo=${patientNo}';
            $pop.newTabWindow($(this).text(), url);
        });
    });
</script>
</html>