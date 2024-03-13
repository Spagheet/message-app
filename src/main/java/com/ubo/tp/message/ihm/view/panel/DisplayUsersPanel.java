package com.ubo.tp.message.ihm.view.panel;

import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayUsersPanel extends JPanel {
    GridBagConstraints gbc;
    Map<String, UserPanel> panelMap;
    public DisplayUsersPanel() {
        this.panelMap = new HashMap<String, UserPanel>();
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        this.setVisible(true);
    }
    public void resetPanel() {
        this.gbc.gridy = 0;
        this.removeAll();
        this.panelMap = new HashMap<String, UserPanel>();
    }
    public void addUserPanel(User user, AppButton subsribeButton, AppButton unsubscribeButton) {
        UserPanel userPanel = new UserPanel(user, subsribeButton, unsubscribeButton);
        this.panelMap.put(user.getUserTag(), userPanel);
        this.add(userPanel, this.gbc);
        this.gbc.gridy++;
        System.out.println("added user to user display");
    }
    public void sessionUpdate(User user) {
        if(user != null) {
            for(Map.Entry<String, UserPanel> entry : panelMap.entrySet()) {
                UserPanel panelToUpdate = entry.getValue();
                System.out.println("session update in display users\nuser is following : "+user.isFollowing(panelToUpdate.getUser()));
                if(user.isFollowing(panelToUpdate.getUser())) {
                    panelToUpdate.setSubscribed();
                } else {
                    panelToUpdate.setUnsubscribed();
                }
            }
        }
    }
}
