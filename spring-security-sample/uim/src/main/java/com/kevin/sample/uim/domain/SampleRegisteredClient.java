/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.domain;

import lombok.Data;

@Data
public class SampleRegisteredClient {

    private long id;

    private String clientId;

    private String clientSecret;

    private String[] grantTypes;

    private String[] authMethods;

    private String[] scope;

    private boolean enabled;


}
