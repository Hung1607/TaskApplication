//package com.example.taskadministration.security.oauth2.user;
//
//import java.util.List;
//import java.util.Map;
//
//public class KeycloakOauthUserInfo extends OAuth2UserInfo{
//    public KeycloakOauthUserInfo(Map<String, Object> attributes){
//        super(attributes);
//    }
//    @Override
//    public String getId() {
//        return ((Integer) attributes.get("id")).toString();
//    }
//
//    @Override
//    public String getName() {
//        return (String) attributes.get("name");
//    }
//
//    @Override
//    public String getEmail() {
//        return (String) attributes.get("email");
//    }
//
//    @Override
//    public List<String> getRoles() {
//        return (List<String>) attributes.get("groups");
//    }
//
//    @Override
//    public String getImageUrl() {
//        return (String) attributes.get("avatar_url");
//    }
//}
