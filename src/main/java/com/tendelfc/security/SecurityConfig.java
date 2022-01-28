package com.tendelfc.security;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tendelfc.enums.RoleEnum;
import com.tendelfc.service.AuthService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private JWTService jwtService;

	@Autowired
	private LogoutHandlerConfig logoutHandlerConfig;
	
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JWTAuthFilter(jwtService, authService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors()
			.and()
			.authorizeRequests()
				.antMatchers("/event/**").hasAnyRole(RoleEnum.ADMIN.name())
				.antMatchers("/local/**").hasAnyRole(RoleEnum.MANAGER.name(), RoleEnum.USER.name())
				.antMatchers(HttpMethod.POST,"/account/**").permitAll()
				.antMatchers(HttpMethod.DELETE,"/account/**").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MANAGER.name())
				.antMatchers(HttpMethod.POST, "/auth/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// Logout handler
		http
			.logout(logout -> 
				logout  
					.clearAuthentication(true)
		            //.logoutUrl("/auth/logout")                                            
		            .logoutSuccessUrl("/auth/logout")                                      
		            //.logoutSuccessHandler(logoutSuccessHandler)                         
		            //.invalidateHttpSession(true)                                        
		            .addLogoutHandler((request, response, auth) -> {
						try {
							request.logout();
						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					})                                    
		            .deleteCookies("eventweb.token"));

	}

	
	
}
