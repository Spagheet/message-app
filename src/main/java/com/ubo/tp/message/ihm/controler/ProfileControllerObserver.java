package main.java.com.ubo.tp.message.ihm.controler;

import main.java.com.ubo.tp.message.datamodel.User;

public interface ProfileControllerObserver extends MenuOptionControllerObserver {
    public void changeAvatar(String path);

    void unsubscribeEvent(User user);
    void subscribeEvent(User user);
}
