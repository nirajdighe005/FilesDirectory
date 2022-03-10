package com.example.fileDirectory.controllers;

import com.example.fileDirectory.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class FileStructureController {
    @Autowired
    FileService fileService;

    @GetMapping("/files")
    public String getFiles(Model model) {
        Model m = fileService.listFilesForFolder( model);
        return "files";
    }

    @RequestMapping(value="/files/download", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) throws IOException {
        ResponseEntity<Resource> body = fileService.getResponseEntity(fileName);
        return body;
    }
}
