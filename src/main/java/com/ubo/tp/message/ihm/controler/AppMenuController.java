package com.ubo.tp.message.ihm.controler;

import com.ubo.tp.message.datamodel.SessionObserver;
import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.ihm.component.ButtonAction;
import com.ubo.tp.message.ihm.view.AppMenuView;
import com.ubo.tp.message.ihm.view.panel.AppButton;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class AppMenuController implements SessionObserver {
    protected AppMenuControllerObserver observer;
    protected Dimension buttonSize;
    Set<AppButton> appButtons;
    Set<AppButton> loggedInButtons;
    protected AppButton loginButton;
    protected AppButton registerButton;
    protected AppButton chatButton;
    protected AppButton usersButton;
    protected AppButton profileButton;
    protected AppButton logoutButton;
    protected AppMenuView appMenuView;
    public AppMenuController(AppMenuControllerObserver observer) {
        this.observer = observer;
        buttonSize = new Dimension(100, 50);

        loginButton = new AppButton("Login", buttonSize, new ButtonAction() {
            @Override
            public void run() {
                AppMenuController.this.loginButtonEvent();
            }
        });
        logoutButton = new AppButton("Logout", buttonSize, new ButtonAction() {
            @Override
            public void run() {
                AppMenuController.this.logoutButtonEvent();
            }
        });
        registerButton = new AppButton  ("Register", buttonSize, new ButtonAction() {
            @Override
            public  void run() {
                AppMenuController.this.registerButtonEvent();
            }
        });
        chatButton = new AppButton  ("Chat", buttonSize, new ButtonAction() {
            @Override
            public  void run() {
                AppMenuController.this.chatButtonEvent();
            }
        });
        usersButton = new AppButton("Users", buttonSize, new ButtonAction() {
            @Override
            public void run() {
                AppMenuController.this.usersButtonEvent();
            }
        });
        profileButton = new AppButton("Profile", buttonSize, new ButtonAction() {
            @Override
            public void run() {
                AppMenuController.this.profileButtonEvent();
            }
        });

        appButtons = new HashSet<AppButton>();
        appButtons.add(loginButton);
        appButtons.add(registerButton);

        loggedInButtons = new HashSet<>();
        loggedInButtons.add(chatButton);
        loggedInButtons.add(usersButton);
        loggedInButtons.add(profileButton);
        loggedInButtons.add(logoutButton);

        appMenuView = new AppMenuView(appButtons);
    }

    private void logoutButtonEvent() {
        this.observer.logout();
    }

    private void profileButtonEvent() {
        this.observer.setProfileView();
    }

    private void usersButtonEvent() {
        this.observer.setUsersView();
    }

    public AppMenuView getAppMenuView() {
        return this.appMenuView;
    }

    private void registerButtonEvent() {
        this.observer.setRegisterView();
    }

    private void loginButtonEvent() {
        this.observer.setLoginView();
    }
    private void chatButtonEvent(){this.observer.setMessagesView();}

    @Override
    public void sessionUpdate(User user) {
        if(user != null) {
            this.appButtons.addAll(loggedInButtons);
        } else {
            this.appButtons.removeAll(loggedInButtons);
        }
        this.appMenuView.sessionUpdate(user, appButtons);
    }
}
