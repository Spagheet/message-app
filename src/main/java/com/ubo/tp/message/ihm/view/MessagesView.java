package com.ubo.tp.message.ihm.view;

import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.ihm.view.panel.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesView extends JPanel {
    protected ComposeMessagePanel composeMessagePanel;
    protected FilterPanel filterPanel;
    protected DisplayMessagesPanel displayMessagesPanel;
    protected List<Message> messageList;
    protected JScrollPane scrollPane;
    public MessagesView(AppButton filterButton, AppButton sendButton, AppButton backButton) {
        this.messageList = new ArrayList<>();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.composeMessagePanel = new ComposeMessagePanel(sendButton);

        filterPanel = new FilterPanel(filterButton);

        displayMessagesPanel = new DisplayMessagesPanel();
        scrollPane = new JScrollPane(displayMessagesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        //scrollPane.getViewport().setAlignmentX(Component.LEFT_ALIGNMENT);
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(filterPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(composeMessagePanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);
    }

    public String getMessage() {
        return this.composeMessagePanel.getMessage();
    }
    public void emptyMessage() {
        this.composeMessagePanel.emptyMessage();
    }
    public void updateMessages(List<Message> messages) {
        this.displayMessagesPanel.updateMessages(messages);
        this.repaint();
    }
    public String getFilter() {
        return this.filterPanel.getFilter();
    }
}
