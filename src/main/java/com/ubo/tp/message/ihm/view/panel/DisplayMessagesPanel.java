package com.ubo.tp.message.ihm.view.panel;

import com.ubo.tp.message.datamodel.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayMessagesPanel extends JPanel {
    List<Message> messageList;
    GridBagConstraints gbc;
    public DisplayMessagesPanel() {
        this.messageList = new ArrayList<>();
        this.setLayout(new GridBagLayout());
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        this.setVisible(true);
    }
    public void updateMessages(List<Message> messages) {
        this.messageList = messages;
        this.displayMessages();
        System.out.println("messages view updated messages " + messages);
    }
    public void displayMessages() {
        this.removeAll();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        for(Message m : messageList) {
            this.add(new MessagePanel(m), gbc);
            //this.add(new MessagePanel(m));
            gbc.gridy++;
            System.out.println("added message to message display");
        }
    }
}
