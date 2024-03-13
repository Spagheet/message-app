package com.ubo.tp.message.ihm.controler;

import com.ubo.tp.message.datamodel.User;

public interface UserListControllerObserver extends MenuOptionControllerObserver {
    void userFilter(String filter);

    void unsubscribeEvent(User user);
    void subscribeEvent(User user);
}
