package com.example.demo;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @GetMapping("/")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        // Logic to handle the uploaded file
        if (!file.isEmpty()) {
            // You can save the file to a specific directory, process it, etc.
            // For demonstration purposes, let's just set a success message.
            System.out.println(file.getOriginalFilename());
                        // Define the directory to save the uploaded file
           // String uploadDir = "~/Deployment/";
              // Get the current working directory
              String currentWorkingDir = System.getProperty("user.dir");
                
              // Create a Path for the target file using the current working directory
              Path targetPath = Paths.get(currentWorkingDir + "/" + file.getOriginalFilename());
            // Create a Path object for the target file
            //Path targetPath = Paths.get(uploadDir + file.getOriginalFilename());
            
            // Save the file content to the target directory
            try {
                Files.write(targetPath, file.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Error : " + e);
            }
            
            try {
                System.out.println(file.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Error : " + e);
            }
            
            model.addAttribute("message", "File uploaded successfully!");
        } else {
            model.addAttribute("message", "Please select a file to upload.");
        }
        return "upload";
    }
}