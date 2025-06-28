package br.selene.projectseleneback.presentation;

import br.selene.projectseleneback.infra.objectStorage.IStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

    private final IStorageService storageService;

    public ArquivoController(IStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String key = file.getOriginalFilename();
        storageService.upload(key, file.getInputStream(), file.getContentType());
        return ResponseEntity.ok("Arquivo enviado com sucesso: " + key);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {
        byte[] data = storageService.download(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(data);
    }
}
