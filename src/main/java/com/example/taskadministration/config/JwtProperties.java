//package com.example.taskadministration.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.constraints.NotEmpty;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class JwtProperties {
//    private final OAuth2 oauth2;
//
//    public JwtProperties(JwtConfiguration jwtConfiguration){
//        ArrayList<String> authorizedRedirectUrisVal = new ArrayList<>(jwtConfiguration.getAuthorizedRedirectUris());
//        this.oauth2 = new OAuth2(authorizedRedirectUrisVal);
//    }
//
//    public OAuth2 getOauth2() {
//        return oauth2;
//    }
//
//    public static final class OAuth2 {
//        private List<String> authorizedRedirectUris;
//
//        public OAuth2(List<String> authorizedRedirectUris){
//            this.authorizedRedirectUris = authorizedRedirectUris;
//        }
//
//        public List<String> getAuthorizedRedirectUris() {
//            return authorizedRedirectUris;
//        }
//
//        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
//            this.authorizedRedirectUris = authorizedRedirectUris;
//            return this;
//        }
//    }
//
//    @Configuration
//    @Validated
//    static class JwtConfiguration{
//        @NotEmpty
//        private List<String> authorizedRedirectUris = new ArrayList<>();
//
//        public List<String> getAuthorizedRedirectUris() {
//            return authorizedRedirectUris;
//        }
//
//        public void setAuthorizedRedirectUris(List<String> authorizedRedirectUris) {
//            this.authorizedRedirectUris = authorizedRedirectUris;
//        }
//    }
//
//
//}
