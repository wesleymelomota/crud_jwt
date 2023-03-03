package com.jwt.ex2.JwtEx2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.ex2.JwtEx2.security.JWTfilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	protected SecurityFilterChain WebFilterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.cors().and().csrf().disable()
				.addFilterAfter(new JWTfilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests()
				.antMatchers(HttpMethod.POST,"/user").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.GET, "/user/obter-todos").hasRole("MANAGER")
				.antMatchers("/users-pag").hasAnyRole("USER", "MANAGER")
				.antMatchers("/managers").hasRole("MANAGER")
				.antMatchers("/produto/obter-todos").hasAnyRole("USER", "MANAGER")
				.antMatchers("/produto/**").hasRole("MANAGER")
				.anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
	/*@Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }*/
}
