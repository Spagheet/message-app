package main.java.com.ubo.tp.message.ihm.view;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.view.panel.AppButton;

import javax.swing.*;
import java.util.Set;

public class AppMenuView extends JPanel {
    JLabel sessionLabel;
    public AppMenuView(Set<AppButton> buttons) {

        // change to list off buttons to name the buttons and add / remove as needed

        sessionLabel = new JLabel();
        sessionLabel.setText("");
        addButtons(buttons);
    }
    protected void addButtons(Set<AppButton> buttons) {
        for(AppButton button : buttons) {
            add(button);
        }
    }
    protected void setSessionLabel(User user) {
        this.add(sessionLabel);
        this.sessionLabel.setText("Current user : " + user.getName() + "#" + user.getUserTag());
    }

    public void sessionUpdate(User user, Set<AppButton> appButtons) {
        this.removeAll();
        this.addButtons(appButtons);
        if(user != null) {
            this.setSessionLabel(user);
        }
    }
}
