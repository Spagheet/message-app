package com.ubo.tp.message.ihm.view.panel;

import com.ubo.tp.message.ihm.component.ButtonAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppSubmitButton extends JPanel {
    ButtonAction buttonAction;
    public AppSubmitButton(String label, Dimension dimension, ButtonAction buttonAction) {
        this.buttonAction = buttonAction;
        JButton button = new JButton(label);
        button.setPreferredSize(dimension);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AppSubmitButton.this.buttonAction.run();
            }
        });
        this.add(button);
    }
}
