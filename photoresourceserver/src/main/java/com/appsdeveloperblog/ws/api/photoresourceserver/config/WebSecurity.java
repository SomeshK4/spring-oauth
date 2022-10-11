package com.appsdeveloperblog.ws.api.photoresourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Somesh Kumar
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurity {

    /**
     * Scope is a mechanism in OAuth 2.0 to limit an application’s access to a user’s account.
     * An application can request one or more scopes, this information is then presented to the user in the consent screen,
     * and the access token issued to the application will be limited to the scopes granted
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers(HttpMethod.GET, "/api/v1/photos")
                       // .hasAuthority("SCOPE_profile")   //used to limit application access to user's account on the basis of scope defined in the keycloak server
                        .hasRole("developer")
                        //.hasAnyRole("developer", "user")
                        //.hasAuthority("ROLE_developer")   //While using the hasAuthority we have to prefix with ROLE_ and SCOPE_ but while using hasRole we don't have to use any prefix. It is because in hasRole prefix is added by spring security automatically.
                         .anyRequest().authenticated())
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                        httpSecurityOAuth2ResourceServerConfigurer
                                .jwt()
                                .jwtAuthenticationConverter(jwtAuthenticationConverter));
        return http.build();
    }
}
