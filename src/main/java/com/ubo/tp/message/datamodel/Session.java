package com.ubo.tp.message.datamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Session {
    Set<SessionObserver> observerSet;
    User user;
    public Session() {
        this.user = null;
        this.observerSet = new HashSet<>();
    }
    public void addObserver(SessionObserver observer) {
        this.observerSet.add(observer);
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
        this.updateObservers();
    }
    protected void updateObservers() {
        for(SessionObserver o : observerSet) {
            o.sessionUpdate(this.user);
        }
    }

    public void addFollowing(User user) {
        this.user.addFollowing(user.getUserTag());
        this.updateObservers();
    }

    public void removeFollowing(User user) {
        this.user.removeFollowing(user.getUserTag());
        this.updateObservers();
    }

    public void setAvatarPath(String path) {
        this.user.setAvatarPath(path);
        this.updateObservers();
    }

    public void logout() {
        this.user = null;
        this.updateObservers();
    }
}
