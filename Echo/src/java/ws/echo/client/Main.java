/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.echo.client;

import java.io.IOException;
import java.net.URI;
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
        String uri = "ws://localhost:8080/echo/echows";
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            Session session = container.connectToServer(EchoClient.class, URI.create(uri));
            session.getBasicRemote().sendText("red");
            session.getBasicRemote().sendText("green");
            session.getBasicRemote().sendText("blue");
            
            // wait for the incoming replies
            try {Thread.sleep(1000);} catch (InterruptedException ex) {}
            
//            session.close();
        } catch (DeploymentException | IOException ex) {
            ex.printStackTrace();
        }

    }
    
}
