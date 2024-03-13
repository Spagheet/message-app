package main.java.com.ubo.tp.message.ihm.view.panel;

import main.java.com.ubo.tp.message.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UserPanel extends JPanel {
    User user;
    JLabel userLabel;
    ImagePanel avatar;
    GridBagConstraints gbc;
    AppButton subscribeButton;
    AppButton unsubscribeButton;
    public UserPanel(User u, AppButton subsribeButton, AppButton unsubscribeButton) {
        this.user = u;
        this.userLabel = new JLabel();
        this.subscribeButton = subsribeButton;
        this.unsubscribeButton = unsubscribeButton;

        File avatarFile = new File(this.user.getAvatarPath());
        Dimension avatarDimension = new Dimension(50, 50);
        this.avatar = new ImagePanel(avatarFile, avatarDimension);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(avatar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(userLabel, gbc);

       this.setUnsubscribed();

        this.setVisible(true);
        this.updateUser();
    }
    public void updateUser() {
        this.userLabel.setText(user.getName());
    }
    public void setSubscribed() {
        this.remove(subscribeButton);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(unsubscribeButton, gbc);
    }
    public void setUnsubscribed() {
        this.remove(unsubscribeButton);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(subscribeButton, gbc);
    }
    public User getUser() {
        return this.user;
    }
}
