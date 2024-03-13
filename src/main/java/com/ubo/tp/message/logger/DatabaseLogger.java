package com.ubo.tp.message.logger;

import com.ubo.tp.message.core.database.IDatabaseObserver;
import com.ubo.tp.message.datamodel.Message;
import com.ubo.tp.message.datamodel.User;

public class DatabaseLogger implements IDatabaseObserver {
    private void printMessage(Message m) {
        System.out.println("\t" + m.getText() + "\n");
    }
    private void printUser(User u) {
        System.out.println("\t" + u.getName() + "\n");
    }
    @Override
    public void notifyMessageAdded(Message addedMessage) {
        System.out.println("Message added :\n");
        this.printMessage(addedMessage);
    }

    @Override
    public void notifyMessageDeleted(Message deletedMessage) {
        System.out.println("Message deleted :\n");
        this.printMessage(deletedMessage);
    }

    @Override
    public void notifyMessageModified(Message modifiedMessage) {
        System.out.println("Message modified :\n");
        this.printMessage(modifiedMessage);
    }

    @Override
    public void notifyUserAdded(User addedUser) {
        System.out.println("User added :\n");
        this.printUser(addedUser);
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        System.out.println("User deleted :\n");
        this.printUser(deletedUser);
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        System.out.println("User modified :\n");
        this.printUser(modifiedUser);
    }
}
