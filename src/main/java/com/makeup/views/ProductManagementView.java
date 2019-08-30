package com.makeup.views;

import com.makeup.product.domain.CategoryFacade;
import com.makeup.product.domain.ProductFacade;
import com.makeup.product.domain.dto.CategoryDto;
import com.makeup.product.domain.dto.CreateProductDto;
import com.makeup.utils.ParameterizedException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static com.vaadin.ui.Notification.Type.WARNING_MESSAGE;
import static java.lang.Integer.parseInt;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "product-management")
public class ProductManagementView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    ProductFacade productFacade;
    CategoryFacade categoryFacade;
    ValidationField validationField;

    public ProductManagementView(ProductFacade productFacade, CategoryFacade categoryFacade) {
        this.productFacade = productFacade;
        this.categoryFacade = categoryFacade;
    }

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label nameShopLabel = new Label("MakeUp Products Management");
        root.addComponents(nameShopLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuLayout.setWidth("350");
    }

    private void managementProduct(){
        validationField = new ValidationField();
        HorizontalLayout mainMenuLayout = new HorizontalLayout();
        mainMenuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        VerticalLayout textFieldsLayout = new VerticalLayout();
        CheckBoxGroup<CategoryDto> productCategories = new CheckBoxGroup<>("Categories:");
        productCategories.setItems(categoryFacade.findAll());
        productCategories.setValue(categoryFacade.findAll());
        productCategories.deselectAll();
        HorizontalLayout buttonsHorizontalLayout = new HorizontalLayout();

        TextField nameTextfield = new TextField("Product name:");
        TextArea descriptionTextfield = new TextArea("Description:");
        TextField capacityTextField = new TextField("Capacity:");
        TextField priceTextField = new TextField("Price:");
        TextField amountTextField = new TextField("Amount:");
        Button saveProductButton = new Button("Save product", VaadinIcons.COMPILE);
        Button returnButton = new Button("Return", VaadinIcons.EXIT);

        saveProductButton.addClickListener(clickEvent -> {
            validationField.validBlankField(nameTextfield,capacityTextField,priceTextField,amountTextField);
            validationField.validFieldIsDouble(capacityTextField, priceTextField);
            validationField.validFieldIsInteger(amountTextField);
            validationField.validBlankArea(descriptionTextfield);
            CreateProductDto productDto = new CreateProductDto(nameTextfield.getValue(),
                                                                descriptionTextfield.getValue(),
                                                                Double.parseDouble(capacityTextField.getValue()),
                                                                new BigDecimal(priceTextField.getValue()),
                                                                parseInt(amountTextField.getValue()),
                                                                productCategories.getSelectedItems());
            productFacade.addProduct(productDto);
        });

        returnButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("homepage"));

        VaadinSession.getCurrent().setErrorHandler(errorEvent ->
                Notification.show(ParameterizedException.exception, WARNING_MESSAGE).setDelayMsec(1000));

        buttonsHorizontalLayout.addComponents(saveProductButton, returnButton);
        textFieldsLayout.addComponents(nameTextfield,descriptionTextfield,capacityTextField,priceTextField,amountTextField);
        menuLayout.addComponents(textFieldsLayout,buttonsHorizontalLayout);
        mainMenuLayout.addComponents(menuLayout, productCategories);
        root.addComponent(mainMenuLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        header();
        managementProduct();
    }
}
