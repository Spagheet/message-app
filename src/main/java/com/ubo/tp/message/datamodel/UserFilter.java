package main.java.com.ubo.tp.message.datamodel;

import main.java.com.ubo.tp.message.ihm.controler.UserListController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFilter {
    String filter;
    List<User> userList;
    List<UserFilterObserver> observers;
    public UserFilter() {
        this.filter = "";
        this.userList = new ArrayList<>();
        this.observers = new ArrayList<>();
    }
    protected void notifyObservers() {
        List<User> filteredUsers = this.getFilteredList();
        for(UserFilterObserver o : observers) {
            o.notifyFilterUpdate(filteredUsers);
        }
    }

    private List<User> getFilteredList() {
        if(this.filter.equals("")) {
            return this.userList;
        } else {
            List<User> newUserList = new ArrayList<>();
            return newUserList;
        }
    }

    public void addObserver(UserFilterObserver observer) {
        this.observers.add(observer);
    }

    public void setFilter(String filter) {
        this.filter = filter;
        this.notifyObservers();
    }

    public void notifyUserAdded(User addedUser) {
        this.userList.add(addedUser);
        this.notifyObservers();
    }

    public void notifyUserDeleted(User deletedUser) {
        this.userList.remove(deletedUser);
        this.notifyObservers();
    }

    public void notifiyUserModified(User modifiedUser) {
        UUID uuid = modifiedUser.getUuid();
        User modified = null;
        for(User u : userList) {
            if(u.getUuid().equals(uuid)) {
                modified = u;
                break;
            }
        }
        if(modified != null) {
            userList.remove(modified);
            userList.add(modifiedUser);
        }
        this.notifyObservers();
    }
}
