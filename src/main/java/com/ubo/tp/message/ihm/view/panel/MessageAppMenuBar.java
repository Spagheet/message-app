package main.java.com.ubo.tp.message.ihm.view.panel;

import main.java.com.ubo.tp.message.ihm.view.MessageAppMainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MessageAppMenuBar extends JMenuBar {
    public MessageAppMenuBar() {
        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/images/exitIcon_20.png"));

        JMenu fileMenu = new JMenu("File");

        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.setIcon(exitIcon);
        fileMenu.add(aboutItem);
        fileMenu.add(exitItem);
        this.add(fileMenu);

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(
                        null,
                        "Information about this app goes here",
                        "About this app",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //MessageAppMenuBar.this.dispatchEvent(new WindowEvent(MessageAppMenuBar.this, WindowEvent.WINDOW_CLOSING));
                System.exit(0);
            }
        });
    }
}
