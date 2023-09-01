/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.domain;

import lombok.Data;

import java.util.Date;
@Data
public class User {

    private long userId;

    private String userName;

    private String password;

    private boolean enabled;


}
