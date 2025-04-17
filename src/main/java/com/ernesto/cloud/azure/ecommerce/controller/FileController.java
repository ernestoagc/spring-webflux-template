package com.ernesto.cloud.azure.ecommerce.controller;

import com.ernesto.cloud.azure.ecommerce.dto.FileResponseDto;
import com.ernesto.cloud.azure.ecommerce.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins="*")
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public Mono<ResponseEntity<FileResponseDto>> doUploadFilesPost(
            @RequestPart("files") Flux<FilePart> filePartFlux,
            ServerWebExchange exchange) {

       return fileService.uploadAsset(filePartFlux)
               .map(ResponseEntity::ok);

    }
}
