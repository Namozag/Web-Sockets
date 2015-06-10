/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.echo.client;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author Hany
 */
@ClientEndpoint
public class EchoClient {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to " + session.getRequestURI());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
//        session.getAsyncRemote().sendText("Hellooooo " + message);
//        return "Hello " + message;
    }
}
