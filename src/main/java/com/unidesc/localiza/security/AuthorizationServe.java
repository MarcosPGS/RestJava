package com.unidesc.localiza.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;





@Configuration
@EnableAuthorizationServer
public class AuthorizationServe extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory().withClient("acesso_via_computador").secret("{noop}123456").scopes("crud")
//				.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(1800)
//				.refreshTokenValiditySeconds(3600 * 12);
//
//	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter())
				.authenticationManager(authenticationManager);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("secret");
		return accessTokenConverter;
	}

	// ADICIONANDO NOVO METODO

	public ClientDetailsService clientDetailsService() {
		return new ClientDetailsService() {

			@Override
			public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
				 BaseClientDetails details = new BaseClientDetails();
				 details.setClientSecret("{noop}@ngul@r0");
				 details.setAuthorizedGrantTypes(Arrays.asList("authorization_code","refresh_token","password"));
				details.setScope(Arrays.asList("read","Write"));
				details.setRegisteredRedirectUri(Collections.singleton("http://anywhere.com"));
				details.setResourceIds(Arrays.asList("oauth2-resource"));
				
				Set<GrantedAuthority> authorities = new HashSet<>();
				authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
				details.setAuthorities(authorities);
				 return details;
			}
		};
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService());
	}


	
}
