package com.ubo.tp.message.ihm.controler;

import com.ubo.tp.message.datamodel.User;
import com.ubo.tp.message.datamodel.UserFilterObserver;
import com.ubo.tp.message.ihm.component.ButtonAction;
import com.ubo.tp.message.ihm.view.UserListView;
import com.ubo.tp.message.ihm.view.panel.AppButton;

import java.awt.*;
import java.util.List;

public class UserListController extends AbstractMenuOptionController<UserListControllerObserver> implements UserFilterObserver {
    User user;
    AppButton subsribeButton;
    AppButton unsubscribeButton;
    AppButton filterUsersButton;
    UserListView userListView;
    Dimension subscribeButtonSize;
    List<User> userList;
    public UserListController(UserListControllerObserver observer){
        super(observer);
        this.subscribeButtonSize = new Dimension(150, 50);

        this.filterUsersButton = new AppButton("Search", buttonSize, UserListController.this::userFilterEvent);
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
        for(User userListuser : userList) {
            subsribeButton = new AppButton("Subscribe", subscribeButtonSize, () -> UserListController.this.subscribeEvent(userListuser));
            this.unsubscribeButton = new AppButton("Unsubscribe", subscribeButtonSize, () -> UserListController.this.unsubscribeEvent(userListuser));
            this.userListView.addUser(userListuser, subsribeButton, unsubscribeButton);
        }
    }

    public void sessionUpdate(User user) {
        this.userListView.sessionUpdate(user);
        this.userListView.revalidate();
        this.userListView.repaint();
    }
}
