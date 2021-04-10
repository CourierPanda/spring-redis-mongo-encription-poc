package com.courierpanda.controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.courierpanda.model.Photo;
import com.courierpanda.repository.PhotoRepository;

@RestController
public class PhotoController {
	@Autowired
    private PhotoRepository photoRepo;
	@PostMapping("/photos/add")
	public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model) throws IOException {
		
		 Photo photo = new Photo(title); 
	        photo.setImage(
	          new Binary(BsonBinarySubType.BINARY, image.getBytes())); 
	        photo = photoRepo.insert(photo); 
	        return photo.getId();
		
	}
	@GetMapping("/photos/{id}")
	public void getPhoto(@PathVariable String id, Model model) throws IOException { 
        Photo photo = photoRepo.findById(id).get(); 
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image", 
          Base64.getEncoder().encodeToString(photo.getImage().getData()));
        String incodedString = Base64.getEncoder().encodeToString(photo.getImage().getData());
        byte[] img1 = Base64.getDecoder().decode(incodedString);

        FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/rvknt/OneDrive/Desktop/ML/"+photo.getTitle()+".png");
        try {
			fileOutputStream.write(Base64.getDecoder().decode(incodedString));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
        	fileOutputStream.close();
        }
    }
}
