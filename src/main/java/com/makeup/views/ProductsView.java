package com.makeup.views;

import com.makeup.order.domain.OrderFacade;
import com.makeup.order.domain.dto.CreateOrderDto;
import com.makeup.product.domain.ProductFacade;
import com.makeup.product.domain.dto.ProductDto;
import com.makeup.utils.GlobalAuthorization;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import static com.makeup.views.message.ViewMessage.CAUSE.LOG_IN;
import static com.makeup.views.message.ViewMessage.CAUSE.PRODUCT_BOUGHT;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "products")
public class ProductsView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    ProductFacade productFacade;
    OrderFacade orderFacade;
    ValidationField validationField;

    public ProductsView(ProductFacade productFacade, OrderFacade orderFacade) {
        this.productFacade = productFacade;
        this.orderFacade = orderFacade;
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
        validationField = new ValidationField();
        HorizontalLayout navigateButtonsLayout = new HorizontalLayout();
        navigateButtonsLayout.setDefaultComponentAlignment(Alignment.BOTTOM_CENTER);
        Grid<ProductDto> productsGrid = new Grid<>("All products in our shop. Select any and buy");
        productsGrid.setWidth("800");
        productsGrid.setItems(productFacade.findAllProducts());
        productsGrid.addColumn(ProductDto::getName).setCaption("Name").setId("name");
        productsGrid.addColumn(ProductDto::getDescription).setCaption("Description");
        productsGrid.addColumn(ProductDto::getCapacity).setCaption("Capacity");
        productsGrid.addColumn(ProductDto::getAmount).setCaption("Amount");
        productsGrid.addColumn(ProductDto::getPrice).setCaption("Price");
        productsGrid.sort("name", SortDirection.ASCENDING);

        Button buyProductsButton = new Button("Buy");
        TextField amountToBuyTextField = new TextField("Amount to buy:");
        Button returnButton = new Button("Return");

        buyProductsButton.addClickListener(clickEvent ->{
            if (!GlobalAuthorization.isAuthorized()){
                Notification.show(LOG_IN.getMessage()).setDelayMsec(1500);
            } else if (productsGrid.getSelectedItems().size() > 0 && !amountToBuyTextField.isEmpty()){
                validationField.validateAmount(amountToBuyTextField, productsGrid);
                CreateOrderDto createOrderDto = new CreateOrderDto(GlobalAuthorization.name, productsGrid.getSelectedItems());
                createOrderDto.changeAmount(Integer.parseInt(amountToBuyTextField.getValue()));
                orderFacade.addOrder(createOrderDto);
                Notification.show(String.format(
                        PRODUCT_BOUGHT.getMessage(), productsGrid.getSelectedItems().iterator().next().getName()));
                getUI().getNavigator().navigateTo("products");
            }
        });

        returnButton.addClickListener(clickEvent -> navigateTo("homepage"));

        navigateButtonsLayout.addComponents(amountToBuyTextField,buyProductsButton,returnButton);
        menuLayout.addComponents(productsGrid, navigateButtonsLayout);
        root.addComponent(menuLayout);
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
