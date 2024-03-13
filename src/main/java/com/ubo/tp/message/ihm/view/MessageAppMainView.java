package main.java.com.ubo.tp.message.ihm.view;

import main.java.com.ubo.tp.message.ihm.view.panel.ControlableContentPanel;
import main.java.com.ubo.tp.message.ihm.view.panel.MessageAppMenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de la vue principale de l'application.
 */
public class MessageAppMainView {
    protected JFrame mFrame;
    protected ControlableContentPanel controlableContentPanel;

    public MessageAppMainView(ControlableContentPanel controlableContentPanel) {
        this.controlableContentPanel = controlableContentPanel;
    }
    public void initGUI() {
        // Création de la fenetre principale
        mFrame = new JFrame("MessageApp");
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/logo_20.png"));
        mFrame.setSize(1000, 800);

        mFrame.setIconImage(appIcon.getImage());
        mFrame.setLayout(new GridBagLayout());
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mFrame.setJMenuBar(new MessageAppMenuBar());
        this.mFrame.add(controlableContentPanel);
    }
    public void showGUI() {
        // Init auto de l'IHM au cas ou ;)
        if (mFrame == null) {
            this.initGUI();
        }
        // Affichage dans l'EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Custom de l'affichage
                JFrame frame = MessageAppMainView.this.mFrame;
                Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((screenSize.width - frame.getWidth()) / 6,
                        (screenSize.height - frame.getHeight()) / 4);

                // Affichage
                MessageAppMainView.this.mFrame.setVisible(true);
                //MessageAppMainView.this.mFrame.pack();
            }
        });
    }
}
