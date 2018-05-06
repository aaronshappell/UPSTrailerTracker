package com.ups.UPSTrailerTracker;

import com.ups.UPSTrailerTracker.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
        storageService.store(file);
        return "Successfully uploaded " + file.getOriginalFilename();
    }

    /*
    Will we need GET to retrieve uploaded files?
    Or just temporarily upload to extract data and then delete?
     */
}
