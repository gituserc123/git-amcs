<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>首次上报</title>
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
        url:"${base}/ui/amcs/law/manage/getAll",
        sync:true,
        callback :function (rst){
          eventData=rst;
        }
      })
      var groupData=_.groupBy(eventData,function(o){return o.lawTitle})
      for(var k  in groupData){
        var htmlStr = "";
        var arr=groupData[k]
        var typeStr = '<h2 class="h2-title-a"> <span class="s-title pad-l20" style="color: #333333;font-size: 16px;">'+k+'</span> </h2> <hr class="mar-l10 mar-r10 mar-t0 mar-b0" style="border-color:#b2def4"> <div class="medical-panel cus-panel">'
        arr.forEach(function(item){
            var tmpStr = "<div class='panel-item' data-option='${base}/"+item.lawUrl+"'>"+item.lawName+"</div>";
            htmlStr += tmpStr;
        });
        typeStr+=htmlStr;
        typeStr+='</div>'
        $('.events_wrap').append(typeStr)
      }

        $(".cus-panel").on('click','.panel-item',function(e){
            e.preventDefault();
            e.stopPropagation();
            let url = $(this).data('option');
            $pop.newTabWindow($(this).text(), url);
        });
    });
</script>
</html>