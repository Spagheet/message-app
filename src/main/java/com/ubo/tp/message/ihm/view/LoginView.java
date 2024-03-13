package com.ubo.tp.message.ihm.view;

import com.ubo.tp.message.ihm.component.ButtonAction;
import com.ubo.tp.message.ihm.view.panel.AppButton;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class LoginView extends JPanel {
    protected JTextField tagField;
    protected JPasswordField passwordField;

    protected Set<AppButton> buttonSet;

    public LoginView(Set<AppButton> buttonSet) {
        this.buttonSet = buttonSet;
        setLayout(new GridBagLayout());

        JLabel tagLabel = new JLabel("User tag:");
        JLabel passwordLabel = new JLabel("Password:");

        tagField = new JTextField();
        tagField.setColumns(15);
        passwordField = new JPasswordField();
        passwordField.setColumns(15);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(tagLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(tagField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        for(AppButton button : buttonSet) {
            add(button);
        }
    }
    public String getUsername() {
        return this.tagField.getText();
    }
    public String getPassword(){return String.valueOf(this.passwordField.getPassword());}
}
