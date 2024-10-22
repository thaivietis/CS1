package com.nqt.cs1.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public SpringSecurityConfig(){
        super();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .successHandler(customSuccessHandler())
                        .failureUrl("/erorr")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/index"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID").invalidateHttpSession(true))
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE)
                        .permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/images/**"),
                                new AntPathRequestMatcher("/assets/**"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/auth/**")).permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/admin/**"),
                                new AntPathRequestMatcher("/department/**"),
                                new AntPathRequestMatcher("/infomation/**"),
                                new AntPathRequestMatcher("/user/**"),
                                new AntPathRequestMatcher("/employee/**")).hasRole("ADMIN")
//                      Test với người dùng có role USER (Logic thực tế không đúng)
                        .requestMatchers(
                                new AntPathRequestMatcher("/department/**"),
                                new AntPathRequestMatcher("/employee/**"),
                                new AntPathRequestMatcher("/infomation/**")).hasRole("USER")
                        .anyRequest().authenticated());
        return http.build();
    }
}
