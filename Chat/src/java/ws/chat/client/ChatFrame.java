/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.chat.client;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Hany
 */
public class ChatFrame extends JFrame implements ActionListener {
    
    private JTextField input;
    private JTextArea messages;
    private JButton button;
    ChatClient chatClient;

    public ChatFrame(ChatClient chatClient) throws HeadlessException {
        this.chatClient = chatClient;
        input = new JTextField(25);
        messages = new JTextArea(10, 35);
        button = new JButton("Send");
        button.addActionListener(this);
        
        this.setLayout(new FlowLayout());
        this.add(input);
        this.add(button);
        this.add(messages);
        
        this.setSize(400, 250);
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
//        System.out.println("closed");
//                ChatFrame.this.chatClient.quit();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chatClient.send(input.getText());
    }

    public void addMessage(String message) {
        messages.append(message + "\n");
    }
    

}
