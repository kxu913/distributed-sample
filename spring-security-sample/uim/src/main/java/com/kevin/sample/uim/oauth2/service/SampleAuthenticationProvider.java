package com.kevin.sample.uim.oauth2.service;

import com.kevin.sample.uim.domain.User;
import com.kevin.sample.uim.domain.UserDetail;
import com.kevin.sample.uim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SampleAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private RedisTemplate<String, UserDetail> userRedisTemplate;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
//        UserDetail user = userRedisTemplate.opsForValue().get(userName);
//        if (user != null) {
//            if(user.getUserName().equals(userName)&&user.getPassword().equals(password)){
//                return new UsernamePasswordAuthenticationToken(userName,passwordEncoder.encode(password),user.getAuthorities());
//            }
//
//        }else{
            User dbUser = userService.validate(userName,password);
            UserDetail detail = userService.getUser(dbUser.getUserId());
            if(dbUser.getUserId()!=0){
                userRedisTemplate.opsForValue().set(userName,detail,1, TimeUnit.DAYS);
                return new UsernamePasswordAuthenticationToken(userName,passwordEncoder.encode(password),detail.getAuthorities());
            }
//        }
        throw new BadCredentialsException("password is not correct, please check username and password.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
