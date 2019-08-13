package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.UserFacade;
import com.makeup.utils.GlobalAuthorization;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.util.EventListener;

import static com.makeup.utils.Constant.User.*;


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
        Label welcomeLabel = new Label("Welcome to the store !!!!");
        root.addComponent(welcomeLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
        root.addComponent(menuLayout);
    }

    private void checkAuthorize(){
        if (GlobalAuthorization.isAuthorized()) {
            UI.setCurrent(this.getUI());
//            UI.getCurrent().getPage().getUI().getPage().reload();
//            getUI().getPage().reload();
            checkRole();
        } else {
            unauthorizedLayout();
        }
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
                .ifPresent(role -> {

                    UI.getCurrent().getNavigator().navigateTo("user-profile");
//                    Page.getCurrent().getUI().getNavigator().navigateTo("owner-profile");


//                    Navigator navigator = getUI().getNavigator();
//                    navigator.navigateTo("owner-profile");
//                    UI.getCurrent().setNavigator(navigator);

//                    navigateTo("owner-profile");

                });
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

        loginButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("login"));

        registerButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("register-account"));

        SettingsLayout.setSizeFullButtons(productsButton, loginButton, registerButton);
        menuLayout.addComponents(productsButton, loginButton, registerButton);
        root.addComponent(menuLayout);
    }

    private void navigateTo(String navigateLayout){
        getUI().getNavigator().navigateTo(navigateLayout);
    }
}