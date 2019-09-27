let wsUri = "ws://140.143.125.231:9998/";
var deviceId = parseInt(10 * Math.random() + 1);
var websocket;
let routeMsgId = 10000;
let loginMsgId = 20000;

function sendMessage(message) {
    protobuf.load("/proto/message.proto", function (err, root) {
        if (err) throw err;
        let WSMessage = root.lookup("com.ghj.protocol.MessageProto.Message");
        let wsmessage = WSMessage.create(message);
        let buffer = WSMessage.encode(wsmessage).finish();
        websocket.send(buffer);
    });
}

layui.config({
    base: './layui/lay/modules/'
}).extend({
    notice: 'notice'
});

layui.use(['layim','notice'], function () {
    var notice = layui.notice;
    var layim = layui.layim;

    // 初始化配置，同一样式只需要配置一次，非必须初始化，有默认配置
    notice.options = {
        closeButton:true,//显示关闭按钮
        debug:false,//启用debug
        positionClass:"toast-top-right",//弹出的位置,
        showDuration:"300",//显示的时间
        hideDuration:"1000",//消失的时间
        timeOut:"5000",//停留的时间
        extendedTimeOut:"1000",//控制时间
        showEasing:"swing",//显示时的动画缓冲方式
        hideEasing:"linear",//消失时的动画缓冲方式
        iconClass: 'toast-info', // 自定义图标，有内置，如不需要则传空 支持layui内置图标/自定义iconfont类名
        onclick: null, // 点击关闭回调
    };

    var user = JSON.parse(localStorage.getItem('user'));

    route(wsUri);
    function route(url) {
        websocket = new WebSocket(url);
        websocket.onopen = function () {
            let message = {
                "messageBehavior": 9,
                "messageDirect": 4,
                "deviceId": deviceId,
                "id": routeMsgId,
            }
            sendMessage(message);
        };
        websocket.onmessage = function (evt) {
            onMessage(evt)
        };
    }

    function connect(url) {
        websocket = new WebSocket(url);
        websocket.onopen = function (evt) {
            let message = {
                "id": user.id + loginMsgId,
                "loginName": user.name,
                "fromUserId": user.id,
                "messageTypeId": 0,
                "messageBehavior": 1,
                "messageDirect": 3,
                "deviceId": deviceId,
                "token": user.token
            };
            sendMessage(message);
        };
        websocket.onmessage = function (evt) {
            onMessage(evt)
        };
    }

    function onMessage(evt) {
        protobuf.load("/proto/message.proto", function (err, root) {
            if (err) throw err;
            let WSMessage = root.lookup("com.ghj.protocol.MessageProto.Message");
            let reader = new FileReader();
            reader.readAsArrayBuffer(evt.data);
            reader.onload = function (e) {
                let buf = new Uint8Array(reader.result);
                let buffer = WSMessage.decode(buf);
                if (buffer.matchMessageId == routeMsgId) {
                    websocket.close();
                    let url = "ws://" + buffer.ip + ":" + buffer.port;
                    connect(url);
                } else if (buffer.matchMessageId == user.id + loginMsgId) {
                    //基础配置
                    layim.config({
                        //初始化接口
                        init: {
                            url: '/index/initMainFrame'
                            , data: {id: user.id}
                        }
                        , members: {
                            url: '/index/initMembers/'
                            , data: {}
                        }
                        //上传图片接口
                        , uploadImage: {
                            url: '/upload/image' //（返回的数据格式见下文）
                            , type: '' //默认post
                        }

                        //上传文件接口
                        , uploadFile: {
                            url: '/upload/file' //（返回的数据格式见下文）
                            , type: '' //默认post
                        }
                        , isAudio: true //开启聊天工具栏音频
                        , isVideo: true //开启聊天工具栏视频
                        //扩展工具栏
                        , tool: [{
                            alias: 'code'
                            , title: '代码'
                            , icon: '&#xe64e;'
                        }]
                        , title: "NOW-CHAT"
                        , initSkin: '5.jpg' //1-5 设置初始背景
                        , notice: true //是否开启桌面消息提醒，默认false
                        , msgbox: '/layui/css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
                        , find: '/layui/css/modules/layim/html/find.html' //发现页面地址，若不开启，剔除该项即可
                        , chatLog: '/layui/css/modules/layim/html/chatlog.html' //聊天记录页面地址，若不开启，剔除该项即可

                    });

                    //每次窗口打开或切换，即更新对方的状态
                    layim.on('chatChange', function (res) {
                        //标注成已读
                        $.post('/index/readMessage',
                            {type:res.data.type, fromUserId:res.data.id, toUserId:user.id},
                            function (res) {
                            if (res.code != 0) {
                                return layer.msg(res.msg);
                            }
                        });

                        //查询好友状态
                        var type = res.data.type;
                        if (type === 'friend') {
                            $.ajax({
                                url: "/index/initFriendState",
                                data: {"userId": user.id},
                                type: "Post",
                                dataType: "json",
                                success: function (data) {
                                    if (data.data == true) {
                                        //请求在线状态
                                        layim.setChatStatus('<span style="color:#FF5722;">在线</span>');
                                    } else {
                                        layim.setChatStatus('<span style="color:#FF5722;">离线</span>');
                                    }
                                },
                                error: function (data) {
                                }
                            });
                            //模拟标注好友在线状态
                        } else if (type === 'group') {
                        }
                    });


                    layim.on('ready', function () {
                        $.ajax({
                            url: "/index/initOffLineMessages",
                            data: {"toUserId": user.id},
                            type: "Post",
                            dataType: "json",
                            success: function (data) {
                                var messageList = data.data;
                                for (let i = 0; i < messageList.length; i++) {
                                    var message = messageList[i];
                                    layim.getMessage({
                                        username: message.username //消息来源用户名
                                        , avatar: message.avatar //消息来源用户头像
                                        , id: message.id //消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
                                        , type: message.type //聊天窗口来源类型，从发送消息传递的to里面获取
                                        , content: message.content //消息内容
                                        , cid: message.cid //消息id，可不传。除非你要对消息进行一些操作（如撤回）
                                        , mine: false //是否我发送的消息，如果为true，则会显示在右方
                                        , fromid: message.fromUserId //消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
                                        , timestamp: message.timestamp //服务端时间戳毫秒数。注意：如果你返回的是标准的 unix 时间戳，记得要 *1000
                                    });
                                }
                                //
                            },
                            error: function (data) {
                            }
                        });

                        $.ajax({
                            url: "/index/initUnreadMessageNum",
                            data: {"toUserId": user.id},
                            type: "Post",
                            dataType: "json",
                            success: function (data) {
                                var messageList = data.data;
                                for (let i = 0; i < messageList.length; i++) {
                                    var message = messageList[i];
                                    notice.info(message.content);
                                }
                            },
                            error: function (data) {
                            }
                        });
                    });
                    //监听在线状态的切换事件
                    layim.on('online', function (data) {
                    });

                    layim.on('members', function (data) {
                        console.log(data);
                    });

                    //监听签名修改
                    layim.on('sign', function (value) {

                    });

                    //监听自定义工具栏点击，以添加代码为例
                    layim.on('tool(code)', function (insert) {
                        layer.prompt({
                            title: '插入代码'
                            , formType: 2
                            , shade: 0
                        }, function (text, index) {
                            layer.close(index);
                            insert('[pre class=layui-code]' + text + '[/pre]'); //将内容插入到编辑器
                        });
                    });

                    layim.on('sendMessage', function (res) {
                        var messageDirect = res.to.type == 'friend' ? 1 : 2;
                        var fromUserId = res.mine.id; //包含我发送的消息及我的信息
                        var toUserId = res.to.id; //对方的信息
                        var id = new Snowflake().nextId();
                        var messageTypeId = 1;
                        var content = res.mine.content;
                        var messageBehavior = 3;
                        var deviceId = deviceId;
                        var username = res.mine.username;
                        var avatar = res.mine.avatar;
                        var message = {
                            "fromUserId": fromUserId,
                            "id": id,
                            "messageTypeId": messageTypeId,
                            "content": content,
                            "messageBehavior": messageBehavior,
                            "messageDirect": messageDirect,
                            "deviceId": deviceId,
                            "name": username,
                            "headPortrait": avatar
                        };
                        if (res.to.type == 'friend') {
                            var key = "toUserId";
                        } else {
                            var key = "toGroupId";
                        }
                        var value = toUserId;
                        message[key] = value;
                        //监听到上述消息后，就可以轻松地发送socket了，如：
                        sendMessage(message);
                    });

                } else {
                    if (buffer.messageBehavior != 5) {
                        if (buffer.messageBehavior == 11  || buffer.messageBehavior == 12) {
                            $.ajax({
                                url: "/index/initMessageBoxNum",
                                data: {"toUserId": user.id},
                                type: "Post",
                                dataType: "json",
                                success: function (data) {
                                    layim.msgbox(data.data);
                                },
                                error: function (data) {
                                }
                            });
                        } else {
                            layim.getMessage({
                                username: buffer.name //消息来源用户名
                                , avatar: buffer.headPortrait //消息来源用户头像
                                , id: buffer.messageDirect == 1 ? buffer.fromUserId : buffer.toGroupId //消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
                                , type: buffer.messageDirect == 1 ? "friend" : "group" //聊天窗口来源类型，从发送消息传递的to里面获取
                                , content: buffer.content //消息内容
                                , cid: buffer.id //消息id，可不传。除非你要对消息进行一些操作（如撤回）
                                , mine: false //是否我发送的消息，如果为true，则会显示在右方
                                , fromid: buffer.fromUserId //消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
                                , timestamp: new Date() //服务端时间戳毫秒数。注意：如果你返回的是标准的 unix 时间戳，记得要 *1000
                            });
                        }

                    }
                }
            }
        });
    }
});




