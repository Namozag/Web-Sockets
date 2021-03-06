/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.echo.server;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Hany
 */
@ServerEndpoint("/echows")
public class EchoEndPoint {
    
    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) {
        System.out.println("EchoEndPoint::open " + session.getId());
    }
    
    @OnMessage
    public String onMessage(Session session, String message) {
        System.out.println("EchoEndPoint::message : " + message);
//        session.getAsyncRemote().sendText("Hellooooo " + message);
        return "Hello " + message;
    }

    @OnError
    public void error(Session session, Throwable error) {
        System.out.println("EchoEndPoint::error " + error);
    }
    
    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.println("EchoEndPoint::close " + reason.getCloseCode() + " | " + reason.getReasonPhrase());
    }
    
    
}
