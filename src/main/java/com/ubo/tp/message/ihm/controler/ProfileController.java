package com.ubo.tp.message.ihm.controler;

import com.ubo.tp.message.datamodel.*;
import com.ubo.tp.message.ihm.component.ButtonAction;
import com.ubo.tp.message.ihm.view.FileSelectionView;
import com.ubo.tp.message.ihm.view.ProfileView;
import com.ubo.tp.message.ihm.view.panel.AppButton;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ProfileController extends AbstractMenuOptionController<ProfileControllerObserver> implements SessionObserver {
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
        this.subscribeButton = new AppButton("Subscribe", subscribeButtonSize, () -> ProfileController.this.subscribeEvent(user));
        this.unsubscribeButton = new AppButton("Unsubscribe", subscribeButtonSize, () -> ProfileController.this.unsubscribeEvent(user));
        this.profileView.sessionUpdate(user);
        this.profileView.revalidate();
        this.profileView.repaint();
    }
}
