package com.ernesto.cloud.azure.ecommerce.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.ernesto.cloud.azure.ecommerce.dto.FileResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@AllArgsConstructor
@Service
public class FileService {

   private final BlobContainerAsyncClient blobContainerAsyncClient;

   public Mono<FileResponseDto> uploadAsset(Flux<FilePart> filePartFlux){

     return  filePartFlux.flatMap(filePart ->
               filePart.content().flatMap(dataBuffer -> {
                   BinaryData binaryData=  BinaryData.fromStream(dataBuffer.asInputStream());
                   String fileNameCloud = UUID.randomUUID().toString() + filePart.filename().substring(filePart.filename().lastIndexOf("."));

                   return   blobContainerAsyncClient.getBlobAsyncClient(fileNameCloud)
                           .upload(binaryData)
                           .map(fil-> blobContainerAsyncClient.getBlobContainerUrl()+"/"+fileNameCloud);

               }))
           .collect(Collectors.toList())
           .flatMap(urls->Mono.just(FileResponseDto.builder().url(urls.get(0)).build()));

    }
}
