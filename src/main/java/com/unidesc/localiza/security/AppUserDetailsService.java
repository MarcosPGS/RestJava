package com.unidesc.localiza.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unidesc.localiza.entity.Permissao;
import com.unidesc.localiza.entity.Usuario;
import com.unidesc.localiza.negocio.service.UsuarioService;
import com.unidesc.localiza.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;//TODO: TROCAR PARA USUARIOSERVICE
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuarioSeguranca = usuarioService.buscarLogin(login);
		
		
		
		return new User(usuarioSeguranca.getLogin(),usuarioSeguranca.getSenha()
				, converterPerfis(usuarioSeguranca.getPermissaos()));
	}

	private Collection<? extends GrantedAuthority> converterPerfis(List<Permissao> permissaos) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		permissaos.forEach(p->{
			authorities.add(new SimpleGrantedAuthority(p.getPermissao()));
			
		});
		return authorities;
	}

}
