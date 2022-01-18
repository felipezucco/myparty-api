package com.tendelfc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tendelfc.enums.RoleEnum;
import com.tendelfc.service.AccountService;
import com.tendelfc.service.AuthService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService)
			.passwordEncoder(passwordEncoder);
	}

	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JWTAuthFilter(jwtService, authService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
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
			
	}

}
