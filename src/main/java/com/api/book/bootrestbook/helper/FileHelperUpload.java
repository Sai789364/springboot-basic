package com.api.book.bootrestbook.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHelperUpload {
    public final String upload_dir = new ClassPathResource("static/image/").getFile().getAbsolutePath();

    public FileHelperUpload() throws IOException{

    }
    public Boolean uploadFile(MultipartFile multipartFile) {
        boolean f = false;
        try {
            Files.copy(multipartFile.getInputStream(),
                    Paths.get(upload_dir + File.separator + multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            f=true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
