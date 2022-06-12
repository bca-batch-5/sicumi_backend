package com.sicumi.project.sicumi.config;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.sicumi.project.sicumi.services.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
  
  private String JWT_SECRET = "secretKey";

  private Integer JWT_EXPIRATION_MS = 180000;

  public String generateJwToken(Authentication authentication){
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    String base64Secret = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());

    Map<String, Object> claims = new HashMap<>();
    claims.put("email", userPrincipal.getUsername());

    return Jwts.builder()
    .setSubject("User Details")
    .setClaims(claims)
    .setIssuedAt(new Date())
    .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_MS))
    .signWith(SignatureAlgorithm.HS512, base64Secret)
    .compact();
  }

  public String getUsernameFromToken(String token){
    String base64Secret = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());
    
    return Jwts.parser()
    .setSigningKey(base64Secret)
    .parseClaimsJws(token)
    .getBody()
    .get("email")
    .toString();
  }

  public boolean validateToken(String token){
    try {
      String base64Secret = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());

      Jwts.parser()
      .setSigningKey(base64Secret)
      .parseClaimsJws(token);

      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
