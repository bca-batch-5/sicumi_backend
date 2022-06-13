package com.sicumi.project.sicumi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sicumi.project.sicumi.services.UserDetailsServicesImpl;



@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserDetailsServicesImpl userDetailServiceImpl;

  @Autowired
  private AuthentEntryPoint authEntryPoint;

  @Bean
  public CorsConfig jwtFilter(){
    return new CorsConfig();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception{
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailServiceImpl);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception{
    http = http.cors().and().csrf().disable();

    http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

    http = http.exceptionHandling().authenticationEntryPoint(authEntryPoint).and();

    http.authorizeHttpRequests().antMatchers("/").permitAll()
    .antMatchers(HttpMethod.GET, "/user/**").permitAll()
    .antMatchers(HttpMethod.POST, "/user/**" ).permitAll()
    .anyRequest().authenticated();

    http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
