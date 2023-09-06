/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service;

import java.util.List;

import com.kevin.sample.uim.domain.SampleRegisteredClient;

public interface RegisteredClientService {
    public List<SampleRegisteredClient> getRegisteredClients();

    public long saveRegisteredClient(SampleRegisteredClient domain);

}
