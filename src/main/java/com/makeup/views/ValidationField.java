package com.makeup.views;

import com.makeup.product.domain.dto.ProductDto;
import com.makeup.views.message.ViewMessage;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

import static com.makeup.views.message.ViewMessage.CAUSE.*;

class ValidationField {

    void validFieldIsDouble(TextField... textFields){
        Stream.of(textFields).forEach(field -> {
            if (isDigit(field.getValue())){
                throw new ViewMessage(String.format(FIELD_MUST_BE_DIGIT.getMessage(), field.getCaption()));
            }
            boolean dot = false;
            for(char c : field.getValue().toCharArray()){
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
            if (!isDigit(field.getValue())){
                throw new ViewMessage(String.format(NOT_BE_LESS_THAN_ZERO.getMessage(), field.getCaption()));
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

        if (amountToBuy == 0) {
            throw new ViewMessage(AMOUNT_COULD_NOT_BE_ZERO.getMessage());
        }
        if (amountToBuy > amountInStore) {
            throw new ViewMessage(String.format(AMOUNT_TO_BUY_RANGE.getMessage(), amountInStore));
        }
    }

    private boolean isDigit(String value){
        return StringUtils.isNumericSpace(value);
    }
}
