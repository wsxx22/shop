package com.makeup.views;

import com.makeup.utils.GlobalAuthorization;
import com.makeup.utils.ParameterizedException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import static com.makeup.utils.Constant.User.*;
import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "homepage")
public class HomepageView extends Composite implements View {
    VerticalLayout root;
    VerticalLayout menuLayout;

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label welcomeLabel = new Label("Welcome to the store !!!!");
        root.addComponent(welcomeLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
        VaadinSession.getCurrent().setErrorHandler(errorEvent ->
                Notification.show(ParameterizedException.exception, WARNING_MESSAGE));
    }

    private void checkAuthorize(){
        if (GlobalAuthorization.isAuthorized()) {
            checkRole();
        }
        unauthorizedLayout();
    }

    private void checkRole(){
        adminRole();
        cashierRole();
        userRole();
    }

    private void adminRole() {
        GlobalAuthorization.getUserRoles().stream()
                .filter(role -> role.equals(ADMIN_ROLE))
                .findAny()
                .ifPresent(role -> navigateTo("owner-profile"));
    }

    private void cashierRole() {
        GlobalAuthorization.getUserRoles().stream()
                .filter(role -> role.equals(CASHIER_ROLE))
                .findAny()
                .ifPresent(role -> navigateTo("cashier-profile"));
    }

    private void userRole() {
        GlobalAuthorization.getUserRoles().stream()
                .filter(role -> role.equals(USER_ROLE))
                .findAny()
                .ifPresent(role -> navigateTo("user-profile"));
    }

    private void unauthorizedLayout(){
        Button productsButton = new Button("View products in the store", VaadinIcons.LIST);
        Button loginButton = new Button("Sign In", VaadinIcons.SIGN_IN);
        Button registerButton = new Button("Register", VaadinIcons.USER);

        productsButton.addClickListener(clickEvent -> navigateTo("products"));
        loginButton.addClickListener(clickEvent -> navigateTo("login"));
        registerButton.addClickListener(clickEvent -> navigateTo("register-account"));

        SettingsLayout.setSizeFullButtons(productsButton, loginButton, registerButton);
        menuLayout.addComponents(productsButton, loginButton, registerButton);
        root.addComponent(menuLayout);
    }

    private void navigateTo(String navigateLayout){
        getUI().getNavigator().navigateTo(navigateLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        header();
        checkAuthorize();
    }
}