package com.fileupload.springboot_file_upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fileupload.springboot_file_upload.service.FilesStorageService;

import jakarta.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootFileUploadApplication implements CommandLineRunner {

	@Resource
	  FilesStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootFileUploadApplication.class, args);
	}
	
	@Override
	  public void run(String... arg) throws Exception {
//	    storageService.deleteAll();
	    storageService.init();
	  }

}
