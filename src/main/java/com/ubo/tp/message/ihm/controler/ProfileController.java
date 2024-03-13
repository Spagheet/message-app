package main.java.com.ubo.tp.message.ihm.controler;

import main.java.com.ubo.tp.message.datamodel.*;
import main.java.com.ubo.tp.message.ihm.component.ButtonAction;
import main.java.com.ubo.tp.message.ihm.view.FileSelectionView;
import main.java.com.ubo.tp.message.ihm.view.ProfileView;
import main.java.com.ubo.tp.message.ihm.view.UserListView;
import main.java.com.ubo.tp.message.ihm.view.panel.AppButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ProfileController extends MenuOptionController<ProfileControllerObserver> implements SessionObserver {
    ProfileView profileView;
    MouseAdapter avatarClick;
    AppButton subscribeButton;
    AppButton unsubscribeButton;
    Dimension subscribeButtonSize;
    List<User> followerList;
    public ProfileController(ProfileControllerObserver observer) {
        super(observer);
        this.followerList = new ArrayList<>();
        this.subscribeButtonSize = new Dimension(150, 50);
        avatarClick = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                FileSelectionView folderSelectionView = new FileSelectionView();
                ProfileController.this.folderSelectEvent(folderSelectionView.getDirectory());
            }
        };
        this.profileView = new ProfileView(this.backButton, this.avatarClick);
    }

    private void folderSelectEvent(String directory) {
        this.observer.changeAvatar(directory);
    }
    protected void unsubscribeEvent(User user) {
        this.observer.unsubscribeEvent(user);
    }

    protected void subscribeEvent(User user) {
        this.observer.subscribeEvent(user);
    }
    public ProfileView getProfileView() {
        return this.profileView;
    }
    @Override
    public void sessionUpdate(User user) {
        this.subscribeButton = new AppButton("Subscribe", subscribeButtonSize, new ButtonAction() {
            @Override
            public void run() {
                ProfileController.this.subscribeEvent(user);
            }
        });
        this.unsubscribeButton = new AppButton("Unsubscribe", subscribeButtonSize, new ButtonAction() {
            @Override
            public void run() {
                ProfileController.this.unsubscribeEvent(user);
            }
        });
        this.profileView.sessionUpdate(user);
        this.profileView.revalidate();
        this.profileView.repaint();
    }
}
