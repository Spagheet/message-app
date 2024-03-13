package com.ubo.tp.message.ihm.controler;

import java.util.Map;

public interface RegisterControllerObserver extends MenuOptionControllerObserver {
    public boolean registerRequestEvent(Map<String, String> fields);
}
