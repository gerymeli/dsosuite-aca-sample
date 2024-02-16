package com.iqera.dsosuiteaca;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @Value("${dsosuite.azure.storage.blob.endpoint}")
    private String blobStorageEndpoint;

    @Value("${dsosuite.azure.storage.sastoken}")
    private String storageSasToken;

    public BlobServiceClient createBlobServiceClient(){
        return new BlobServiceClientBuilder()
                .endpoint(blobStorageEndpoint)
                .sasToken(storageSasToken)
                .buildClient();
    }

    public BlobContainerClient createBlobContainerClient(String containerName){
        return new BlobContainerClientBuilder()
                .endpoint(blobStorageEndpoint)
                .sasToken(storageSasToken)
                .containerName(containerName)
                .buildClient();
    }

    @RequestMapping("/")
    public String home() {
    return "Hello World from Azure Container Apps";
    }

    public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    }

}