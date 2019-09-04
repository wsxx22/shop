package com.makeup.views;

import com.makeup.product.domain.CategoryFacade;
import com.makeup.product.domain.ProductFacade;
import com.makeup.product.domain.dto.CategoryDto;
import com.makeup.product.domain.dto.CreateProductDto;
import com.makeup.product.domain.dto.ProductDto;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static com.makeup.views.message.ViewMessage.CAUSE.*;
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
    }

    private void managementProduct(){
        validationField = new ValidationField();

        VerticalLayout textFieldsLayout = new VerticalLayout();
        CheckBoxGroup<CategoryDto> productCategories = new CheckBoxGroup<>("Categories:");
        productCategories.setItems(categoryFacade.findAll());
        productCategories.setValue(categoryFacade.findAll());
        productCategories.deselectAll();
        HorizontalLayout buttonsHorizontalLayout = new HorizontalLayout();
        HorizontalLayout managementProductsHorizontalLayout = new HorizontalLayout();
        managementProductsHorizontalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        TextField nameTextfield = new TextField("Product name:");
        TextArea descriptionTextfield = new TextArea("Description:");
        TextField capacityTextField = new TextField("Capacity:");
        TextField priceTextField = new TextField("Price:");
        TextField amountTextField = new TextField("Amount:");
        Button updateProductButton = new Button("Update product", VaadinIcons.LEVEL_UP);
        Button saveProductButton = new Button("Save product", VaadinIcons.COMPILE);
        Button resetButton = new Button("Clean all fields", VaadinIcons.REFRESH);
        Button returnButton = new Button("Return", VaadinIcons.EXIT);

        Grid<ProductDto> productsGrid = new Grid<>("Select any product to update:");
        productsGrid.setWidth("200");
        productsGrid.setItems(productFacade.findAllProducts());
        productsGrid.addColumn(ProductDto::getName).setCaption("Name");

        updateProductButton.addClickListener(clickEvent -> {
            if (productsGrid.getSelectedItems().size() > 0){
                validationField.validFieldIsDouble(capacityTextField, priceTextField);
                validationField.validFieldIsInteger(amountTextField);
                ProductDto selectedProductDto = productsGrid.getSelectedItems().iterator().next();
                ProductDto productDto = ProductDto.builder()
                        .id(selectedProductDto.getId())
                        .name(nameTextfield.getValue().isBlank() ? selectedProductDto.getName() : nameTextfield.getValue())
                        .description(descriptionTextfield.getValue().isBlank() ? selectedProductDto.getDescription() : descriptionTextfield.getValue())
                        .capacity(getDefaultValueIfBlank(capacityTextField, selectedProductDto))
                        .price(priceTextField.getValue().isBlank() ? selectedProductDto.getPrice() : new BigDecimal(priceTextField.getValue()))
                        .amount(amountTextField.getValue().isBlank() ? 0 : parseInt(amountTextField.getValue()))
                        .categories(productCategories.getSelectedItems()).build();
                productFacade.update(productDto);
                Notification.show(String.format(PRODUCT_UPDATED.getMessage(),productDto.getName())).setDelayMsec(1500);
                navigateTo("product-management");
            }else {
                Notification.show(PRODUCT_MUST_BE_SELECTED.getMessage()).setDelayMsec(1500);
            }
        });

        saveProductButton.addClickListener(clickEvent -> {
            validateProductFields(nameTextfield, descriptionTextfield, capacityTextField, priceTextField, amountTextField);
            CreateProductDto createProductDto = new CreateProductDto(nameTextfield.getValue(),
                                                                descriptionTextfield.getValue(),
                                                                Double.parseDouble(capacityTextField.getValue()),
                                                                new BigDecimal(priceTextField.getValue()),
                                                                parseInt(amountTextField.getValue()),
                                                                productCategories.getSelectedItems());
            productFacade.addProduct(createProductDto);
            Notification.show(PRODUCT_ADDED.getMessage()).setDelayMsec(1500);
            navigateTo("product-management");
        });

        resetButton.addClickListener(clickEvent -> navigateTo("product-management"));
        returnButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("homepage"));

        buttonsHorizontalLayout.addComponents(updateProductButton,saveProductButton,resetButton,returnButton);
        textFieldsLayout.addComponents(nameTextfield,descriptionTextfield,capacityTextField,priceTextField,amountTextField);
        managementProductsHorizontalLayout.addComponents(productsGrid,textFieldsLayout,productCategories);
        menuLayout.addComponents(managementProductsHorizontalLayout,buttonsHorizontalLayout);
        root.addComponent(menuLayout);
    }

    private double getDefaultValueIfBlank(TextField capacityTextField, ProductDto selecterProductDto) {
        return capacityTextField.getValue().isBlank() ?
                selecterProductDto.getCapacity() : Double.parseDouble(capacityTextField.getValue());
    }

    private void validateProductFields(TextField nameTextfield, TextArea descriptionTextfield,
                                       TextField capacityTextField, TextField priceTextField, TextField amountTextField) {
        validationField.validBlankField(nameTextfield, capacityTextField, priceTextField, amountTextField);
        validationField.validFieldIsDouble(capacityTextField, priceTextField);
        validationField.validFieldIsInteger(amountTextField);
        validationField.validBlankArea(descriptionTextfield);
    }

    void navigateTo(String page){
        getUI().getNavigator().navigateTo(page);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        header();
        managementProduct();
    }
}
