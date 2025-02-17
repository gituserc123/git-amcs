var temData = {
  "msg":"\u64CD\u4F5C\u6210\u529F\uFF01",
  "code":"200",
  "data":[
    {
      "creator":8351083510,
      "bizType":"AHIS_1",
      "modifyDate":"2020-08-06 10:30:48",
      "endDate":"",
      "msgId":"1291200020594614274",
      "messageTitle":"\u533B\u5631\u53D8\u52A8(\u59D3\u540D:\u9EC4\u4F20\u51E4)",
      "gapSeconds":"",
      "modifer":8351083510,
      "rowId":1,
      "messageUrl":"\/ui\/inpdoctor\/dealRoleMsg",
      "beginDate":"",
      "messageType":2,
      "hospId":9999,
      "bizId":"1240088259116150786",
      "state":0,
      "id":"1291200020691083265",
      "platformCode":"AHIS",
      "messageContent":"{\"patientName\":\"\u9EC4\u4F20\u51E4\",\"currentWardName\":\"\u4E00\u75C5\u533A\",\"state6\":0,\"state5\":0,\"id\":1240088259116150786,\"currentDeptName\":\"\u7EFC\u5408\u773C\u75C5\u79D1\",\"bedNumber\":\"001\",\"state2\":2,\"currentWardId\":1075926831689093122,\"inpNumber\":\"ZY20200318001\"}",
      "createDate":"2020-08-06 10:30:48"
    },
    {
      "creator":8351083510,
      "bizType":"AEMR_Admission",
      "modifyDate":"2020-08-06 09:38:02",
      "endDate":"2020-08-25 18:34:01",
      "msgId":"1291186812634849282",
      "messageTitle":"\u4F4F\u9662\u75C5\u5386(cheng)",
      "gapSeconds":"",
      "modifer":8351083510,
      "rowId":2,
      "messageUrl":"\/ui\/doc\/writer\/writeMedicalRecord?inpNumber=ZY20191016003",
      "beginDate":"2020-08-05 15:34:01",
      "messageType":2,
      "hospId":9999,
      "bizId":"1291186743562756099",
      "state":0,
      "id":"1291186812706152449",
      "platformCode":"aemr",
      "messageContent":"{\"bedNumber\":\"10006\u5E8A\",\"eventContents\":[{\"contentOrUrl\":\"\/ui\/doc\/writer\/writeMedicalRecord?inpNumber=ZY20191016003\",\"promptingContent\":\"\u9EBB\u9189\u540C\u610F\u4E66\u5269\u4F59\u4E66\u5199\u65F6\u95F4\u4E0D\u8DB322\u5C0F\u65F6\"}],\"inpDeptName\":\"\u767D\u5185\u969C\u79D1\",\"inpWardName\":\"\u4E00\u75C5\u533A\",\"patientName\":\"cheng\",\"patientURL\":\"\/ui\/doc\/writer\/writeMedicalRecord?inpNumber=ZY20191016003\"}",
      "createDate":"2020-08-06 09:38:02"
    }
  ]
};



var sendMsg = {"type":"subMsg","msg":{"time":1596782833689,"channel":"9999-8351083510","content":"{\"creator\":\"8351083510\",\"bizType\":\"AEMR_Admission\",\"modifyDate\":\"2020-08-07 14:47:10\",\"endDate\":\"2020-08-25 18:32:01\",\"messageTitle\":\"住院病历(张三)\",\"modifer\":\"8351083510\",\"beginDate\":\"2020-08-06 15:34:01\",\"messageUrl\":\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\",\"messageType\":2,\"hospId\":9999,\"bizId\":\"1291626924002521089\",\"id\":\"1291626936371896322\",\"platformCode\":\"aemr\",\"createDate\":\"2020-08-07 14:47:10\",\"messageContent\":\"{\\\"bedNumber\\\":\\\"1106床\\\",\\\"eventContents\\\":[{\\\"contentOrUrl\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"promptingContent\\\":\\\"西医处方剩余书写时间不足-6小时\\\"}],\\\"inpDeptName\\\":\\\"白内障科\\\",\\\"inpWardName\\\":\\\"二病区\\\",\\\"patientName\\\":\\\"张三\\\",\\\"patientURL\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\"}\"}"}};



var sendMsg1 = {"type":"subMsg","msg":{"time":1597044653936,"channel":"9999-8351083510","content":"{\"creator\":\"8351083510\",\"bizType\":\"AEMR_Admission\",\"modifyDate\":\"2020-08-10 15:30:50\",\"endDate\":\"2020-08-25 19:21:58\",\"messageTitle\":\"住院病历(张三)\",\"modifer\":\"8351083510\",\"beginDate\":\"2020-08-09 23:34:01\",\"messageUrl\":\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\",\"messageType\":2,\"hospId\":9999,\"bizId\":\"1292725078391382017\",\"id\":\"1292725090257215490\",\"state\":\"0\",\"platformCode\":\"aemr\",\"createDate\":\"2020-08-10 15:30:50\",\"messageContent\":\"{\\\"bedNumber\\\":\\\"1106床\\\",\\\"eventContents\\\":[{\\\"contentOrUrl\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"promptingContent\\\":\\\"西医处方剩余书写时间不足1小时\\\"}],\\\"inpDeptName\\\":\\\"白内障科\\\",\\\"inpWardName\\\":\\\"二病区\\\",\\\"patientName\\\":\\\"张三\\\",\\\"patientURL\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"uniquePersonKey\\\":\\\"9999ZY20191016006\\\"}\"}"}};

var sendMsg2 ={"type":"subMsg","msg":{"time":1597044654050,"channel":"9999-8351083510","content":"{\"creator\":\"8351083510\",\"bizType\":\"AEMR_Admission\",\"modifyDate\":\"2020-08-10 15:30:50\",\"endDate\":\"2020-08-25 22:36:41\",\"messageTitle\":\"住院病历(张三)\",\"modifer\":\"8351083510\",\"beginDate\":\"2020-08-09 23:34:01\",\"messageUrl\":\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\",\"messageType\":2,\"hospId\":9999,\"bizId\":\"1292725078391382018\",\"id\":\"1292725090764726273\",\"state\":\"0\",\"platformCode\":\"aemr\",\"createDate\":\"2020-08-10 15:30:50\",\"messageContent\":\"{\\\"bedNumber\\\":\\\"1106床\\\",\\\"eventContents\\\":[{\\\"contentOrUrl\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"promptingContent\\\":\\\"生命体征测量记录剩余书写时间不足7小时\\\"}],\\\"inpDeptName\\\":\\\"白内障科\\\",\\\"inpWardName\\\":\\\"二病区\\\",\\\"patientName\\\":\\\"张三\\\",\\\"patientURL\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"uniquePersonKey\\\":\\\"9999ZY20191016006\\\"}\"}"}}


var updateMsg1 = {"type":"subMsg","msg":{"time":1597044739882,"channel":"9999-8351083510","content":"{\"creator\":\"8351083510\",\"bizType\":\"AEMR_Admission\",\"modifyDate\":\"2020-08-10 15:32:18\",\"endDate\":\"2020-08-26 18:14:36\",\"messageTitle\":\"住院病历(张三)\",\"modifer\":\"8351083510\",\"beginDate\":\"2020-08-09 23:34:01\",\"messageUrl\":\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\",\"messageType\":2,\"hospId\":9999,\"bizId\":\"1292725078391382017\",\"id\":\"1292725090257215490\",\"state\":\"0\",\"platformCode\":\"aemr\",\"createDate\":\"2020-08-10 15:30:50\",\"messageContent\":\"{\\\"bedNumber\\\":\\\"168床\\\",\\\"eventContents\\\":[{\\\"contentOrUrl\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"promptingContent\\\":\\\"西医处方剩余书写时间不足1小时\\\"}],\\\"inpDeptName\\\":\\\"青光眼科\\\",\\\"inpWardName\\\":\\\"二病区\\\",\\\"patientName\\\":\\\"张三\\\",\\\"patientURL\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"uniquePersonKey\\\":\\\"9999ZY20191016006\\\"}\"}"}};

var updateMsg2 = {"type":"subMsg","msg":{"time":1597044739973,"channel":"9999-8351083510","content":"{\"creator\":\"8351083510\",\"bizType\":\"AEMR_Admission\",\"modifyDate\":\"2020-08-10 15:32:19\",\"endDate\":\"2020-08-26 22:23:34\",\"messageTitle\":\"住院病历(张三)\",\"modifer\":\"8351083510\",\"beginDate\":\"2020-08-09 23:34:01\",\"messageUrl\":\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\",\"messageType\":2,\"hospId\":9999,\"bizId\":\"1292725078391382018\",\"id\":\"1292725090764726273\",\"state\":\"0\",\"platformCode\":\"aemr\",\"createDate\":\"2020-08-10 15:30:50\",\"messageContent\":\"{\\\"bedNumber\\\":\\\"168床\\\",\\\"eventContents\\\":[{\\\"contentOrUrl\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"promptingContent\\\":\\\"生命体征测量记录剩余书写时间不足7小时\\\"}],\\\"inpDeptName\\\":\\\"青光眼科\\\",\\\"inpWardName\\\":\\\"二病区\\\",\\\"patientName\\\":\\\"张三\\\",\\\"patientURL\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"uniquePersonKey\\\":\\\"9999ZY20191016006\\\"}\"}"}};

var delItemMsg = {"type":"subMsg","msg":{"time":1597044989418,"channel":"9999-8351083510","content":"{\"creator\":\"8351083510\",\"bizType\":\"AEMR_Admission\",\"modifyDate\":\"2020-08-10 15:36:28\",\"endDate\":\"2020-08-25 19:36:25\",\"messageTitle\":\"住院病历(张三)\",\"modifer\":\"8351083510\",\"beginDate\":\"2020-08-09 23:34:01\",\"messageUrl\":\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\",\"messageType\":2,\"hospId\":9999,\"bizId\":\"1292725078391382017\",\"id\":\"1292725090257215490\",\"state\":\"2\",\"platformCode\":\"aemr\",\"createDate\":\"2020-08-10 15:30:50\",\"messageContent\":\"{\\\"bedNumber\\\":\\\"268床\\\",\\\"eventContents\\\":[{\\\"contentOrUrl\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"promptingContent\\\":\\\"西医处方剩余书写时间不足0小时\\\"}],\\\"inpDeptName\\\":\\\"青光眼科\\\",\\\"inpWardName\\\":\\\"二病区\\\",\\\"patientName\\\":\\\"张三\\\",\\\"patientURL\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"uniquePersonKey\\\":\\\"9999ZY20191016006\\\"}\"}"}};

var delItemMsg2 = {"type":"subMsg","msg":{"time":1597044989499,"channel":"9999-8351083510","content":"{\"creator\":\"8351083510\",\"bizType\":\"AEMR_Admission\",\"modifyDate\":\"2020-08-10 15:36:29\",\"endDate\":\"2020-08-25 22:36:25\",\"messageTitle\":\"住院病历(张三)\",\"modifer\":\"8351083510\",\"beginDate\":\"2020-08-09 23:34:01\",\"messageUrl\":\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\",\"messageType\":2,\"hospId\":9999,\"bizId\":\"1292725078391382018\",\"id\":\"1292725090764726273\",\"state\":\"2\",\"platformCode\":\"aemr\",\"createDate\":\"2020-08-10 15:30:50\",\"messageContent\":\"{\\\"bedNumber\\\":\\\"268床\\\",\\\"eventContents\\\":[{\\\"contentOrUrl\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"promptingContent\\\":\\\"生命体征测量记录剩余书写时间不足0小时\\\"}],\\\"inpDeptName\\\":\\\"青光眼科\\\",\\\"inpWardName\\\":\\\"二病区\\\",\\\"patientName\\\":\\\"张三\\\",\\\"patientURL\\\":\\\"/ui/doc/writer/writeMedicalRecord?inpNumber\\u003dZY20191016006\\\",\\\"uniquePersonKey\\\":\\\"9999ZY20191016006\\\"}\"}"}};




$('h1').after('<button type="button" class="btn-testMsg1">追1</button>' +
  '  <button type="button" class="btn-testMsg2">追2</button>' +
  '  <button type="button" class="btn-updateMsg1">更1</button>' +
  '  <button type="button" class="btn-updateMsg2">更2</button>' +
  '  <button type="button" class="btn-testMsg3">减1</button>' +
  '  <button type="button" class="btn-testMsg4">减2</button>')



$('.btn-testMsg').click(function (){
  aierMsg.receiveMsg(sendMsg);
});

$('.btn-testMsg1').click(function (){
  aierMsg.receiveMsg(sendMsg1);
});
$('.btn-testMsg2').click(function (){
  aierMsg.receiveMsg(sendMsg2);
});



$('.btn-updateMsg1').click(function (){
  aierMsg.receiveMsg(updateMsg1);
});

$('.btn-updateMsg2').click(function (){
  aierMsg.receiveMsg(updateMsg2);
});


$('.btn-testMsg3').click(function (){
  aierMsg.receiveMsg(delItemMsg);
});

$('.btn-testMsg4').click(function (){
  aierMsg.receiveMsg(delItemMsg2);
});

$('.btn-testMsg5').click(function (){
  aierMsg.receiveMsg(updateMsg);
});



var temData_b2 = {
  "msg":"\u64CD\u4F5C\u6210\u529F\uFF01",
  "code":"200",
  "data":[
    {
      "creator":8351083510,
      "bizType":"AHIS_1",
      "modifyDate":"2020-08-19 15:45:53",
      "endDate":"",
      "msgId":"1294207035692666881",
      "messageTitle":"\u5728\u9662\u60A3\u8005\u5904\u7406",
      "gapSeconds":"",
      "modifer":8351083510,
      "rowId":1,
      "messageUrl":"\/ui\/inpdoctor\/dealRoleMsg?messageId=1249527609706455042&patientId=1133600509888352258&inpNumber=ZY20200413002",
      "beginDate":"",
      "messageType":2,
      "hospId":9999,
      "bizId":"1249527609706455042",
      "state":0,
      "id":"1295990355992637442",
      "platformCode":"ahis",
      "messageContent":"{\"patientName\":\"\u6C88\u4F1F\",\"currentWardName\":\"\u4E00\u75C5\u533A\",\"state6\":0,\"state5\":0,\"id\":1249527609706455042,\"currentDeptName\":\"\u767D\u5185\u969C\u79D1\",\"bedNumber\":\"13\",\"state2\":0,\"currentWardId\":1075926831689093122,\"inpNumber\":\"ZY20200413002\"}",
      "createDate":"2020-08-14 17:39:37"
    }
  ]
}


var updateMsg_b1 = {"type":"subMsg","msg":{"time":1632642167728,"channel":"9999-8351083510","content":"{\"bizId\":\"1441933194856280065\",\"bizType\":\"AHIS_1\",\"createDate\":\"2021-09-26 09:23:40\",\"creator\":\"7917079170\",\"hospId\":9999,\"id\":\"1441936476414828545\",\"messageContent\":\"{\\\"patientName\\\":\\\"昆明视光926\\\",\\\"currentWardName\\\":\\\"一病区\\\",\\\"state6\\\":0,\\\"state5\\\":0,\\\"id\\\":1441933194856280065,\\\"currentDeptName\\\":\\\"斜视及小儿眼病科\\\",\\\"bedNumber\\\":\\\"昆明27\\\",\\\"state2\\\":2,\\\"urgent\\\":0,\\\"currentWardId\\\":1075926831689093122,\\\"inpNumber\\\":\\\"ZY20210926001\\\"}\",\"messageTitle\":\"在院患者处理\",\"messageType\":2,\"messageUrl\":\"/ui/inpnurse/inpatientOrder?inpNumber\\u003dZY20210926001\\u0026patientId\\u003d1441927114998722561\",\"modifer\":\"7917079170\",\"modifyDate\":\"2021-09-26 15:42:46\",\"platformCode\":\"ahis\",\"state\":\"0\"}"}}

var updateMsg_b2 = {"type":"subMsg","msg":{"time":1601198526720,"channel":"9999-8351083510","content":"{\"creator\":\"8351083510\",\"bizType\":\"AHIS_1\",\"modifyDate\":\"2020-09-27 17:22:06\",\"messageTitle\":\"在院患者处理\",\"modifer\":\"8351083510\",\"messageUrl\":\"/ui/inpnurse/inpatientOrder?inpNumber\\u003dZY20200320003\\u0026patientId\\u003d1174844908274655233\",\"messageType\":2,\"hospId\":9999,\"bizId\":\"1240912380374982657\",\"id\":\"1294205572258058242\",\"state\":\"0\",\"platformCode\":\"ahis\",\"createDate\":\"2020-08-14 17:33:48\",\"messageContent\":\"{\\\"patientName\\\":\\\"刘冲\\\",\\\"currentWardName\\\":\\\"一病区\\\",\\\"state6\\\":0,\\\"state5\\\":0,\\\"id\\\":1240912380374982657,\\\"currentDeptName\\\":\\\"白内障科\\\",\\\"bedNumber\\\":\\\"501\\\",\\\"state2\\\":2,\\\"urgent\\\":39,\\\"currentWardId\\\":1075926831689093122,\\\"inpNumber\\\":\\\"ZY20200320003\\\"}\"}"}};

var updateMsg_b3 = {"type":"subMsg","msg":{"time":1632641190536,"channel":"9999-8351083510","content":"{\"bizId\":\"1245608536300482562\",\"bizType\":\"AHIS_1\",\"createDate\":\"2021-09-23 10:38:31\",\"creator\":\"8351083510\",\"hospId\":9999,\"id\":\"1440868149167247361\",\"messageContent\":\"{\\\"patientName\\\":\\\"吴枫\\\",\\\"currentWardName\\\":\\\"一病区\\\",\\\"state6\\\":0,\\\"state5\\\":0,\\\"id\\\":1245608536300482561,\\\"currentDeptName\\\":\\\"综合眼病科\\\",\\\"bedNumber\\\":\\\"001\\\",\\\"state2\\\":8,\\\"urgent\\\":3,\\\"currentWardId\\\":1075926831689093122,\\\"inpNumber\\\":\\\"ZY20200402001\\\"}\",\"messageTitle\":\"在院患者处理\",\"messageType\":2,\"messageUrl\":\"/ui/inpnurse/inpatientOrder?inpNumber\\u003dZY20200402001\\u0026patientId\\u003d1240099183962701826\",\"modifer\":\"8351083510\",\"modifyDate\":\"2021-09-26 15:26:30\",\"platformCode\":\"ahis\",\"state\":\"2\"}"}}


$('h1').after('<button type="button" class="btn-updateMsg_b1">医嘱增</button>' +
'<button type="button" class="btn-updateMsg_b2">特殊医嘱增</button>'+
'<button type="button" class="btn-updateMsg_b3">医嘱减</button>');

$('.btn-updateMsg_b1').click(function (){
  aierMsg.receiveMsg(updateMsg_b1);
});

$('.btn-updateMsg_b2').click(function (){
  aierMsg.receiveMsg(updateMsg_b2);
});

$('.btn-updateMsg_b3').click(function (){
  aierMsg.receiveMsg(updateMsg_b3);
});

var tempData_cv ={"type":"subMsg","msg":{"time":1599447414495,"channel":"9999-8351083510","content":"{\"creator\":\"8351083510\",\"bizType\":\"ALIS_CV\",\"modifyDate\":\"2020-09-07 10:56:54\",\"modifer\":\"8351083510\",\"messageType\":2,\"hospId\":9999,\"bizId\":\"1302776950049570817\",\"id\":\"1302780736667123713\",\"state\":\"2\",\"platformCode\":\"alis\",\"createDate\":\"2020-09-07 09:28:26\"}"}};



$('h1').after('<button type="button" class="btn-updateMsg-cv1">cv更1</button>');

$('.btn-updateMsg-cv1').click(function (){
  aierMsg.receiveMsg(tempData_cv);
});
// $('.btn-updateMsg-cv2').click(function (){
//   aierMsg.receiveMsg(tempData_cv2);
// });
