package com.kevin.sample.uim.oauth2.service;

import com.kevin.sample.uim.domain.User;
import com.kevin.sample.uim.generator.IdGenerator;
import com.kevin.sample.uim.oauth2.domain.MutableUser;
import com.kevin.sample.uim.oauth2.domain.SampleUser;
import com.kevin.sample.uim.oauth2.domain.SampleUserDetails;
import com.kevin.sample.uim.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class SampleUserDetailService implements UserDetailsManager, UserDetailsPasswordService {
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        User dbUser = userService.getUserByName(user.getUsername());
        Assert.state(dbUser != null && dbUser.getUserId()!=0, "Current user doesn't exist in database.");
        userService.updatePassword(dbUser.getUserId(),newPassword);
        MutableUser mutableUser = new MutableUser(user);
        mutableUser.setPassword(newPassword);
        return mutableUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);
        Assert.state(user != null && user.getUserId()!=0, "Current user doesn't exist in database.");

        return new SampleUser(user);
    }

    @Override
    public void createUser(UserDetails user) {
        User domainUser = new User();
        domainUser.setUserName(user.getUsername());
        domainUser.setEnabled(user.isEnabled());
        domainUser.setUserId(new IdGenerator(1,1,1).nextId());
        domainUser.setPassword(user.getPassword());
        userService.saveUser(domainUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = this.securityContextHolderStrategy.getContext().getAuthentication();
        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException(
                    "Can't change password as no Authentication object found in context " + "for current user.");
        }
        String username = currentUser.getName();
        log.info("Changing password for user '{}'", username);
        // If an authentication manager has been set, re-authenticate the user with the
        // supplied password.
        if (this.authenticationManager != null) {
            log.info("Reauthenticating user '{}' for password change request.", username);
            this.authenticationManager
                    .authenticate(UsernamePasswordAuthenticationToken.unauthenticated(username, oldPassword));
        }
        else {
            log.info("No authentication manager set. Password won't be re-checked.");
        }
        User user = userService.getUserByName(username);
        Assert.state(user != null && user.getUserId()!=0, "Current user doesn't exist in database.");
        userService.updatePassword(user.getUserId(),newPassword);
    }

    @Override
    public boolean userExists(String username) {
        User user = userService.getUserByName(username);
        return user!=null && user.getUserId()!=0;
    }

    public void setSecurityContextHolderStrategy(SecurityContextHolderStrategy securityContextHolderStrategy) {
        Assert.notNull(securityContextHolderStrategy, "securityContextHolderStrategy cannot be null");
        this.securityContextHolderStrategy = securityContextHolderStrategy;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
