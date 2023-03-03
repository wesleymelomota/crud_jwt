package com.jwt.ex2.JwtEx2.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.ex2.JwtEx2.config.SecurityConfig;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JWTfilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader(JwtCreator.HEADER_AUTHORIZATION);
		try {
			
			if(token != null && !token.isEmpty()) {
				
				JWTobject jwtObj = JwtCreator.create(token, SecurityConfig.PREFIX, SecurityConfig.KEY);
				
				List<SimpleGrantedAuthority> authorities = authorities(jwtObj.getRoles());
				
				UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
						jwtObj.getSubject(), null, authorities);
				SecurityContextHolder.getContext().setAuthentication(userToken);
			}else {
				SecurityContextHolder.clearContext();
			}
			filterChain.doFilter(request, response);
		}catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.FORBIDDEN.value());
			return;
		}
	}
	private List<SimpleGrantedAuthority> authorities(List<String> roles){
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
