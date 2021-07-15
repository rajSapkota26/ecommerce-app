package com.mbs.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mbs.model.MyImage;

@RestController
@CrossOrigin("*")
public class ImageUploads {
	
	
	@PostMapping(value="/uploading",produces="application/json")
	@ResponseBody
	public MyImage uploadImage(@RequestBody MultipartFile file) {
		MyImage imag=null;
		String imageUrl="";
		
		System.out.println("request comming");
		try {			
			
			long currentTimeMillis = System.currentTimeMillis();
			
			imageUrl=String.valueOf(currentTimeMillis)+file.getOriginalFilename();
			System.out.println(imageUrl);
			boolean uploadFile = uploadFile(file,imageUrl);
			if(uploadFile) {
				System.out.println("done");
				
				imag=new MyImage();
		
				imag.setName((ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(imageUrl).toUriString()));
			return 	imag;
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return imag ;
	}
	
	
	public boolean uploadFile(MultipartFile file,String imageName) {
		boolean f=false;
		System.out.println(imageName);
		try {
			String UPLOAD_DIR1=new ClassPathResource("static/images").getFile().getAbsolutePath();	
//			String UPLOAD_DIR="C:\\Users\\ASUS\\eclipse-workspace\\myshop\\src\\main\\resources\\static\\images";	
			Files.copy(file.getInputStream(),Paths.get(UPLOAD_DIR1+File.separator+imageName),StandardCopyOption.REPLACE_EXISTING );
			System.out.println("done");
			return true;		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}

}
