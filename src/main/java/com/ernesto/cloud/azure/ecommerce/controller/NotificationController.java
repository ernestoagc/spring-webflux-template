package com.ernesto.cloud.azure.ecommerce.controller;

import com.ernesto.cloud.azure.ecommerce.dto.NotificationRequestDto;
import com.ernesto.cloud.azure.ecommerce.dto.NotificationResponsetDto;
import com.ernesto.cloud.azure.ecommerce.dto.ProductRequestDto;
import com.ernesto.cloud.azure.ecommerce.dto.ProductResponseDto;
import com.ernesto.cloud.azure.ecommerce.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/notification")
@CrossOrigin(origins="*")
@Slf4j
public class NotificationController {

    @Autowired
    NotificationService notificationService;


    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Mono<NotificationResponsetDto> create(@RequestBody NotificationRequestDto notificationRequestDto)  {
        log.info("===>Lega");
        return notificationService.sendNotification(notificationRequestDto);
    }
}
