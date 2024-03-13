package com.ubo.tp.message.ihm.component;

import com.ubo.tp.message.datamodel.User;

import java.util.Map;

public interface ActionsComponentObserver {
    public boolean registerUser(Map<String, String> fields);
    public boolean loginUser(String tag, String password);
    public void sendMessage(String message);

    void setFilter(String filter);

    void setUserFilter(String filter);

    void unsubscribeEvent(User user);

    void subscribeEvent(User user);

    void changeAvatar(String path);

    void logout();
}
