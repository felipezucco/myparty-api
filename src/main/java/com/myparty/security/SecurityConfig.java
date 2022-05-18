package com.myparty.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.myparty.enums.RoleEnum;
import com.myparty.service.AuthService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JWTAuthFilter(jwtService, authService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/event/**").permitAll() //hasAnyRole(RoleEnum.ADMIN.name())
                .antMatchers("/api/local/**").hasAnyRole(RoleEnum.MANAGER.name(), RoleEnum.USER.name())
                .antMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MANAGER.name())
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        // Logout Handler
        http
                .logout(logout
                        -> logout
                        .clearAuthentication(true)
                        .logoutSuccessHandler(logoutSuccessHandler));
    }

}
