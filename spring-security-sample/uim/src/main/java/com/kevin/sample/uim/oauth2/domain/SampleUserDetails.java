package com.kevin.sample.uim.oauth2.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public interface SampleUserDetails extends UserDetails {

    public void setPassword(String password);


}
