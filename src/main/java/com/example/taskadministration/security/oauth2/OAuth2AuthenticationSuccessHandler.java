//package com.example.taskadministration.security.oauth2;
//
//import com.example.taskadministration.config.JwtProperties;
//import com.example.taskadministration.error.BadRequestException;
//import com.example.taskadministration.util.CookieUtils;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.Optional;
//
//import static com.example.taskadministration.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
//
//
//@Component
//public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    private JwtProperties jwtProperties;
//
//    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
//    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
//
//    @Autowired
//    OAuth2AuthenticationSuccessHandler(JwtProperties jwtProperties,
//                                       HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository,
//                                       OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
//
//        this.jwtProperties = jwtProperties;
//        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
//        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//        String targetUrl = determineTargetUrl(request, response, authentication);
//
//        if (response.isCommitted()) {
//            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
//            return;
//        }
//
//        clearAuthenticationAttributes(request, response);
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);
//    }
//
//    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
//        super.clearAuthenticationAttributes(request);
//        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
//    }
//
//    private boolean isAuthorizedRedirectUri(String uri) {
//        URI clientRedirectUri = URI.create(uri);
//
//        return this.jwtProperties.getOauth2().getAuthorizedRedirectUris()
//                .stream()
//                .anyMatch(authorizedRedirectUri -> {
//                    // Only validate host and port. Let the clients use different paths if they want to
//                    URI authorizedURI = URI.create(authorizedRedirectUri);
//                    if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
//                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
//                        return true;
//                    }
//                    return false;
//                });
//    }
//
//    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
//                .map(Cookie::getValue);
//
//        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
//        }
//
//        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
//
//        OAuth2AccessToken token = this.oAuth2AuthorizedClientService.loadAuthorizedClient(
//                ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId(), authentication.getName()).getAccessToken();
//
//        return UriComponentsBuilder.fromUriString(targetUrl)
//                .queryParam("token", token)
//                .build().toUriString();
//    }
//
//}
