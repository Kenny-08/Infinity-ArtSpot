package com.ken.infinity.services;

import com.ken.infinity.models.User;
import com.ken.infinity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        List<GrantedAuthority> grantList = new ArrayList<>();
        String role = user.getRole();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantList.add(authority);

        System.out.println(grantList);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantList
        );

    }
}
