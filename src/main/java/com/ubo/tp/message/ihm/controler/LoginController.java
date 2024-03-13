package com.ubo.tp.message.ihm.controler;

import com.ubo.tp.message.ihm.component.ButtonAction;
import com.ubo.tp.message.ihm.view.LoginView;
import com.ubo.tp.message.ihm.view.panel.AppButton;

import java.util.HashSet;
import java.util.Set;

public class LoginController extends AbstractMenuOptionController<LoginControllerObserver> {
   LoginView loginView;
   AppButton loginButton;
   Set<AppButton> buttonSet;
    public LoginController(LoginControllerObserver observer) {
        super(observer);
        loginButton = new AppButton("Login", buttonSize, new ButtonAction() {
            @Override
            public void run() {
                LoginController.this.notifyLoginRequestEvent();
            }
        });

        buttonSet = new HashSet<AppButton>();
        buttonSet.add(backButton);
        buttonSet.add(loginButton);

        loginView = new LoginView(buttonSet);
    }

    public LoginView getLoginView() {
        return this.loginView;
    }

    public boolean notifyLoginRequestEvent() {
        System.out.println("login request");
        return this.observer.loginRequestEvent(this.getLoginView().getUsername(), this.getLoginView().getPassword());
    }
}
