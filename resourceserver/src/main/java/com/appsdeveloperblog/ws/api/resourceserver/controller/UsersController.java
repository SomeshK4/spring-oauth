package com.appsdeveloperblog.ws.api.resourceserver.controller;

import com.appsdeveloperblog.ws.api.resourceserver.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    Environment environment;

    @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> status() {
        return ResponseEntity.ok()
                .body("Service is up and running on port :" + environment.getProperty("local.server.port"));
    }

    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")  //Check if the userid in JWT is same as supplied in the path variable
    //@PreAuthorize("hasAuthority('ROLE_developer')")   //Only user with role as developer can call this endpoint
    //@Secured("ROLE_developer")  //Role based access control
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok().body("Deleted user with id : " + id + " and JWT subject " + jwt.getSubject());
    }


    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        UserDTO userDTO = new UserDTO("Somesh Kumar", "12e3d4aa-6a67-458e-8718-75a92625f9bb");  //Replace the userId with sub value in the access token after debugging it from https://jwt.io/
        return userDTO;
    }

}
