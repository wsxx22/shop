package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.UserFacade;
import com.makeup.utils.GlobalAuthorization;
import com.makeup.utils.ParameterizedException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.stream.Stream;

import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;

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
        settingsLabel.setHeight("50");
        Label changeLoginLabel = new Label("If you want change username, contact with administration.");
        root.addComponents(nameShopLabel, settingsLabel, changeLoginLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
    }

    private void settings(){
        if (GlobalAuthorization.isAuthorized()){
            header();
            HorizontalLayout buttonsLayout = new HorizontalLayout();
            TextField changeEmailField = new TextField("Enter a new email:");
            PasswordField changePasswordField = new PasswordField("Enter a new password:");

            Button saveChangesButton = new Button("Save");
            Button returnButton = new Button("Return");

            saveChangesButton.addClickListener(clickEvent -> {
                changePassword(changePasswordField);
                changeEmail(changeEmailField);
            });
            returnButton.addClickListener(clickEvent ->
                    getUI().getNavigator().navigateTo("user-profile"));

            VaadinSession.getCurrent().setErrorHandler(
                    errorEvent -> Notification.show(ParameterizedException.exception, WARNING_MESSAGE));

            buttonsLayout.addComponents(saveChangesButton, returnButton);
            menuLayout.addComponents(changeEmailField, changePasswordField, buttonsLayout);
            root.addComponent(menuLayout);
        }
    }

    private void changePassword(PasswordField passwordField) {
        Stream.of(passwordField)
                .filter(field -> !field.isEmpty())
                .findFirst()
                .ifPresent(field -> {
                    userFacade.changePassword(passwordField.getValue());
                    Notification.show("Your password changed.").setDelayMsec(1000);
                });
    }

    private void changeEmail(TextField emailField) {
        Stream.of(emailField)
                .filter(field -> !field.isEmpty())
                .findFirst()
                .ifPresent(field -> {
                    userFacade.changeEmail(emailField.getValue());
                    Notification.show("Your email changed.").setDelayMsec(1500);
                });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        settings();
    }
}