package com.sicumi.project.sicumi.config;

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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sicumi.project.sicumi.services.UserDetailsServicesImpl;

@Component
public class CorsConfig extends OncePerRequestFilter {
  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserDetailsServicesImpl userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    
  String headerAuth = request.getHeader("Authorization");

  if(headerAuth != null && headerAuth.startsWith("Bearer")){
    String jwtToken = headerAuth.substring(7, headerAuth.length());
    if(jwtToken != null && jwtUtil.validateToken(jwtToken)){
      String username = jwtUtil.getUsernameFromToken(jwtToken);

      UserDetails userDetails = userService.loadUserByUsername(username);

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
  }

  response.setHeader("Access-Control-Allow-Origin", "*");
  response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
  response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
  response.setHeader("Access-Control-Max-Age", "3600");
  if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
    response.setStatus(HttpServletResponse.SC_OK);
  } else {
    filterChain.doFilter(request, response);
  }
  }
  
}
