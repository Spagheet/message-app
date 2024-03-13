package main.java.com.ubo.tp.message.ihm.controler;

public interface MessagesControllerObserver extends MenuOptionControllerObserver {
    public void sendMessage(String message);

    void setFilter(String filter);
}
