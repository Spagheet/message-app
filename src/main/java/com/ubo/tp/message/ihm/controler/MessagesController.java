package main.java.com.ubo.tp.message.ihm.controler;

import main.java.com.ubo.tp.message.datamodel.FilterObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.ihm.component.ButtonAction;
import main.java.com.ubo.tp.message.ihm.view.MessagesView;
import main.java.com.ubo.tp.message.ihm.view.panel.AppButton;

import java.awt.*;
import java.util.List;

public class MessagesController extends MenuOptionController<MessagesControllerObserver> implements FilterObserver {
    protected MessagesView messagesView;
    protected AppButton filterButton;
    protected AppButton sendMessage;
    public MessagesController(MessagesControllerObserver observer) {
        super(observer);
        filterButton = new AppButton("Search", buttonSize, new ButtonAction() {
            @Override
            public void run() {
                MessagesController.this.filterRequestEvent();
            }
        });
        sendMessage = new AppButton("Send", buttonSize, new ButtonAction() {
            @Override
            public void run() {
                MessagesController.this.sendMessageEvent();
            }
        });
        this.messagesView = new MessagesView(filterButton, sendMessage, backButton);
    }
    protected void filterRequestEvent() {
        String filter = this.messagesView.getFilter();
        this.observer.setFilter(filter);
        System.out.println("filter button press");
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
