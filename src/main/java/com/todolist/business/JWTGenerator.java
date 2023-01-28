package com.todolist.business;

import java.util.Date;

import javax.security.sasl.AuthenticationException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {

	public static final String SECRET_KEY = "mysecretkey";
	public Date currDate = new Date();
	public Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);
	
	
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		return Jwts.builder()
		.setIssuedAt(currDate)
		.setExpiration(expirationDate)
		.setSubject(username).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public String getUserNameFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) throws AuthenticationException {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new AuthenticationException("Token expired or incorrect");
		} 
	}
	
}
