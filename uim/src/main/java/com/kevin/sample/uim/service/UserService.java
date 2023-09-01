/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service;
import java.util.List;
import com.kevin.sample.domain.User;
import com.kevin.sample.domain.UserDetail;

public interface UserService {
    public List<User> getUsers();

    public long saveUser(User domain);

    public UserDetail getUser(long userId);

}
