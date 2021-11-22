package com.databasecontroller.DatabaseController.APIAuthentication;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import com.databasecontroller.DatabaseController.APIAuthentication.APIKeyAuthFilter;

@Configuration
@EnableWebSecurity
public class APISecurityConfig extends WebSecurityConfigurerAdapter {
	
	/* Setting up Spring Security Layer to make sure all requests are authenticated. */

	 @Autowired private APIKeyAuthenticationProvider apiKeyAuthenticationProvider;

	  @Override
	  protected void configure(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity.authorizeRequests().anyRequest().authenticated().and().addFilterBefore(
	            new APIKeyAuthFilter(authenticationManager()),
	            AnonymousAuthenticationFilter.class).csrf().disable();;
	  }

	  @Bean
	  public AuthenticationManager authenticationManager() {
	    return new ProviderManager(Collections.singletonList(apiKeyAuthenticationProvider));
	  }
}
