package com.makeup.views;

import com.makeup.order.domain.OrderFacade;
import com.makeup.order.domain.dto.OrderDto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.format.DateTimeFormatter;

import static com.makeup.utils.Constant.Label.STORE_NAME;
import static com.makeup.utils.Constant.Label.WELCOME_USER;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringView(name = "history-orders")
public class OrderHistoryView extends Composite implements View {

    VerticalLayout root;
    VerticalLayout menuLayout;
    OrderFacade orderFacade;

    public OrderHistoryView(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setCompositionRoot(root);
    }

    private void header(){
        Label nameShopLabel = new Label(STORE_NAME);
        Label welcomeNameLabel = new Label("These are your orders.");
        root.addComponents(nameShopLabel, welcomeNameLabel);

        menuLayout = new VerticalLayout();
        menuLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

    private void products(){
        Grid<OrderDto> ordersDto = new Grid<>("Orders:");
        ordersDto.setWidth("600");
        ordersDto.setItems(orderFacade.findAllByLoggedUser());
        ordersDto.addColumn(orderDto -> orderDto.getProducts().iterator().next().getName()).setCaption("Products");
        ordersDto.addColumn(OrderDto::getTotalPrice).setCaption("Total price");
        ordersDto.addColumn(orderDto -> orderDto.getOrderTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))).setCaption("Order time").setId("orderTime");
        ordersDto.sort("orderTime", SortDirection.DESCENDING);

        Button returnButton = new Button("Return");
        returnButton.addClickListener(clickEvent -> getUI().getNavigator().navigateTo("homepage"));

        menuLayout.addComponents(ordersDto, returnButton);
        root.addComponent(menuLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupLayout();
        header();
        products();
    }
}
