package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapperImpl categoryMapper;

    Set<CategoryDto> findAll(){
        return categoryMapper.toDtoSet(categoryRepository.findAll());
    }

}
