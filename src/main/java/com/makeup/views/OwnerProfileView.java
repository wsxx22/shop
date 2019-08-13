package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.UserFacade;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
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
        Label profileLabel = new Label("Admin Profile");
        root.addComponents(nameShopLabel, profileLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
    }

    private void authorizedUserLayout(){

        root.addComponents(menuLayout);
    }
}
