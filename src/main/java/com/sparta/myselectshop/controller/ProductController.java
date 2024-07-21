package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.Service.ProductService;
import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ProductController {

    private final ProductService productService;
    //관심상품등록
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct
            (@PathVariable Long id,@RequestBody ProductMypriceRequestDto productRequestDto) {
        return productService.updateProduct(id,productRequestDto);
    }
}
