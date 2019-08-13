package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.UserFacade;
import com.makeup.utils.GlobalAuthorization;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "user-profile")
public class UserProfileView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    RoleFacade roleFacade;
    UserFacade userFacade;

    public UserProfileView(RoleFacade roleFacade, UserFacade userFacade) {
        this.roleFacade = roleFacade;
        this.userFacade = userFacade;

        setupLayout();
        header();
        authorizedUserLayout();
    }

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label nameShopLabel = new Label("MakeUp Shop");
        Label profileLabel = new Label("Your profile");
        root.addComponents(nameShopLabel, profileLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
    }

    private void authorizedUserLayout(){
        if (GlobalAuthorization.isAuthorized()){
            Button productsButton = new Button("View products in the store", VaadinIcons.SHOP);
            Button historyOrderButton = new Button("History order", VaadinIcons.LIST);
            Button settingsButton = new Button("Change settings", VaadinIcons.LIST);
            Button logoutButton = new Button("Logout", VaadinIcons.SIGN_OUT);

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
}
