package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.UserFacade;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "owner-profile")
public class OwnerProfileView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    UserFacade userFacade;

    public OwnerProfileView(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label nameShopLabel = new Label("MakeUp Shop");
        Label profileLabel = new Label("Admin Profile");
        root.addComponents(nameShopLabel, profileLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

    private void authorizedOwnerLayout(){

        Button productsButton = new Button("Products in the store");
        Button managementProductsButton = new Button("Add a new product or update an existing one");
        Button logoutButton = new Button("Logout", VaadinIcons.SIGN_OUT);

        productsButton.addClickListener(clickEvent -> navigateTo("products"));

        managementProductsButton.addClickListener(clickEvent -> navigateTo("product-management"));

        logoutButton.addClickListener(clickEvent -> {
            userFacade.logout();
            navigateTo("homepage");
        });

        menuLayout.addComponents(managementProductsButton,productsButton,logoutButton);
        root.addComponents(menuLayout);
    }

    private void navigateTo(String view){
        getUI().getNavigator().navigateTo(view);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        header();
        authorizedOwnerLayout();
    }
}
