/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chat.server;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Hany
 */
@ServerEndpoint("/speak/{chatroom}")
public class ChatEndpoint {
    
    private String user;
    private String chatroom;
    
    @OnOpen
    public void onOpen(Session session,
            @PathParam("chatroom") String chatroom) {
        
        this.user = getParamValue(session.getQueryString(), "user");
        if(this.user == null) {
            this.user = "guest";
        }
        
        this.chatroom = chatroom;
        System.out.println("OPEN " + chatroom + "/" + user + " @ " + session.getId());
        session.getUserProperties().put("chatroom", chatroom);
        
        broadcast(session, user + " has joined the conversation");
    }
    
    private String getParamValue(String query, String param) {
        if(query != null && query.contains(param)) {
            query = query.substring( query.indexOf(param) + param.length() + 1 );
            if(query.contains("&")) {
                query = query.substring(0, query.indexOf('&'));
            }
            return query;
        }
        return null;
    }
    
    @OnMessage
    public void onMessage(Session session, String message) {
        broadcast(session, user + " : " + message);
    }
    
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        broadcast(session, user + " has left the conversation");
        System.out.println("onClose | " + closeReason);
    }
    
    private void broadcast(Session session, String what) {
        System.out.println(what);
        for(Session s : session.getOpenSessions()) {
            if(s.getUserProperties().get("chatroom").equals(chatroom)) {
                s.getAsyncRemote().sendText(what);
            }
        }
    }
    
}
