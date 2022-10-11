package com.appsdeveloperblog.ws.clients.photoappwebclient.controller;

import com.appsdeveloperblog.ws.clients.photoappwebclient.model.AlbumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class AlbumController {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    private static final String URL = "http://localhost:8060/api/v1/albums";

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser oidcUser) {

        /**
         *  First approach to call the api using RestTemplate. Consequences of this
         *  approach is that everytime we have to call the protected api's we have
         *  to get the access token and set it in the headers for every request.
         *
         *  We can use WebFlux WebClient class to get rid of all this plumbing
         *  work.
         *
         */
        /* var authentication = SecurityContextHolder.getContext().getAuthentication();
        var oauth2AuthToken = (OAuth2AuthenticationToken)authentication;

        var oAuth2AuthorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(oauth2AuthToken.getAuthorizedClientRegistrationId(),
                oauth2AuthToken.getName());

        var accessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();

        System.out.println("JWT access token : " + accessToken);

        System.out.println("Principal : " + oidcUser);
        var idToken = oidcUser.getIdToken();
        System.out.println("Token value : " + idToken.getTokenValue());


        //Read the albums from albums resource server
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+accessToken);
        var httpEntity = new HttpEntity<List<AlbumDTO>>(headers);
        ResponseEntity<List<AlbumDTO>> response = restTemplate.exchange(URL, HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<>() {}
        );

        model.addAttribute("albums", response.getBody()); */


        /**
         * Second approach to use Webclient to call the api
         */

        List<AlbumDTO> albums = webClient
                .get()
                .uri(URL)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AlbumDTO>>() {
                }).block();

        model.addAttribute("albums", albums);

        //Hard coded albums list is commented out
        /**
         AlbumDTO albums = new AlbumDTO();
        albums.setAlbumId("Album Id 1");
        albums.setAlbumTitle("Album Title 1");
        albums.setAlbumUrl("http://localhost:80/albums/1");

         AlbumDTO albums1 = new AlbumDTO();
        albums1.setAlbumId("Album Id 2");
        albums1.setAlbumTitle("Album Title 2");
        albums1.setAlbumUrl("http://localhost:80/albums/2");

        model.addAttribute("albums", Arrays.asList(albums, albums1)); **/
        return "albums";
    }
}
