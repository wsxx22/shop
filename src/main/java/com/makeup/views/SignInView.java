package com.makeup.views;

import com.makeup.user.domain.UserFacade;
import com.makeup.utils.ParameterizedException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;
import static com.vaadin.ui.Notification.Type.HUMANIZED_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "login")
public class SignInView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    UserFacade userFacade;

    public SignInView(UserFacade userFacade){
        this.userFacade = userFacade;
        setup();
        header();
        signIn();
    }

    private void setup(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label welcomeLabel = new Label("Sign in");
        root.addComponent(welcomeLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
    }

    private void signIn(){
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        TextField loginField = new TextField("Login:");
        PasswordField passwordField = new PasswordField("Password:");

        Button signInButton = new Button("Sign In", VaadinIcons.SIGN_IN);
        Button returnButton = new Button("Return", VaadinIcons.EXIT);

        signInButton.addClickListener(clickEvent -> {
            userFacade.login(loginField.getValue(), passwordField.getValue());
            Notification.show("Logged in successfully", HUMANIZED_MESSAGE);
            getUI().getNavigator().navigateTo("homepage");
        });

        returnButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("homepage"));

        VaadinSession.getCurrent().setErrorHandler(errorEvent ->
                Notification.show(ParameterizedException.exception, ERROR_MESSAGE));

        SettingsLayout.setFullSizeTextFields(loginField, passwordField);
        buttonsLayout.addComponents(signInButton,returnButton);
        menuLayout.addComponents(loginField, passwordField, buttonsLayout);
        root.addComponent(menuLayout);
    }
}
