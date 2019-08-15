package com.makeup.views;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.UI;

@SpringUI
@SpringViewDisplay()
public class InitUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getUI().getNavigator().navigateTo("homepage");
        getNavigator().setErrorView(InitUI.getCurrent().getNavigator().getCurrentView());

    }
}
