/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.ws.api.albumresourceserver.controller;

import com.appsdeveloperblog.ws.api.albumresourceserver.dto.AlbumDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumsController {
    
    @GetMapping
    public List<AlbumDTO> getAlbums() {

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setAlbumId("albumIdHere");
        albumDTO.setUserId("1");
        albumDTO.setAlbumTitle("Album 1 title");
        albumDTO.setAlbumDescription("Album 1 description");
        albumDTO.setAlbumUrl("Album 1 URL");

        AlbumDTO albumDTO1 = new AlbumDTO();
        albumDTO1.setAlbumId("albumIdHere");
        albumDTO1.setUserId("2");
        albumDTO1.setAlbumTitle("Album 2 title");
        albumDTO1.setAlbumDescription("Album 2 description");
        albumDTO1.setAlbumUrl("Album 2 URL");
         
        return Arrays.asList(albumDTO, albumDTO1);
    }
 
}
