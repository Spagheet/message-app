package main.java.com.ubo.tp.message.ihm.controler;

import main.java.com.ubo.tp.message.datamodel.User;

public interface UserListControllerObserver extends MenuOptionControllerObserver {
    void userFilter(String filter);

    void unsubscribeEvent(User user);
    void subscribeEvent(User user);
}
