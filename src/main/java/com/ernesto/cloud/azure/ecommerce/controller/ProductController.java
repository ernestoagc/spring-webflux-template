package com.ernesto.cloud.azure.ecommerce.controller;

import com.ernesto.cloud.azure.ecommerce.dto.ProductDto;
import com.ernesto.cloud.azure.ecommerce.dto.ProductRequestDto;
import com.ernesto.cloud.azure.ecommerce.dto.ProductResponseDto;
import com.ernesto.cloud.azure.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins="*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value="/{id}")
    @ResponseBody
    public Mono<ProductDto> getById(@PathVariable String id)   {
       return productService.getDetail(id);
    }


    @PostMapping("/")
    @ResponseBody
    public Mono<ProductResponseDto> create(@RequestBody ProductRequestDto currencyExchangeDto)  {
        return Mono.just(ProductResponseDto.builder().id(2).build()) ;
    }

    @GetMapping(value="/fetch")
    @ResponseBody
    public Flux<ProductDto> fetchAll()   {
        return productService.fetchAll();
    }

}
