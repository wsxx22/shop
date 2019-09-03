package com.makeup.views;

import com.makeup.product.domain.dto.ProductDto;
import com.makeup.views.message.ViewMessage;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

import static com.makeup.views.message.ViewMessage.CAUSE.AMOUNT_RANGE;
import static com.makeup.views.message.ViewMessage.CAUSE.COULD_NOT_BE_BLANK;
import static com.makeup.views.message.ViewMessage.CAUSE.FIELD_MUST_BE_DIGIT;
import static com.makeup.views.message.ViewMessage.CAUSE.FIELD_MUST_BE_INTEGER;

class ValidationField {

    void validFieldIsDouble(TextField... textFields){
        Stream.of(textFields).forEach(field -> {
                    boolean dot = false;
                    for(char c : field.getValue().toCharArray()){
                        if (isDigit(c)) continue;
                        if (c == '.'){
                            if (!dot){
                                dot=true;
                                continue;
                            }
                        }
                        throw new ViewMessage(String.format(FIELD_MUST_BE_DIGIT.getMessage(), field.getCaption()));
                    }
                });
    }

    void validFieldIsInteger(TextField... textFields){
        Stream.of(textFields).forEach(field -> {
            for(char c : field.getValue().toCharArray()) {
                if (isDigit(c)) {
                } else {
                    throw new ViewMessage(String.format(FIELD_MUST_BE_INTEGER.getMessage(), field.getCaption()));
                }
            }
        });
    }

    void validBlankField(TextField... textFields) {
        Stream.of(textFields)
                .filter(field -> StringUtils.isBlank(field.getValue()))
                .forEach(field -> {
                    throw new ViewMessage(String.format(COULD_NOT_BE_BLANK.getMessage(), field.getCaption()));
                });
    }

    void validBlankArea(TextArea... textAreas) {
        Stream.of(textAreas)
                .filter(area -> StringUtils.isBlank(area.getValue()))
                .forEach(area -> {
                    throw new ViewMessage(String.format(COULD_NOT_BE_BLANK.getMessage(), area.getCaption()));
                });
    }

    void validateAmount(TextField amountToBuyTextField, Grid<ProductDto> productsGrid){
        validFieldIsInteger(amountToBuyTextField);
        int amountToBuy = Integer.parseInt(amountToBuyTextField.getValue());
        int amountInStore = productsGrid.getSelectedItems().iterator().next().getAmount();

        if (amountToBuy > amountInStore || amountToBuy < 0) {
            throw new ViewMessage(String.format(AMOUNT_RANGE.getMessage(), amountInStore));
        }
    }

    private boolean isDigit(char c) {
        if (c == '0' | c == '1' | c == '2' | c == '3' | c == '4' | c == '5' | c == '6' | c == '7' | c == '8' | c == '9') {
            return true;
        }
        return false;
    }

}
