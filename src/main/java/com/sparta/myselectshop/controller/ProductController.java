package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ProductController {

    private final ProductService productService;
    //관심상품등록
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(productRequestDto,userDetails.getUser());
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct
            (@PathVariable Long id,@RequestBody ProductMypriceRequestDto productRequestDto) {
        return productService.updateProduct(id,productRequestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ){
        return productService.getProducts(userDetails.getUser()
        ,page-1,size,sortBy,isAsc);
    }

    @PostMapping("/products/{productId}/folder")
    public void addFolderToProduct(@PathVariable Long productId, @RequestParam Long folderId, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        productService.addFolder(productId,folderId,userDetails.getUser());
    }

}
