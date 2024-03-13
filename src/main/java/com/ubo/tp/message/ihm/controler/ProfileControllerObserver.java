package com.ubo.tp.message.ihm.controler;

import com.ubo.tp.message.datamodel.User;

public interface ProfileControllerObserver extends MenuOptionControllerObserver {
    public void changeAvatar(String path);

    void unsubscribeEvent(User user);
    void subscribeEvent(User user);
}
