package com.cfl.cfl_project.config;

import com.cfl.cfl_project.jwt.JwtFilter;
import com.cfl.cfl_project.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
public class SpringSecurityConfig {

    // step 1

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter) throws Exception {
        return httpSecurity.cors(cors->cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> req
                                .requestMatchers("register","login","getMail/{userName}",
                                        "mentor/**","cfl/**","refresh/**",
                                        "jwtWithRefreshToken/**","accept/**","Goals/**","cflSkill/**",
                                        "logbook/**","manager/**","managerRating/**","certificate/**",
                                        "menteeToMentorFeedBack/**","goalSettingTracker/**","probationTracker/**",
                                "questionRadio/**","memories/**","resume/**","videos/**","cflRoles/**",
                                "rewardsAndRecognition/**","probationConfirmation/**","mentorToMenteeFeedBack/**"
                                ,"managerToCflFeedBack/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // convert plain text password into Bcryptpassword

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // step 4
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        //return authenticationConfiguration.getAuthenticationManager();
        return new ProviderManager(authenticationProvider());
    }


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    // step 5
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

        // this line is searching the row based on username or useremail in db

        // here setUserDetailsService this require UserDetailsService return type
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);

        // this line is taking password which we enter during login and encode that password
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }


    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
}