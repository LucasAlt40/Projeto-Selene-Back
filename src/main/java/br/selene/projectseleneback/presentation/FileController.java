package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.infra.objectStorage.IStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    private final IStorageService storageService;

    public FileController(IStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String key = file.getOriginalFilename();
        String newKey = storageService.getName(key);
        storageService.upload(newKey, file.getInputStream(), file.getContentType());
        return ResponseEntity.ok(newKey);
    }

}
