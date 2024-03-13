package main.java.com.ubo.tp.message.ihm.view.panel;

import main.java.com.ubo.tp.message.core.util.ImagePanelGenerator;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class MessagePanel extends JPanel {
    Message message;
    User user;
    JLabel userLabel;
    JLabel messageContent;
    ImagePanel avatar;
    GridBagConstraints gbc;
    ImagePanelGenerator imagePanelGenerator;
    public MessagePanel(Message m) {
        this.imagePanelGenerator = new ImagePanelGenerator();
        this.user = m.getSender();
        this.message = m;
        this.userLabel = new JLabel();
        this.messageContent = new JLabel();
        System.out.println(this.user.getAvatarPath());
        this.avatar = imagePanelGenerator.getImagePanelFromUrl(this.user.getAvatarPath(), new Dimension(50, 50));

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

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(messageContent, gbc);
        this.setVisible(true);
        updateMessage();
    }
    protected void updateMessage() {
        this.userLabel.setText(this.user.getName() + " : ");
        this.messageContent.setText(this.message.getText());
        this.repaint();
    }
}
