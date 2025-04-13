package com.ernesto.cloud.azure.ecommerce.service;

import com.azure.security.keyvault.secrets.SecretAsyncClient;
import com.azure.security.keyvault.secrets.SecretClient;
import com.ernesto.cloud.azure.ecommerce.dto.ProductDto;
import com.ernesto.cloud.azure.ecommerce.dto.ProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final String testSecret;

    public Mono<ProductDto> register(ProductRequestDto productRequestDto){

        return Mono.just(ProductDto.builder()
                .code(productRequestDto.getCode())
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .build());
    }

    public Mono<ProductDto> getDetail(String id){
        return Mono.just(ProductDto.builder()
                .code("pro").name(testSecret)
                .build());
    }

    public Flux<ProductDto> fetchAll(){
      return  Flux.fromIterable(Collections.singletonList(ProductDto.builder()
              .code("pro").name("new product")
              .build()));
    }

}
