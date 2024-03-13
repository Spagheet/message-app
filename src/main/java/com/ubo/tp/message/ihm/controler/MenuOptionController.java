package main.java.com.ubo.tp.message.ihm.controler;

import main.java.com.ubo.tp.message.ihm.component.ButtonAction;
import main.java.com.ubo.tp.message.ihm.view.panel.AppButton;

import java.awt.*;

public abstract class MenuOptionController<O extends MenuOptionControllerObserver> {
    O observer;
    public MenuOptionController(O observer) {
        this.observer = observer;
    }
    Dimension buttonSize = new Dimension(100, 50);
    AppButton backButton = new AppButton("Back", buttonSize, new ButtonAction() {
        @Override
        public void run() {
            MenuOptionController.this.goBackToMenu();
        }
    });
    protected void goBackToMenu() {
        this.observer.goBackToMenu();
    }
}
