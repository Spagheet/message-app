package com.ubo.tp.message.ihm.controler;

import com.ubo.tp.message.ihm.component.ButtonAction;
import com.ubo.tp.message.ihm.view.FileSelectionView;
import com.ubo.tp.message.ihm.view.RegisterAccountView;
import com.ubo.tp.message.ihm.view.panel.AppButton;

import java.util.*;
import java.util.List;

public class RegisterController extends AbstractMenuOptionController<RegisterControllerObserver> {
    protected RegisterAccountView registerView;
    protected AppButton registerButton;
    protected AppButton folderSelectButton;

    protected List<AppButton> buttonList;
    public RegisterController(RegisterControllerObserver observer) {
        super(observer);
        registerButton = new AppButton("Register", buttonSize, RegisterController.this::registerRequestEvent);
        folderSelectButton = new AppButton("Select a folder", buttonSize, () -> {
            FileSelectionView folderSelectionView = new FileSelectionView();
            RegisterController.this.folderSelectEvent(folderSelectionView.getDirectory());
        });
        buttonList = new ArrayList<>();
        buttonList.add(registerButton);
        buttonList.add(backButton);
        buttonList.add(folderSelectButton);
        registerView = new RegisterAccountView(buttonList);
    }
    public RegisterAccountView getRegisterView() {
        return this.registerView;
    }
    public void registerRequestEvent() {
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("username", this.registerView.getUsername());
        fields.put("tag", this.registerView.getTag());
        fields.put("password", this.registerView.getPassword());
        fields.put("avatar", this.registerView.getAvatar());
        if(!this.validateForm(fields)) {
            this.registerView.badFields();
        } else if(this.observer.registerRequestEvent(fields)) {
            this.registerView.registerSuccessful();
        } else {
            this.registerView.registerUnsuccessful();
        }
    }
    public void folderSelectEvent(String directory) {
        this.registerView.setAvatarField(directory);
    }
    protected boolean validateForm(Map<String, String> fields) {
        for(Map.Entry<String, String> entry : fields.entrySet()) {
            String value = entry.getValue();
            if(value == null || value.equals("")) {
                return false;
            }
        }
        return true;
    }
}
