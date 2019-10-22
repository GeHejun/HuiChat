﻿let Socket = '';
let setIntervalWebSocketPush = null;

/**建立连接 */
export function createSocket(url) {
    if (!Socket) {
        console.log('建立websocket连接')
        Socket = new WebSocket(url)
        Socket.onopen = onopenWS;
        Socket.onmessage = onmessageWS;
        Socket.onerror = onerrorWS;
        Socket.onclose = oncloseWS;
    } else {
        console.log('websocket已连接')
    }
}
/**打开WS之后发送心跳 */
export function onopenWS() {
    sendPing() //发送心跳
}
/**连接失败重连 */
export function onerrorWS() {
    clearInterval(setIntervalWebSocketPush)
    Socket.close();
    createSocket() //重连
}
/**WS数据接收统一处理 */
export function onmessageWS(e) {
    window.dispatchEvent(new CustomEvent('onmessageWS', {
        detail: {
            data: e
        }
    }))
}
/**发送数据
 * @param eventType
 */
export function sendWSPush(obj) {
    if (Socket !== null && Socket.readyState === 3) {
        Socket.close();
        createSocket() //重连
    } else if (Socket.readyState === 1) {
        Socket.send(JSON.stringify(obj))
    } else if (Socket.readyState === 0) {
        setTimeout(() => {
            Socket.send(JSON.stringify(obj))
        }, 3000)
    }
}
/**关闭WS */
export function oncloseWS() {
    clearInterval(setIntervalWebSocketPush)
    console.log('websocket已断开')
}
/**发送心跳 */
export function sendPing() {
    let user = localStorage.getItem("user");
    let ping = {"from":user.id};
    Socket.send(ping)
    setIntervalWebSocketPush = setInterval(() => {
        Socket.send(ping)
    }, 2000)
}