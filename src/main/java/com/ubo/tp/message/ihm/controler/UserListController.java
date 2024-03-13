package main.java.com.ubo.tp.message.ihm.controler;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.datamodel.UserFilterObserver;
import main.java.com.ubo.tp.message.ihm.component.ButtonAction;
import main.java.com.ubo.tp.message.ihm.view.UserListView;
import main.java.com.ubo.tp.message.ihm.view.panel.AppButton;

import java.awt.*;
import java.util.List;

public class UserListController extends MenuOptionController<UserListControllerObserver> implements UserFilterObserver {
    User user;
    AppButton subsribeButton;
    AppButton unsubscribeButton;
    AppButton filterUsersButton;
    UserListView userListView;
    Dimension subscribeButtonSize;
    List<User> userList;
    public UserListController(UserListControllerObserver observer){
        super(observer);
        System.out.println(observer + " " + this.observer);
        this.subscribeButtonSize = new Dimension(150, 50);

        this.filterUsersButton = new AppButton("Search", buttonSize, new ButtonAction() {
            @Override
            public void run() {
                UserListController.this.userFilterEvent();
            }
        });
        this.userListView = new UserListView(filterUsersButton, backButton);
    }

    protected void userFilterEvent() {
        String filter = this.userListView.getUserFilter();
        this.observer.userFilter(filter);
    }
    protected void unsubscribeEvent(User user) {
        this.observer.unsubscribeEvent(user);
    }

    protected void subscribeEvent(User user) {
        // null pointer exception
        this.observer.subscribeEvent(user);
    }
    public UserListView getUserListView() {
        return this.userListView;
    }

    @Override
    public void notifyFilterUpdate(List<User> userList) {
        this.userListView.clearUsers();
        this.userList = userList;
        for(User user : userList) {
            subsribeButton = new AppButton("Subscribe", subscribeButtonSize, new ButtonAction() {
                @Override
                public void run() {
                    UserListController.this.subscribeEvent(user);
                }
            });
            this.unsubscribeButton = new AppButton("Unsubscribe", subscribeButtonSize, new ButtonAction() {
                @Override
                public void run() {
                    UserListController.this.unsubscribeEvent(user);
                }
            });
            this.userListView.addUser(user, subsribeButton, unsubscribeButton);
        }
    }

    public void sessionUpdate(User user) {
        this.userListView.sessionUpdate(user);
        this.userListView.revalidate();
        this.userListView.repaint();
    }
}
