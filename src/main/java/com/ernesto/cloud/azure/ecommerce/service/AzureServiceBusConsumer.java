package com.ernesto.cloud.azure.ecommerce.service;

import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AzureServiceBusConsumer {


    private final ServiceBusProcessorClient serviceBusProcessorClient;

    public void startSessionProcessor() {
        serviceBusProcessorClient.start();
    }

    @PostConstruct
    public void init() {
        startSessionProcessor();
    }
}
