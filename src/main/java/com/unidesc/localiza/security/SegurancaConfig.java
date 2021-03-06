//package com.unidesc.localiza.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//
//@EnableWebSecurity
//@EnableAuthorizationServer
//@EnableResourceServer
//public class SegurancaConfig extends WebSecurityConfigurerAdapter {
//
//	
//	@Autowired
//	UserDetailsService userDetailsService;
//	
//	
//	
//	@Bean
//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		
//		return super.authenticationManager();
//	} 
//	
//	@Autowired// aqui esta dizendo qual criptografia utilizei
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Override//aqui estou passando o usuario e tipo de criptografia;
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//	}	
//}
