package main.java.com.ubo.tp.message.ihm.view;

import main.java.com.ubo.tp.message.ihm.view.panel.AppButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterAccountView extends JPanel {
    protected JTextField usernameField;
    protected JTextField tagField;
    protected JPasswordField passwordField;
    protected JTextField avatarField;

    protected JLabel registeredLabel;
    protected Set<JTextField> fields;
    public RegisterAccountView(List<AppButton> buttonList) {
        setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Username:");
        JLabel tagLabel = new JLabel("Tag");
        JLabel passwordLabel = new JLabel("Password");
        JLabel avatarLabel = new JLabel("Avatar");

        registeredLabel = new JLabel("User registered");
        tagField = new JTextField();
        tagField.setColumns(15);
        passwordField = new JPasswordField();
        passwordField.setColumns(15);
        usernameField = new JTextField();
        usernameField.setColumns(15);
        avatarField = new JTextField();
        avatarField.setColumns(15);

        fields = new HashSet<>();
        fields.add(tagField);
        fields.add(passwordField);
        fields.add(usernameField);
        fields.add(avatarField);

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

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(avatarLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(avatarField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        add(registeredLabel, gbc);
        registeredLabel.setVisible(false); // Initially hidden

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonList.get(0), gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonList.get(1), gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonList.get(2), gbc);
    }
    public void registerSuccessful() {
        this.registeredLabel.setText("User registered");
        this.registeredLabel.setVisible(true);
        this.emptyFields();
    }

    public void registerUnsuccessful() {
        this.registeredLabel.setText("User exists already");
        this.registeredLabel.setVisible(true);
        this.tagField.setText("");
    }
    public void badFields() {
        this.registeredLabel.setText("Bad fields");
        this.registeredLabel.setVisible(true);
    }
    public String getUsername() {
        return this.usernameField.getText();
    }
    public String getTag() {
        return this.tagField.getText();
    }
    public String getPassword() {
        return String.valueOf(this.passwordField.getPassword());
    }
    public String getAvatar() {
        return this.avatarField.getText();
    }
    public void setAvatarField(String directory) {
        this.avatarField.setText(directory);
    }
    protected void emptyFields() {
        for(JTextField field : fields) {
            field.setText("");
        }
    }
}
