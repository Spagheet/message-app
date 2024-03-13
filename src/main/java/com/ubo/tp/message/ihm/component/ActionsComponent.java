package main.java.com.ubo.tp.message.ihm.component;

import main.java.com.ubo.tp.message.datamodel.SessionObserver;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.controler.*;
import main.java.com.ubo.tp.message.ihm.view.panel.ControlableContentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ActionsComponent implements
        LoginControllerObserver,
        AppMenuControllerObserver,
        RegisterControllerObserver,
        MessagesControllerObserver,
        UserListControllerObserver,
        ProfileControllerObserver,
        SessionObserver {
    protected LoginController loginController;
    protected RegisterController registerController;
    protected AppMenuController appMenuController;
    protected MessagesController messagesController;
    protected UserListController userListController;
    protected ProfileController profileController;
    protected ControlableContentPanel controlableContentPanel;
    protected ActionsComponentObserver observer;



    protected Dimension buttonSize;
    public ActionsComponent(ActionsComponentObserver observer, ControlableContentPanel controlableContentPanel) {
        this.observer = observer;

        loginController = new LoginController(this);

        registerController = new RegisterController(this);

        appMenuController = new AppMenuController(this);
        messagesController = new MessagesController(this);
        userListController = new UserListController(this);
        profileController = new ProfileController(this);

        this.controlableContentPanel = controlableContentPanel;
        this.controlableContentPanel.initPanel(appMenuController.getAppMenuView());

    }

    protected void setControlableContentPanel(JPanel newPanel) {
        this.controlableContentPanel.setCurrentPanel(newPanel);
        this.controlableContentPanel.revalidate();
        this.controlableContentPanel.repaint();
    }

    public AppMenuController getAppMenuController() {
        return this.appMenuController;
    }
    public MessagesController getMessagesController(){return this.messagesController;}
    public UserListController getUsersController(){return this.userListController;}

    @Override
    public boolean loginRequestEvent(String tag, String password) {
        return this.observer.loginUser(tag, password);
    }

    @Override
    public boolean registerRequestEvent(Map<String, String> fields) {
        return this.observer.registerUser(fields);
    }

    @Override
    public void goBackToMenu() {
        this.setControlableContentPanel(this.appMenuController.getAppMenuView());
    }

    @Override
    public void sendMessage(String message) {
        this.observer.sendMessage(message);
    }

    @Override
    public void setFilter(String filter) {
        this.observer.setFilter(filter);
    }

    @Override
    public void setLoginView() {
        this.setControlableContentPanel(this.loginController.getLoginView());
    }

    @Override
    public void setRegisterView() {
        this.setControlableContentPanel(this.registerController.getRegisterView());
    }

    @Override
    public void setMessagesView() {
        this.setControlableContentPanel(this.messagesController.getMessagesView());
    }

    @Override
    public void setUsersView() {
        this.setControlableContentPanel(this.userListController.getUserListView());
    }

    @Override
    public void setProfileView() {
        this.setControlableContentPanel(this.profileController.getProfileView());
    }

    @Override
    public void logout() {
        this.observer.logout();
        this.goBackToMenu();
    }

    @Override
    public void sessionUpdate(User user) {
        //this.setControlableContentPanel(this.appMenuController.getAppMenuView());
        this.appMenuController.sessionUpdate(user);
        this.userListController.sessionUpdate(user);
        this.profileController.sessionUpdate(user);
    }

    @Override
    public void userFilter(String filter) {
        this.observer.setUserFilter(filter);
    }

    @Override
    public void unsubscribeEvent(User user) {
        this.observer.unsubscribeEvent(user);
    }

    @Override
    public void subscribeEvent(User user) {
        this.observer.subscribeEvent(user);
    }

    @Override
    public void changeAvatar(String path) {
        this.observer.changeAvatar(path);
    }
}
