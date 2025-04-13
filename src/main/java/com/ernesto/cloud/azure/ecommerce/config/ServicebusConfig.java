package com.ernesto.cloud.azure.ecommerce.config;

import com.azure.messaging.servicebus.*;
import com.ernesto.cloud.azure.ecommerce.properties.AzureServiceBusProperties;
import com.ernesto.cloud.azure.ecommerce.properties.KeyVaultProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

@Configuration
@AllArgsConstructor
public class ServicebusConfig {

    private final String serviceBusSecret;

    @Bean
    @ConfigurationProperties(prefix = "application.ernestotech.azure.service-bus")
    public AzureServiceBusProperties AzureServiceBusProperties(){
        return new AzureServiceBusProperties();
    }

    @Bean
    ServiceBusClientBuilder serviceBusClientBuilder(
            AzureServiceBusProperties azureServiceBusProperties
            ) {
        return new ServiceBusClientBuilder()
                .fullyQualifiedNamespace(azureServiceBusProperties.getNamespace())
                .connectionString(serviceBusSecret);
    }



    @Bean
    ServiceBusSenderClient serviceBusSenderClient(ServiceBusClientBuilder builder,
                                                  AzureServiceBusProperties azureServiceBusProperties) {
        return builder
                .sender()
                .queueName(azureServiceBusProperties.getQueue())
                .buildClient();
    }

    @Bean
    ServiceBusSenderAsyncClient serviceBusSenderAsyncClient(ServiceBusClientBuilder builder,
                                                            AzureServiceBusProperties azureServiceBusProperties) {
        return builder
                .sender()
                .queueName(azureServiceBusProperties.getQueue())
                .buildAsyncClient();
    }

    @Bean
    ServiceBusProcessorClient serviceBusProcessorClient(ServiceBusClientBuilder builder,
                                                        AzureServiceBusProperties azureServiceBusProperties) {
        return builder.processor()
                .queueName(azureServiceBusProperties.getQueue())
                .processMessage(ServicebusConfig::processMessage)
                .processError(ServicebusConfig::processError)
                .buildProcessorClient();
    }

    private static void processMessage(ServiceBusReceivedMessageContext context) {
        ServiceBusReceivedMessage message = context.getMessage();
        System.out.printf("Processing message. Id: %s, Sequence #: %s. Contents: %s%n",
                message.getMessageId(), message.getSequenceNumber(), message.getBody());
    }

    private static void processError(ServiceBusErrorContext context) {
        System.out.printf("Error when receiving messages from namespace: '%s'. Entity: '%s'%n",
                context.getFullyQualifiedNamespace(), context.getEntityPath());
    }
}
