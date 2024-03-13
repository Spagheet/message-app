package com.ubo.tp.message.ihm.view.panel;

import javax.swing.*;

public class FilterPanel extends JPanel {
    JTextField filter;
    public FilterPanel(AppButton filterButton) {
        filter = new JTextField();
        filter.setColumns(15);
        this.add(filter);
        this.add(filterButton);
    }

    public String getFilter() {
        return this.filter.getText();
    }
}
