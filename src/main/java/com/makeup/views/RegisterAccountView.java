package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.UserFacade;
import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.utils.ParameterizedException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mindrot.jbcrypt.BCrypt;

import static com.makeup.utils.Constant.User.USER_ROLE;
import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "register-account")
public class RegisterAccountView extends Composite implements View {

    VerticalLayout root;
    UserFacade userFacade;
    RoleFacade roleFacade;

    public RegisterAccountView(UserFacade userFacade, RoleFacade roleFacade) {
        this.userFacade = userFacade;
        this.roleFacade = roleFacade;

    }

    private void setup(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label headerLabel = new Label("Registering account");
        root.addComponent(headerLabel);
    }

    private void register(){
        VerticalLayout registerLayout = new VerticalLayout();
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        registerLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        registerLayout.setWidth("400");

        TextField loginField = new TextField("Login:");
        TextField emailField = new TextField("Email:");
        PasswordField passwordField = new PasswordField("Password:");


        Button createAccountButton = new Button("Create", VaadinIcons.ENTER);
        Button returnButton = new Button("Return", VaadinIcons.EXIT);

        createAccountButton.addClickListener(click -> {
            CreateUserDto createUserDto = new CreateUserDto(loginField.getValue(), emailField.getValue(), passwordField.getValue());
            String role = roleFacade.findRole(USER_ROLE).getRole();
            if (userFacade.create(createUserDto, role)){
                Notification.show("Registered successfully!").setDelayMsec(1500);
                navigateToHomepage();
            } });

        returnButton.addClickListener(click -> navigateToHomepage());

//        VaadinSession.getCurrent().setErrorHandler(errorEvent ->
//                Notification.show(ParameterizedException.exception, ERROR_MESSAGE).setDelayMsec(2000));

        SettingsLayout.setFullSizeTextFields(loginField, emailField, passwordField);
        SettingsLayout.setSizeButtons(createAccountButton, returnButton);

        buttonsLayout.addComponents(createAccountButton, returnButton);
        registerLayout.addComponents(loginField,emailField, passwordField, buttonsLayout);
        root.addComponent(registerLayout);
    }

    private void navigateToHomepage(){
        getUI().getNavigator().navigateTo("homepage");
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setup();
        header();
        register();
    }
}
