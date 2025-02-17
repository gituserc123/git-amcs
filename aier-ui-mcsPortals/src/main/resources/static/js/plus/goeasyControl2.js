/**
 * GEcontrol 创建 GO EASY 连接
 *
 * @function init ( channle,@callback )
 * 1生成channle
 * 2建立连接
 * 3.订阅
 * @return GoEasy 实例
 *
 * @function disconnect (void)
 * 关闭连接
 * @function unsubscribe()
 * 取消订阅
 */

var GEcontrol = (function () {
    var _goEasy;
    var eventTypes = {
        connect :["connected","progressing","connectFailed"],//连接
        subscribe: ["subMsg","subOk","subFailed"],//订阅
        unsubscribe:["unsubOk","unsubFailed"],//取消订阅
        send : ['sendOk','sendFailed']//发送
    }
    var _eventMsg = {
        type:"",
    }
    var _channel;
    var _feedback;
    var _pubsub;
    var  setEventMsg = function (t,m) {
        _eventMsg = { type:t, msg:m}
		console.log('收到消息:',_eventMsg);
        typeof _feedback =="function" && _feedback(_eventMsg);
    }

    var init = function (appkey,channel,feedback,userId,userData) {
        _feedback =feedback;
        _channel =channel
        if(!_goEasy) {
            _goEasy = GoEasy.getInstance({
                host:"hangzhou.goeasy.io",  //若是新加坡区域：singapore.goeasy.io
                appkey:appkey,
                modules:['pubsub']//根据需要，传入‘pubsub’或'im’，或数组方式同时传入
            });

            //建立连接
            _goEasy.connect({
                // id:"001", //pubsub选填，im必填
                // data:{"avatar":"/www/xxx.png","nickname":"Neo"}, //必须是一个对象，pubsub选填，im必填，用于上下线提醒和查询在线用户列表时，扩展更多的属性
                onSuccess: function () {  //连接成功
                    setEventMsg(eventTypes.connect[0],"连接成功");
                },
                onProgress:function(attempts) { //连接或自动重连中
                    setEventMsg(eventTypes.connect[1],"连接或自动重连中");
                    // console.log("GoEasy is connecting", attempts);
                },
                onFailed: function (error) { //连接失败
                    setEventMsg(eventTypes.connect[2],error.content); // 错误编码："+error.code+"错误信息："+error.content
                    // error : {"code":999,"content":"Your GoEasy application is expired, please contact your administrator to renew it"}
                }
            });

            _pubsub = _goEasy.pubsub;
        }

        _pubsub.subscribe({// 订阅 消息
            channel: _channel,
            onMessage: function (message) {
                setEventMsg(eventTypes.subscribe[0],message);
            },
            onSuccess: function () {
                setEventMsg(eventTypes.subscribe[1],"订阅成功");
            },
            onFailed: function (error) {
                setEventMsg(eventTypes.subscribe[2],error.content);
                //error : {code: 408, content: "Failed to connect GoEasy."}
            }
        });
    }


    var unsubscribe =function () {// 取消订阅
        _pubsub.unsubscribe({
            channel: _channel,
            onSuccess: function () {
                setEventMsg(eventTypes.unsubscribe[0], "退订成功");
            },
            onFailed: function (error) {
                setEventMsg(eventTypes.unsubscribe[1], error.content);
            }
        });
    }

    var disconnect = function () {//关闭连接
        _goEasy && _goEasy.disconnect({
            onSuccess: function(){
                console.log("GoEasy 断开成功！")
            },
            onFailed: function(error){
                console.log("GoEasy断开失败, 错误码:"+error.code+ ",错误信息:"+error.content);
            }
        });
    }
    var send = function (msg) {//发送消息
        _pubsub.publish({
            channel: _channel,
            message: msg,
            onSuccess: function(){
                setEventMsg(eventTypes.send[0], '发送成功');
            },
            onFailed: function (error) {
                setEventMsg(eventTypes.send[1], error.content);
            }
        });
    }


    return {
        init: init,
        unsubscribe: unsubscribe,
        disconnect: disconnect,
        send: send
    }



})();

