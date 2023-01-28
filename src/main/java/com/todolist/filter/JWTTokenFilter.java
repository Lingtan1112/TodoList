package com.todolist.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.todolist.business.JWTGenerator;
import com.todolist.business.UserService;

public class JWTTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	JWTGenerator jwtGenerator;
	
	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			bearerToken = bearerToken.substring(7, bearerToken.length());
		}else {
			bearerToken = null;
		}
		
		if(StringUtils.hasText(bearerToken) && jwtGenerator.validateToken(bearerToken)) {
			String userName = jwtGenerator.getUserNameFromJWT(bearerToken);
			UserDetails userDetails = userService.loadUserByUsername(userName);
			UsernamePasswordAuthenticationToken uname = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
			uname.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(uname);
		}
		filterChain.doFilter(request, response);
	}

}
