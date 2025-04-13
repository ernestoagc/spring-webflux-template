package com.ernesto.cloud.azure.ecommerce.service;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.ernesto.cloud.azure.ecommerce.dto.NotificationRequestDto;
import com.ernesto.cloud.azure.ecommerce.dto.NotificationResponsetDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final ServiceBusSenderClient senderClient;
    private final ServiceBusSenderAsyncClient serviceBusSenderAsyncClient;


    public Mono<NotificationResponsetDto> sendNotification(NotificationRequestDto notificationRequestDto){

       return serviceBusSenderAsyncClient.sendMessage(
                new ServiceBusMessage(notificationRequestDto.getName())
        ).flatMap(x->Mono.just(NotificationResponsetDto.builder().id("se lgoro").build()) );
    }
}
