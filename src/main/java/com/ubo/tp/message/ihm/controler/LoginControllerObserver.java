package com.ubo.tp.message.ihm.controler;

public interface LoginControllerObserver extends MenuOptionControllerObserver {
    public boolean loginRequestEvent(String tag, String password);
}
