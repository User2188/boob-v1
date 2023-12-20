package com.example.boobmessage.Service;

import com.example.boobapimessage.dto.CommentOrReplyDTO;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint("/websocket/{userName}")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets =new CopyOnWriteArraySet<>();
    private static Map<String, Session> sessionPool = new HashMap<String,Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="userName")String userName) {
        this.session = session;
        webSockets.add(this);
        sessionPool.put(userName, session);
        System.out.println("【websocket消息】有新用户" + userName +"接入，当前在线总数为:"+webSockets.size());
    }

    @OnClose
    public void onClose() {
        try {
            webSockets.remove(this);
            sessionPool.remove(session.getPathParameters().get("userName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("【websocket消息】连接断开，总数为:"+webSockets.size());
        System.out.println("【sessionPool】总数为:"+sessionPool.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:"+message);
    }

    // 此为广播消息-集体通知
    public void sendAllMessage(String message) {
        for(WebSocket webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:"+message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息-单用户通知
    public String sendOneMessage(String userName, String message) {
        System.out.println("【websocket消息】单点消息:"+message+" \n给用户:"+userName);
        Session session = sessionPool.get(userName);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(message);
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
        else{
            return "not online";
        }
    }

    public String getSessions(){
        return sessionPool.toString();
    }

}