/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chat.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author Hany
 */
@ClientEndpoint
public class ChatClient {
    
    private ChatFrame chatFrame;
    private Session session;

    public ChatClient() {
        chatFrame = new ChatFrame(this);
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to " + session.getBasicRemote());
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
        chatFrame.addMessage(message);
    }
    
    public void send(String message) {
        session.getAsyncRemote().sendText(message);
    }
    
    public void close() {
        try {
            session.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
