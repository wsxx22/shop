package com.makeup.views;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

import java.util.stream.Stream;

class SettingsLayout {

    static void setSizeButtons(Button... buttons){
        Stream.of(buttons).forEach(button -> button.setWidth("150"));
    }

    static void setFullSizeTextFields(TextField... textFields){
        Stream.of(textFields).forEach(AbstractComponent::setSizeFull);
    }

    static void setSizeFullButtons(Button... buttons){
        Stream.of(buttons).forEach(AbstractComponent::setSizeFull);
    }
}
