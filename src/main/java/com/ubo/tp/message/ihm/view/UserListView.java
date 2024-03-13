package com.ubo.tp.message.ihm.view;

import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.view.panel.AppButton;
import com.ubo.tp.message.ihm.view.panel.DisplayUsersPanel;
import com.ubo.tp.message.ihm.view.panel.FilterPanel;

import javax.swing.*;
import java.awt.*;

public class UserListView extends JPanel {
    DisplayUsersPanel displayUsersPanel;
    FilterPanel filterPanel;
    GridBagConstraints gbc;
    public UserListView(AppButton filterUsersButton, AppButton backButton) {
        this.filterPanel = new FilterPanel(filterUsersButton);
        this.displayUsersPanel = new DisplayUsersPanel();
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(filterPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(displayUsersPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(backButton, gbc);
    }
    public String getUserFilter() {
        return this.filterPanel.getFilter();
    }
    public void clearUsers() {
        this.displayUsersPanel.resetPanel();
    }

    public void addUser(User user, AppButton subsribeButton, AppButton unsubscribeButton) {
        this.displayUsersPanel.addUserPanel(user, subsribeButton, unsubscribeButton);
    }

    public void sessionUpdate(User user) {
        this.displayUsersPanel.sessionUpdate(user);
    }
}
