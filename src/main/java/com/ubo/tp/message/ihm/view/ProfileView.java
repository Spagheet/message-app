package main.java.com.ubo.tp.message.ihm.view;

import main.java.com.ubo.tp.message.core.util.ImagePanelGenerator;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.view.panel.AppButton;
import main.java.com.ubo.tp.message.ihm.view.panel.DisplayUsersPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;

public class ProfileView extends JPanel {
    User user;
    JLabel name;
    JLabel tag;
    JPanel avatar;
    ImagePanelGenerator imagePanelGenerator;
    GridBagConstraints gbc;
    MouseAdapter folderSelectButton;
    DisplayUsersPanel followersPanel;
    public ProfileView(AppButton backButton, MouseAdapter folderSelectButton) {
        this.folderSelectButton = folderSelectButton;
        this.imagePanelGenerator = new ImagePanelGenerator();
        this.name = new JLabel();
        this.tag = new JLabel();
        this.avatar = new JPanel();
        this.avatar.addMouseListener(this.folderSelectButton);
        this.followersPanel = new DisplayUsersPanel();

        this.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(avatar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(name, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(tag, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(followersPanel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(backButton, gbc);
    }
    public void sessionUpdate(User user) {
        if(user != null) {
            this.user = user;
            this.name.setText("Username : " + user.getName());
            this.tag.setText("Tag : " + user.getUserTag());
            this.remove(avatar);
            this.avatar = imagePanelGenerator.getImagePanelFromUrl(user.getAvatarPath(), new Dimension(50, 50));
            this.avatar.addMouseListener(this.folderSelectButton);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            this.add(avatar, gbc);
        }
    }
}
