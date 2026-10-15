//package com.example.taskadministration.config;
//
//import com.example.taskadministration.security.oauth2.CustomOAuth2UserService;
//import com.example.taskadministration.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
//import com.example.taskadministration.security.oauth2.OAuth2AuthenticationFailureHandler;
//import com.example.taskadministration.security.oauth2.OAuth2AuthenticationSuccessHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtDecoders;
//import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private JwtAuthEntryPoint authEntryPoint;
//
//    private CustomUserDetailService customUserDetailService;
//
//    private JwtGenerator jwtGenerator;
//
////    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
//    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
//
//    private CustomOAuth2UserService customOAuth2UserService;
//
//
//    public SecurityConfig(CustomUserDetailService customUserDetailService,
//                          JwtAuthEntryPoint authEntryPoint,
//                          JwtGenerator jwtGenerator,
//                          CustomOAuth2UserService customOAuth2UserService,
//                          OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler,
//                          OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
//        this.customUserDetailService = customUserDetailService;
//        this.authEntryPoint = authEntryPoint;
//        this.jwtGenerator = jwtGenerator;
//        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
//        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
//        this.customOAuth2UserService = customOAuth2UserService;
//    }
//
//    @Bean
//    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository(){
//        return new HttpCookieOAuth2AuthorizationRequestRepository();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChains(HttpSecurity http) throws Exception{
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configure(http))
//                .exceptionHandling(authEntryPoint -> authEntryPoint.authenticationEntryPoint(this.authEntryPoint))
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
//                        .requestMatchers(
//                                new AntPathRequestMatcher("/task/registerUser"),
//                                new AntPathRequestMatcher("/task/loginUser")
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(AbstractHttpConfigurer::disable)
//                .httpBasic(AbstractHttpConfigurer::disable)
//
//                .sessionManagement(httpSecuritySessionManagementConfigurer ->
//                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2Login(oauth2Login -> oauth2Login
//                                .authorizationEndpoint(authorizationEndpointConfig ->  authorizationEndpointConfig
//                                .baseUri("/oauth2/authorize")
//                                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
//                                        )
//                                .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig.baseUri("/oauth2/callback/*"))
//                                .userInfoEndpoint(userInfoEndpointConfig ->  userInfoEndpointConfig.userService(customOAuth2UserService))
//                                .successHandler(oAuth2AuthenticationSuccessHandler)
//                                .failureHandler(oAuth2AuthenticationFailureHandler)
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt.decoder(this.jwtDecoder())
//                        )
//                );
//
//        return http.build();
//    }
//    //        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return JwtDecoders.fromIssuerLocation("http://localhost:8080/realms/TheHungryHub");
//    }
//
//}
//
