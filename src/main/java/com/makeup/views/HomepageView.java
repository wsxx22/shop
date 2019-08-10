package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.stream.Stream;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "homepage")
public class HomepageView extends Composite implements View {
    VerticalLayout root;
    VerticalLayout menuLayout;
    RoleFacade roleFacade;

    public HomepageView(RoleFacade roleFacade) {
        this.roleFacade = roleFacade;

        setupLayout();
        header();
        menu();
    }

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label welcomeLabel = new Label("Witamy w sklepie");
        root.addComponent(welcomeLabel);
    }

    private void menu(){
        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");

        Button productsButton = new Button("Look at products", VaadinIcons.LIST);
        Button loginButton = new Button("Sign In", VaadinIcons.SIGN_IN);
        Button registerButton = new Button("Register", VaadinIcons.USER);

        registerButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("register-account"));

        setSizeFullButtons(productsButton, loginButton, registerButton);

        menuLayout.addComponents(productsButton, loginButton, registerButton);

        root.addComponent(menuLayout);
    }

    private void setSizeFullButtons(Button... buttons){
        Stream.of(buttons).forEach(AbstractComponent::setSizeFull);
    }


}
