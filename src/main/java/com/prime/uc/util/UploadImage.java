package com.prime.uc.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class UploadImage {
   public static void saveImage(MultipartFile file ,String  path) {
	MultipartFile f = file;
	f.getName();
	String folderPath = "/home/jayalakshmi/ucapp/users/username_id/insurance/";
	 File theDir = new File(path);
    OutputStream outputStream = null;
    if (!theDir.exists()) {
    	 theDir.mkdirs();

    }
    try {
    	
        Files.write(Paths.get(path + f.getOriginalFilename()), f.getBytes());
    } catch (IOException e) {
        e.printStackTrace();
    }
    
   }
}
