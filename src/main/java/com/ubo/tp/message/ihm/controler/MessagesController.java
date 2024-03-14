package com.ubo.tp.message.ihm.controler;

import com.ubo.tp.message.datamodel.FilterObserver;
import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.ihm.component.ButtonAction;
import com.ubo.tp.message.ihm.view.MessagesView;
import com.ubo.tp.message.ihm.view.panel.AppButton;

import java.util.List;

public class MessagesController extends AbstractMenuOptionController<MessagesControllerObserver> implements FilterObserver {
    protected MessagesView messagesView;
    protected AppButton filterButton;
    protected AppButton sendMessage;
    public MessagesController(MessagesControllerObserver observer) {
        super(observer);
        filterButton = new AppButton("Search", buttonSize, MessagesController.this::filterRequestEvent);
        sendMessage = new AppButton("Send", buttonSize, MessagesController.this::sendMessageEvent);
        this.messagesView = new MessagesView(filterButton, sendMessage, backButton);
    }
    protected void filterRequestEvent() {
        String filter = this.messagesView.getFilter();
        this.observer.setFilter(filter);
    }
    protected void sendMessageEvent() {
        String message = this.messagesView.getMessage();
        this.observer.sendMessage(message);
        this.emptyMessage();
    }
    public MessagesView getMessagesView() {
        return this.messagesView;
    }
    public void emptyMessage() {
        this.messagesView.emptyMessage();
    }

    @Override
    public void notifyFilterUpdate(List<Message> messages) {
        this.messagesView.updateMessages(messages);
        this.messagesView.revalidate();
        this.messagesView.repaint();
    }
}
