package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CategoryFacade {

    CategoryService categoryService;

    public Set<CategoryDto> findAll(){
        return categoryService.findAll();
    }
}
