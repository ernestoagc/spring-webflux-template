package com.ernesto.cloud.azure.ecommerce.config;

import com.azure.storage.blob.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class BlobStorageConfig {

    private final String blobStorageSecret;

    @Bean
    public BlobServiceAsyncClient blobServiceAsyncClient(){

        return new BlobServiceClientBuilder()
                .connectionString(blobStorageSecret)
                .buildAsyncClient();
    }

    @Bean
    public BlobContainerAsyncClient blobContainerAsyncClient(BlobServiceAsyncClient blobServiceAsyncClient){
        return blobServiceAsyncClient.getBlobContainerAsyncClient("contsternestotech");
    }
}
