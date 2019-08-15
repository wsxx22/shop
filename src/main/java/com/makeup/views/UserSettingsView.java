package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.UserFacade;
import com.makeup.utils.GlobalAuthorization;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "user-settings")
public class UserSettingsView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    RoleFacade roleFacade;
    UserFacade userFacade;

    public UserSettingsView(RoleFacade roleFacade, UserFacade userFacade) {
        this.roleFacade = roleFacade;
        this.userFacade = userFacade;
    }

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label nameShopLabel = new Label("MakeUp Shop");
        Label settingsLabel = new Label("Your settings");
        root.addComponents(nameShopLabel, settingsLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
    }

    private void settings(){
        if (GlobalAuthorization.isAuthorized()){
            header();
            HorizontalLayout buttonsLayout = new HorizontalLayout();
            Label changeLoginLabel = new Label("If you want change username, contact with administration.");
            TextField changeEmailField = new TextField("Enter a new email:");
            PasswordField changePasswordField = new PasswordField("Enter a new password:");

            Button saveChangesButton = new Button("Save");
            Button returnButton = new Button("Return");

            saveChangesButton.addClickListener(clickEvent -> {
                userFacade.changePassword(changePasswordField.getValue());
                Notification.show("Your password changed.").setDelayMsec(1500);
            });
            returnButton.addClickListener(clickEvent ->
                    getUI().getNavigator().navigateTo("user-profile"));

            buttonsLayout.addComponents(saveChangesButton, returnButton);
            menuLayout.addComponents(changeLoginLabel, changeEmailField, changePasswordField, buttonsLayout);
            root.addComponent(menuLayout);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        settings();
    }

}