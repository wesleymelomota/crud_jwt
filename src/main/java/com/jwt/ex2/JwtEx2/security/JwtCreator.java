package com.jwt.ex2.JwtEx2.security;

import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtCreator {
	public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final String ROULES_AUTHORITIES = "authorities";
	
	public static String create(String prefix, String key, JWTobject tokenJwt) {
		String token = Jwts.builder()
				.setSubject(tokenJwt.getSubject())
				.setIssuedAt(tokenJwt.getIssuedAT())
				.setExpiration(tokenJwt.getExpiration())
				.claim(ROULES_AUTHORITIES, checkRoles(tokenJwt.getRoles()))
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
			return prefix + " " + token;
	}
	public static JWTobject create(String token, String prefix, String key) 
			throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
		token = token.replace(prefix, "");
		Claims claim = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();//confere se o token é valido
		JWTobject objJwt = new JWTobject();
		objJwt.setSubject(claim.getSubject());
		objJwt.setExpiration(claim.getExpiration());
		objJwt.setIssuedAT(claim.getIssuedAt());
		objJwt.setRoles((List) claim.get(ROULES_AUTHORITIES));
		return objJwt;
	}
	private static List<String> checkRoles(List<String> roles) {
		return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_", ""))).collect(Collectors.toList());
	}
}
