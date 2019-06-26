package com.example.storemanagement.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Auth {

  private Boolean authenticated;

  private byte[] token;

  private Collection<? extends GrantedAuthority>  authorities;

  private String username;



  public Auth(Boolean authenticated, byte[] token, Collection<? extends GrantedAuthority> authorities, String username) {
    this.authenticated = authenticated;
    this.token = token;
    this.authorities = authorities;
    this.username = username;
  }


  public Boolean getAuthenticated() {
    return authenticated;
  }

  public void setAuthenticated(Boolean authenticated) {
    this.authenticated = authenticated;
  }

  public byte[] getToken() {
    return token;
  }

  public void setToken(byte[] token) {
    this.token = token;
  }

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
