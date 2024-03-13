package com.ubo.tp.message.ihm.view.panel;

import javax.swing.*;
import java.awt.*;

public class ControlableContentPanel extends JPanel {
    JPanel currentPanel;
    public ControlableContentPanel() {
    }
    public void initPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
        this.add(this.currentPanel);
    }

    public void setCurrentPanel(JPanel currentPanel) {
        this.remove(this.currentPanel);
        this.initPanel(currentPanel);
    }
}
