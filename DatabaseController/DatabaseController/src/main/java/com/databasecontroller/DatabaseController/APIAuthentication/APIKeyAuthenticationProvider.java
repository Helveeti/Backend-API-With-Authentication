package com.databasecontroller.DatabaseController.APIAuthentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class APIKeyAuthenticationProvider implements AuthenticationProvider {
	
	/* Provides api key authentication. */

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String apiKey = (String) authentication.getPrincipal();

    if (ObjectUtils.isEmpty(apiKey)) {
      throw new InsufficientAuthenticationException("No API key in request");
    } else {
      if ("ValidApiKey".equals(apiKey)) {
        return new APIKeyAuthenticationToken(apiKey, true);
      }
      throw new BadCredentialsException("API Key is invalid");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return APIKeyAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
