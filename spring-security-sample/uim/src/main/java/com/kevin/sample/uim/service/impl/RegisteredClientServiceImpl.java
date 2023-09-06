/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service.impl;


import com.kevin.sample.uim.domain.SampleRegisteredClient;
import com.kevin.sample.uim.generator.IdGenerator;
import com.kevin.sample.uim.mapper.RegisteredClientMapper;
import com.kevin.sample.uim.service.RegisteredClientService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RegisteredClientServiceImpl implements RegisteredClientService {
    private final RegisteredClientMapper registeredClientMapper;

    private final PasswordEncoder passwordEncoder;

    public RegisteredClientServiceImpl(RegisteredClientMapper registeredClientMapper,PasswordEncoder passwordEncoder) {
        this.registeredClientMapper = registeredClientMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<SampleRegisteredClient> getRegisteredClients() {
        return registeredClientMapper.getRegisteredClients();
    }

    @Override
    public long saveRegisteredClient(SampleRegisteredClient domain) {
        long id = new IdGenerator(1, 1, 1).nextId();
        domain.setId(id);
        domain.setClientSecret(passwordEncoder.encode(domain.getClientSecret()));
        registeredClientMapper.insert(domain);
        return id;
    }
}
