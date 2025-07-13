package br.selene.projectseleneback.infra.objectStorage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class S3StorageService implements IStorageService{

    private final S3Client s3Client;
    private final String bucket;

    public S3StorageService(S3Client s3Client, @Value("${storage.s3.bucket}") String bucket) {
        this.s3Client = s3Client;
        this.bucket = bucket;
    }

    @Override
    public void upload(String key, InputStream data, String contentType) throws IOException {

        String fullKey = "public/" + key;

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(fullKey)
                        .contentType(contentType)
                        .build(),
                RequestBody.fromInputStream(data, data.available())
        );
    }

    @Override
    public byte[] download(String key) {
        return s3Client.getObjectAsBytes(GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build())
                .asByteArray();
    }

    @Override
    public void delete(String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build());
    }

    @Override
    public String getName(String key) {
        // Gera o timestamp no padrão BR
        String timestamp = new SimpleDateFormat("ssmmHH_ddMMyyyy").format(new Date());

        // Extrai nome base e extensão
        String extension = "";
        int dotIndex = key.lastIndexOf('.');
        if (dotIndex != -1) {
            extension = key.substring(dotIndex); // inclui o ponto
        }

        // Retorna apenas o timestamp + extensão
        return timestamp + extension;
    }

}
