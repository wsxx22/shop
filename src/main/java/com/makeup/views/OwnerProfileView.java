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
    RoleFacade roleFacade;
    UserFacade userFacade;

    public OwnerProfileView(RoleFacade roleFacade, UserFacade userFacade) {
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
        Label profileLabel = new Label("Admin Profile");
        root.addComponents(nameShopLabel, profileLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
    }

    private void authorizedUserLayout(){

        Button logoutButton = new Button("Logout", VaadinIcons.SIGN_OUT);

        logoutButton.addClickListener(clickEvent -> {
            userFacade.logout();
            navigateTo("homepage");
        });

        menuLayout.addComponents(logoutButton);
        root.addComponents(menuLayout);
    }

    private void navigateTo(String view){
        getUI().getNavigator().navigateTo(view);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        header();
        authorizedUserLayout();
    }
}
