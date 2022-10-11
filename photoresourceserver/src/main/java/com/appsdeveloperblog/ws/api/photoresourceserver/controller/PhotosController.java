/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.ws.api.photoresourceserver.controller;

import com.appsdeveloperblog.ws.api.photoresourceserver.dto.PhotoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/photos")
public class PhotosController {
    
    @GetMapping
    public List<PhotoDTO> getPhotos() {

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setAlbumId("albumIdHere");
        photoDTO.setPhotoId("1");
        photoDTO.setUserId("1");
        photoDTO.setPhotoTitle("Photo 1 title");
        photoDTO.setPhotoDescription("Photo 1 description");
        photoDTO.setPhotoUrl("Photo 1 URL");

        PhotoDTO photoDTO1 = new PhotoDTO();
        photoDTO1.setAlbumId("albumIdHere");
        photoDTO1.setPhotoId("2");
        photoDTO1.setUserId("1");
        photoDTO1.setPhotoTitle("Photo 2 title");
        photoDTO1.setPhotoDescription("Photo 2 description");
        photoDTO1.setPhotoUrl("Photo 2 URL");
         
        return Arrays.asList(photoDTO, photoDTO1);
    }
 
}
