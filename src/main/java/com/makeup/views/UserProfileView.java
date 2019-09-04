package com.makeup.views;

import com.makeup.user.domain.UserFacade;
import com.makeup.utils.GlobalAuthorization;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import static com.makeup.utils.Constant.Label.STORE_NAME;
import static com.makeup.utils.Constant.Label.WELCOME_USER;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "user-profile")
public class UserProfileView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    UserFacade userFacade;

    public UserProfileView(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label nameShopLabel = new Label(STORE_NAME);
        Label welcomeNameLabel = new Label(WELCOME_USER);
        root.addComponents(nameShopLabel, welcomeNameLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
    }

    private void authorizedUserLayout(){
        if (GlobalAuthorization.isAuthorized()){
            header();
            Button productsButton = new Button("View products in the store", VaadinIcons.SHOP);
            Button historyOrderButton = new Button("History bought products", VaadinIcons.LIST);
            Button settingsButton = new Button("Change settings", VaadinIcons.LIST);
            Button logoutButton = new Button("Logout", VaadinIcons.SIGN_OUT);

            productsButton.addClickListener(clickEvent -> navigateTo("products"));
            historyOrderButton.addClickListener(clickEvent -> navigateTo("history-orders"));
            settingsButton.addClickListener(clickEvent -> navigateTo("user-settings"));
            logoutButton.addClickListener(clickEvent -> {
                    userFacade.logout();
                    navigateTo("homepage");
            });

            SettingsLayout.setSizeFullButtons(productsButton, historyOrderButton, settingsButton, logoutButton);
            menuLayout.addComponents(productsButton, historyOrderButton, settingsButton, logoutButton);
            root.addComponent(menuLayout);
        }
    }

    private void navigateTo(String view){
        getUI().getNavigator().navigateTo(view);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        authorizedUserLayout();
    }
}
