package main.java.com.ubo.tp.message.ihm.view.panel;

import javax.swing.*;
import java.awt.*;

public class ComposeMessagePanel extends JPanel {
    protected JTextField messageField;
    GridBagConstraints gbc;
    public ComposeMessagePanel(AppButton sendButton) {
        messageField = new JTextField();
        messageField.setColumns(30);

        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(messageField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(sendButton, gbc);
    }

    public String getMessage() {
        return this.messageField.getText();
    }
    public void emptyMessage() {
        this.messageField.setText("");
    }
}
