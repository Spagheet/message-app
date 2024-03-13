package main.java.com.ubo.tp.message.ihm.view.panel;

import main.java.com.ubo.tp.message.ihm.component.ButtonAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppButton extends JPanel {
    ButtonAction buttonAction;
    public AppButton(String label, Dimension dimension, ButtonAction buttonAction) {
        this.buttonAction = buttonAction;
        JButton button = new JButton(label);
        button.setPreferredSize(dimension);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AppButton.this.buttonAction.run();
            }
        });
        this.add(button);
    }
}
