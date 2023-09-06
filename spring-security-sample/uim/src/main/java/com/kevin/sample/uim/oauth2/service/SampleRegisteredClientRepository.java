package com.kevin.sample.uim.oauth2.service;

import com.kevin.sample.uim.domain.SampleRegisteredClient;
import com.kevin.sample.uim.service.RegisteredClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
public class SampleRegisteredClientRepository implements RegisteredClientRepository {

    @Autowired
    private RegisteredClientService registeredClientService;

    @Autowired
    private RedisTemplate<Long, SampleRegisteredClient> registeredClientRedisTemplate;

    @Override
    public void save(RegisteredClient registeredClient) {
        SampleRegisteredClient client = new SampleRegisteredClient();
        client.setClientSecret(registeredClient.getClientSecret());
        client.setClientId(registeredClient.getClientId());
        client.setEnabled(true);
        client.setScope(registeredClient.getScopes().toArray(new String[]{}));
        client.setGrantTypes(grantTypes(registeredClient.getAuthorizationGrantTypes()));
        client.setAuthMethods(authMethods(registeredClient.getClientAuthenticationMethods()));
        registeredClientService.saveRegisteredClient(client);
        registeredClientRedisTemplate.opsForValue().set(client.getId(), client, 1, TimeUnit.DAYS);

    }

    private String[] grantTypes(Set<AuthorizationGrantType> grantTypes) {
        List<String> x = grantTypes.stream().map(AuthorizationGrantType::getValue).collect(Collectors.toList());
        return x.toArray(new String[]{});

    }

    private String[] authMethods(Set<ClientAuthenticationMethod> methods) {
        List<String> x = methods.stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.toList());
        return x.toArray(new String[]{});

    }

    @Override
    public RegisteredClient findById(String id) {
        SampleRegisteredClient client = registeredClientRedisTemplate.opsForValue().get(Long.parseLong(id));
        if (client != null) {
            return fromSampleRegisteredClient(client);
        }
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        List<SampleRegisteredClient> clients = registeredClientService.getRegisteredClients();

        for (SampleRegisteredClient client : clients) {
            if (client.getClientId().equals(clientId)) {
                return fromSampleRegisteredClient(client);
            }
        }

        return null;
    }

    private RegisteredClient fromSampleRegisteredClient(SampleRegisteredClient client) {
        RegisteredClient.Builder builder = RegisteredClient.withId("" + client.getId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret());

        setAuthenticationMethods(builder, client.getAuthMethods());
        setGrantTypes(builder, client.getGrantTypes());
        setScope(builder, client.getScope());



        builder.redirectUri("http://127.0.0.1:8002/login/oauth2/code/localhost")
                .tokenSettings(TokenSettings.builder().reuseRefreshTokens(true).setting("client-id",client.getClientId()).build())
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build());
        return builder.build();
    }

    private void setAuthenticationMethods(RegisteredClient.Builder builder, String[] methods) {
        for (String method : methods) {
            builder.clientAuthenticationMethod(new ClientAuthenticationMethod(method));
        }
    }

    private void setGrantTypes(RegisteredClient.Builder builder, String[] grantTypes) {
        for (String grantType : grantTypes) {
            builder.authorizationGrantType(new AuthorizationGrantType(grantType));
        }
    }

    private void setScope(RegisteredClient.Builder builder, String[] scopes) {
        for (String scope : scopes) {
            builder.scope(scope);
        }
    }
}
