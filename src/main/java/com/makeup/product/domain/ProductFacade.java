package com.makeup.product.domain;

import com.makeup.product.domain.dto.CreateProductDto;
import com.makeup.product.domain.dto.ProductDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ProductFacade {

    ProductService productService;

    public void addProduct(CreateProductDto createProductDto){
        productService.addProduct(createProductDto);
    }

    public Set<ProductDto> findAllProducts(){
        return productService.findAllProducts();
    }

    public void update(ProductDto productDto){
        productService.update(productDto);
    }
}
