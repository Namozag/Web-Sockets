/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chat.client;

import java.io.IOException;
import java.net.URI;
import javax.swing.JOptionPane;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author Hany
 */
public class Main {

    public static void main(String[] args) {
        
        String chatroom = JOptionPane.showInputDialog("Enter Chat room");
        String user = JOptionPane.showInputDialog("Enter your name");
        
        String uri = "ws://localhost:8080/chat/speak/" + chatroom + "?user=" + user;
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        Session session;
        try {
            session = container.connectToServer(ChatClient.class, URI.create(uri));
        } catch (DeploymentException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
}
