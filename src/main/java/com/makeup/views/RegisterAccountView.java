package com.makeup.views;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.UserFacade;
import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.utils.ParameterizedException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.stream.Stream;

import static com.makeup.shared.Constant.User.USER_ROLE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "register-account")
public class RegisterAccountView extends Composite implements View {

    VerticalLayout root;
    UserFacade userFacade;
    RoleFacade roleFacade;

    public RegisterAccountView(UserFacade userFacade, RoleFacade roleFacade) {
        this.userFacade = userFacade;
        this.roleFacade = roleFacade;
        setup();
        header();
        register();
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
        PasswordField repeatPasswordField = new PasswordField("Repeat password:");

        Button createAccountButton = new Button("Create", VaadinIcons.ENTER);
        Button returnButton = new Button("Return", VaadinIcons.EXIT);

        createAccountButton.addClickListener(click -> {userFacade.create(
//                new CreateUserDto("andrzej", "test@wp.pl", "Pozdro123@")
                CreateUserDto.builder()
                                    .login(loginField.getValue())
                                    .email(emailField.getValue())
                                    .password(repeatPasswordField.getValue())
                                    .build()
                            ,roleFacade.findRole(USER_ROLE).getRole());
            Notification.show("Logged Succesfull!");
                });
//        roleFacade.findRole(USER_ROLE).getRole()
//        VaadinSession.getCurrent().setErrorHandler(errorEvent ->
//                Notification.show("test1", Notification.Type.ERROR_MESSAGE));

        setFullSizeTextFields(loginField, emailField, passwordField, repeatPasswordField);
        setSizeButtons(createAccountButton, returnButton);

        buttonsLayout.addComponents(createAccountButton, returnButton);
        registerLayout.addComponents(loginField,emailField, passwordField, repeatPasswordField, buttonsLayout);
        root.addComponent(registerLayout);
    }

    private void setSizeButtons(Button... buttons){
        Stream.of(buttons).forEach(button -> button.setWidth("150"));
    }

    private void setFullSizeTextFields(TextField... textFields){
        Stream.of(textFields).forEach(AbstractComponent::setSizeFull);
    }
}
