package com.databasecontroller.DatabaseController.APIAuthentication;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class APIKeyAuthFilter extends AbstractAuthenticationProcessingFilter {
	
	/* Filter which retrieves given key and token. */
	
	public APIKeyAuthFilter(AuthenticationManager authenticationManager) {
	    super("/**");
	    this.setAuthenticationManager(authenticationManager);
	  }

	  @Override
	  public org.springframework.security.core.Authentication attemptAuthentication(
	      HttpServletRequest request, HttpServletResponse response) {

	    Optional<String> apiKeyOptional = Optional.ofNullable(request.getHeader("x-api-key"));

	    APIKeyAuthenticationToken token =
	        apiKeyOptional.map(APIKeyAuthenticationToken::new).orElse(new APIKeyAuthenticationToken());

	    return getAuthenticationManager().authenticate(token);
	  }

	  protected void successfulAuthentication(
	      HttpServletRequest request,
	      HttpServletResponse response,
	      FilterChain chain,
	      org.springframework.security.core.Authentication authResult)
	      throws IOException, ServletException {

	      SecurityContextHolder.getContext().setAuthentication(authResult);
	    chain.doFilter(request, response);
	  }

}
