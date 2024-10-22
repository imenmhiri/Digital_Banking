package com.fss.Digital.Banking.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fss.Digital.Banking.entites.Client;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private ClientService ClientServiceImpl;
     
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client user = ClientServiceImpl.chargerUtilisateurParUsername(username);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRole().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getNomDuRole()));
		});
		return new User(user.getUsername(),user.getPassword(),authorities); 

	}

}
