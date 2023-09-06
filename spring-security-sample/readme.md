## Spring Security Sample
The project display how to setup an authorization server, and how to use it.

it includes three modules:
1. **uim**: it's a customize authorization server, core module.
2. **uim-client**: it's a client that can use git auth and customize auth to do authorization.
3. **uim-resource-server**: it's a resource server that use JWT token which created by customize authorization server.

### UIM
- `SampleAuthenticationProvider` used to do authorization, verify username/password.
- `SampleRegisteredClientRepository` used to create a registered client, verify client-id/client-secret.
- `SampleUserDetailService` used to manage user detail.

### UIM-Client
- Use git authorization.
You need config git client-id and client-secret.
```agsl
github:
  client-id: 7303adf998aeaed89cf2
  client-secret: 4c53dbce42180499c2593a28ef93726fddfbc583
```
- Use Customize authorization.
Be sure that you add **openid** in scope as default, otherwise the authorization process will be broke due to incorrect user information.
```agsl
spring:
  ...
  security:
    oauth2:
      client:
        registration:
          localhost:
            client-id: kevin
            client-secret: kevin
            scope:
            - "openid"
            - "user:read"
            - "user:write"
            client-authentication-method: client_secret_basic
            authorization-grant-type: authorization_code
            redirect-uri: http://127.0.0.1:8002/login/oauth2/code/localhost
            provider: custom-issuer
            client-name: Localhost
        provider:
          custom-issuer:
            issuer-uri: http://uim.kevin.com
```

### UIM-Resource Server
Be sure that you use **jwk-set-uri** as authorization server.
```agsl
spring:
...
  security:
    oauth2:
      resourceserver:
        jwt:
          jwk-set-uri: http://uim.kevin.com/oauth2/jwks
```