package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.security.JwtUtil;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

@Controller
public class FileController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/upload")
    public @ResponseBody
    StringResponse handleFileUpload(@RequestParam("name") String name,
                                    @RequestParam("file") MultipartFile file,
                                    @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("D:/speedup-of-registration-server-2022/src/main/java/ru/vitaliy/petrov/server/uploaded/" + userID + name));
                stream.write(bytes);
                stream.close();
                return new StringResponse("Вы успешно загрузили файл");
            } catch (Exception e) {
                return new StringResponse("Вам не удалось загрузить файл из-за ошибки");
            }
        } else {
            return new StringResponse("Вам не удалось загрузить файл");
        }
    }

    @GetMapping("/download")
    public @ResponseBody
    FileSystemResource handleFileDownload(@RequestHeader("Authorization") String jwtToken, String fileName) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new FileSystemResource("D:/speedup-of-registration-server-2022/src/main/java/ru/vitaliy/petrov/server/uploaded/" + userID + fileName);
    }
}
