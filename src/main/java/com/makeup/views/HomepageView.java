package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.UserFacade;
import com.makeup.utils.GlobalAuthorization;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "homepage")
public class HomepageView extends Composite implements View {
    VerticalLayout root;
    VerticalLayout menuLayout;
    RoleFacade roleFacade;
    UserFacade userFacade;

    public HomepageView(RoleFacade roleFacade, UserFacade userFacade) {
        this.roleFacade = roleFacade;
        this.userFacade = userFacade;

        setupLayout();
        header();
        checkAuthorize();
    }

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label welcomeLabel = new Label("Welcome to the store");
        root.addComponent(welcomeLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
    }

    private void checkAuthorize(){
        if (GlobalAuthorization.isAuthorized()) {
            authorizedUserLayout();
        } else {
            unauthorizedLayout();
        }
    }

    private void checkTypeUser(){

    }

    private void unauthorizedLayout(){
        Button productsButton = new Button("View products in the store", VaadinIcons.LIST);
        Button loginButton = new Button("Sign In", VaadinIcons.SIGN_IN);
        Button registerButton = new Button("Register", VaadinIcons.USER);

        loginButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("login"));

        registerButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("register-account"));

        SettingsLayout.setSizeFullButtons(productsButton, loginButton, registerButton);
        menuLayout.addComponents(productsButton, loginButton, registerButton);
        root.addComponent(menuLayout);
    }

    private void authorizedUserLayout(){
        Button productsButton = new Button("View products in the store", VaadinIcons.SHOP);
        Button historyOrderButton = new Button("History order", VaadinIcons.LIST);
        Button settingsButton = new Button("Change settings", VaadinIcons.LIST);
        Button logoutButton = new Button("Logout", VaadinIcons.SIGN_OUT);

        logoutButton.addClickListener(clickEvent -> {
            userFacade.logout();
            Page.getCurrent().reload();
        });

        SettingsLayout.setSizeFullButtons(productsButton, historyOrderButton, settingsButton, logoutButton);
        menuLayout.addComponents(productsButton, historyOrderButton, settingsButton, logoutButton);
        root.addComponent(menuLayout);
    }




}