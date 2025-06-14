package br.selene.projectseleneback.infra.objectStorage;

import java.io.IOException;
import java.io.InputStream;

public interface IStorageService {
    void upload(String key, InputStream data, String contentType) throws IOException;
    byte[] download(String key);
    void delete(String key);
}
