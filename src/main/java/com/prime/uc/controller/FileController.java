package com.prime.uc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prime.service.UcFileService;
import com.prime.service.UserInsuranceService;
import com.prime.uc.model.User;
import com.prime.uc.repo.UserRepo;
import com.prime.uc.util.UploadImage;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private UcFileService ucFileService;
	
	@Autowired
	private UserInsuranceService userInsuranceService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Value("${basePath}")
	private String imageBase;
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody User uploadFile(@RequestPart("files") MultipartFile file,
			@RequestParam("userId") UUID userId 
			//@RequestParam("folderPath") String folderPath
			) {
		List<User> u = userRepo.getUserById(userId);
		String userName =u.get(0).getName();
		String imageBase = "/home/jayalakshmi/ucapp/users/"+userName+"_"+ userId+"/profile/";
		
		UploadImage.saveImage(file,imageBase);
		//files.stream().forEach(f-> UploadImage.saveImage(f, ""));		
		//@RequestPart("userInsurance") UserInsurance userInsurance
		//final UserInsurance userInsurance1 =  userInsuranceService.saveUserInsurance(userInsurance);
		//files.stream().forEach(f-> UploadImage.saveImage(f, folderPath));
		
//		List<UCFile> ucFiles = files.stream().map(f -> {
//			UCFile ucFile = new UCFile();
//			ucFile.setFileName(f.getOriginalFilename());
//			ucFile.setMimeType(f.getContentType());
//			UserInsurance u = new UserInsurance();
//			u.setId(userInsuranceId);
//			ucFile.setProperty(u);
//			ucFile.setPath(folderPath+f.getOriginalFilename());
//			return ucFile;
//		}).collect(Collectors.toList());
//		
//		ucFileService.saveUcFiles(ucFiles);
	u.get(0).setImageUrl(imageBase+file.getOriginalFilename());
	
	return userRepo.save(u.get(0));
	}
	
	@GetMapping(
		  value = "/get-image",
		  produces = MediaType.IMAGE_JPEG_VALUE
		)
		public @ResponseBody byte[] getImageWithMediaType(String path) throws IOException {
		    InputStream in = new FileInputStream(new File(path));
		    return IOUtils.toByteArray(in);
		}
}
