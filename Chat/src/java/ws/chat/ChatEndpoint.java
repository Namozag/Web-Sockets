/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chat;

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
@ServerEndpoint("/speak/{chatroom}/{user}")
public class ChatEndpoint {
    
    private String user;
    private String chatroom;
    
    @OnOpen
    public void onOpen(Session session,
            @PathParam("chatroom") String chatroom,
            @PathParam("user") String user) {
        
        this.user = user;
        this.chatroom = chatroom;
        System.out.println("OPEN " + chatroom + "/" + user + " @ " + session.getId());
        session.getUserProperties().put("chatroom", chatroom);
        
        broadcast(session, user + " has joined the conversation");
    }
    
    @OnMessage
    public void onMessage(Session session, String message) {
        broadcast(session, user + " : " + message);
    }
    
    @OnClose
    public void onClose(Session session) {
        broadcast(session, user + " has left the conversation");
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
