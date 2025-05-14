package com.example.demo.Helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public final class FileHelper {

    private FileHelper(){}

    public static String fileupload(MultipartFile file){
        if(file== null || file.isEmpty()){return null;}

        try{

                String filename= UUID.randomUUID()+"_"+file.getOriginalFilename();
                //caminho relativo ao directorio do projecto
                Path uploadDir= Paths.get("uploads").toAbsolutePath().normalize();
                Files.createDirectories(uploadDir);
                Path filePath=uploadDir.resolve(filename);
                Files.write(filePath,file.getBytes());
            // Aqui usamos Files.copy com REPLACE_EXISTING
                Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        }catch(IOException e){
            throw new RuntimeException("Error upload the file");
        }

    }
}
