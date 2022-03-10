package com.example.fileDirectory.services;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FileService {

    //TODO: PLEASE ENTER DIRECTORY OF YOUR CHOICE
    public static final String DIRECTORY_NAME = "";
    public static final String CONTENT_DISPOSITION_FORMAT = "attachment; filename=\"%s\"";
    public static final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
    public static final String FILE_ATTRIBUTE_NAME = "files";

    public Model listFilesForFolder( Model model) {
        final File folder = new File(DIRECTORY_NAME);
        List<File> filesInDir = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (!fileEntry.isDirectory()) {
                filesInDir.add(fileEntry);
            }
        }
        model.addAttribute(FILE_ATTRIBUTE_NAME, filesInDir);
        return model;
    }

    public ResponseEntity<Resource> getResponseEntity(String fileName) throws IOException {

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Path.of(DIRECTORY_NAME + "\\" + fileName
        )));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(CONTENT_DISPOSITION_HEADER, String.format(CONTENT_DISPOSITION_FORMAT, fileName))
                .body(resource);
    }
}
