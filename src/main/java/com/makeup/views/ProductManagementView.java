package com.makeup.views;

import com.makeup.product.domain.CategoryFacade;
import com.makeup.product.domain.ProductFacade;
import com.makeup.product.domain.dto.CategoryDto;
import com.makeup.product.domain.dto.CreateProductDto;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

import static com.makeup.views.exception.ViewException.CAUSE.FIELD_MUST_BE_DIGIT;
import static com.makeup.views.exception.ViewException.CAUSE.FIELD_MUST_BE_INTEGER;
import static java.lang.Integer.parseInt;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "product-management")
public class ProductManagementView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    ProductFacade productFacade;
    CategoryFacade categoryFacade;

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
        Button saveProduct = new Button("Save product", VaadinIcons.COMPILE);
        Button returnButton = new Button("Return", VaadinIcons.EXIT);

        validTextField(capacityTextField);
        validTextField(priceTextField);
        validIsInteger(amountTextField);

        saveProduct.addClickListener(clickEvent -> {
            CreateProductDto productDto = new CreateProductDto(nameTextfield.getValue(),
                                                                descriptionTextfield.getValue(),
                                                                Double.parseDouble(capacityTextField.getValue()),
                                                                new BigDecimal(priceTextField.getValue()),
                                                                parseInt(amountTextField.getValue()),
                                                                productCategories.getSelectedItems());
            productFacade.addProduct(productDto);
        });

        returnButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("homepage"));
        
        buttonsHorizontalLayout.addComponents(saveProduct, returnButton);
        textFieldsLayout.addComponents(nameTextfield,descriptionTextfield,capacityTextField,priceTextField,amountTextField);
        menuLayout.addComponents(textFieldsLayout,buttonsHorizontalLayout);
        mainMenuLayout.addComponents(menuLayout, productCategories);
        root.addComponent(mainMenuLayout);
    }

    private void validTextField(TextField field){
//        if (field.isEmpty())
        boolean dot = false;
        for(char c : field.getValue().toCharArray()){
            if (isDigit(c)) continue;
            if (c == '.'){
                if (!dot){
                    dot=true;
                    continue;
                }
            }
            throw new RuntimeException(String.format(FIELD_MUST_BE_DIGIT.getMessage(), field.getCaption()));
        }
    }

    private void validIsInteger(TextField field){
        for(char c : field.getValue().toCharArray()) {
            if (isDigit(c)) {
            } else {
                throw new RuntimeException(String.format(FIELD_MUST_BE_INTEGER.getMessage(), field.getCaption()));
            }
        }
    }

    private boolean isDigit(char c) {
        if (c == '0' | c == '1' | c == '2' | c == '3' | c == '4' | c == '5' | c == '6' | c == '7' | c == '8' | c == '9') {
            return true;
        }
        return false;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        header();
        managementProduct();
    }
}
