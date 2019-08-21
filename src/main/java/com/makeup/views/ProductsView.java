package com.makeup.views;

import com.makeup.product.domain.ProductFacade;
import com.makeup.product.domain.dto.ProductDto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "products")
public class ProductsView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    ProductFacade productFacade;

    public ProductsView(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    private void setup(){
        root = new VerticalLayout();
        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label headerLabel = new Label("MakeUp Shop Products");
        menuLayout.addComponents(headerLabel);
    }

    private void products(){
        HorizontalLayout navigateButtonsLayout = new HorizontalLayout();
        Grid<ProductDto> productsGrid = new Grid<>("All products in our shop:");
        productsGrid.setWidth("800");
        productsGrid.setItems(productFacade.findAllProducts());
        productsGrid.addColumn(ProductDto::getName).setCaption("Name");
        productsGrid.addColumn(ProductDto::getDescription).setCaption("Description");
        productsGrid.addColumn(ProductDto::getCapacity).setCaption("Capacity");
        productsGrid.addColumn(ProductDto::getAmount).setCaption("Amount");
        productsGrid.addColumn(ProductDto::getPrice).setCaption("Price");

        Button buyProductsButton = new Button("Buy");
        Button returnButton = new Button("Return");

        returnButton.addClickListener(clickEvent -> navigateTo("homepage"));

        navigateButtonsLayout.addComponents(buyProductsButton,returnButton);
        menuLayout.addComponents(productsGrid, navigateButtonsLayout);
        root.addComponent(menuLayout);
    }

    private void authorized(){

    }

    private void unauthorized(){

    }

    private void navigateTo(String navigateLayout){
        getUI().getNavigator().navigateTo(navigateLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setup();
        header();
        products();
    }
}
