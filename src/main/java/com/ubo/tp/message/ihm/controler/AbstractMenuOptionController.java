package com.ubo.tp.message.ihm.controler;

import com.ubo.tp.message.ihm.component.ButtonAction;
import com.ubo.tp.message.ihm.view.panel.AppButton;

import java.awt.*;

public abstract class AbstractMenuOptionController<O extends MenuOptionControllerObserver> {
    O observer;
    public AbstractMenuOptionController(O observer) {
        this.observer = observer;
    }
    Dimension buttonSize = new Dimension(100, 50);
    AppButton backButton = new AppButton("Back", buttonSize, new ButtonAction() {
        @Override
        public void run() {
            AbstractMenuOptionController.this.goBackToMenu();
        }
    });
    protected void goBackToMenu() {
        this.observer.goBackToMenu();
    }
}
