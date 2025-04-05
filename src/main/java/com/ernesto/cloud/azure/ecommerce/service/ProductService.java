package com.ernesto.cloud.azure.ecommerce.service;

import com.ernesto.cloud.azure.ecommerce.dto.ProductDto;
import com.ernesto.cloud.azure.ecommerce.dto.ProductRequestDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class ProductService {

    public Mono<ProductDto> register(ProductRequestDto productRequestDto){
        return Mono.just(ProductDto.builder()
                .code(productRequestDto.getCode())
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .build());
    }

    public Mono<ProductDto> getDetail(String id){
        return Mono.just(ProductDto.builder()
                .code("pro").name("new product")
                .build());
    }

    public Flux<ProductDto> fetchAll(){
      return  Flux.fromIterable( Arrays.asList(ProductDto.builder()
                .code("pro").name("new product")
                .build()));
    }

}
