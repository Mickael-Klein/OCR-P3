package com.chatop.configuration;

import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiRepository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email)
    throws UsernameNotFoundException {
    Optional<DbUser> optionalUser = userRepository.findByEmail(email);

    DbUser user = optionalUser.orElseThrow(() ->
      new UsernameNotFoundException("User not found with email: " + email)
    );

    return new User(user.getEmail(), user.getPassword(), null);
  }
}
