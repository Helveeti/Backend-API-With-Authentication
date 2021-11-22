package com.databasecontroller.DatabaseController.APIAuthentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.AuthorityUtils;

@SuppressWarnings("serial")
@Transient
public class APIKeyAuthenticationToken extends AbstractAuthenticationToken {
	
	/* This class determines whether user is authenticated. */

  private String apiKey;
  
  public APIKeyAuthenticationToken(String apiKey, boolean authenticated) {
    super(AuthorityUtils.NO_AUTHORITIES);
    this.apiKey = apiKey;
    setAuthenticated(authenticated);
  }

  public APIKeyAuthenticationToken(String apiKey) {
    super(AuthorityUtils.NO_AUTHORITIES);
    this.apiKey = apiKey;
    setAuthenticated(false);
  }

  public APIKeyAuthenticationToken() {
    super(AuthorityUtils.NO_AUTHORITIES);
    setAuthenticated(false);
  }

  @Override
  public Object getCredentials() {
    return null;
  }
  
  @Override
  public Object getPrincipal() {
    return apiKey;
  }
}
