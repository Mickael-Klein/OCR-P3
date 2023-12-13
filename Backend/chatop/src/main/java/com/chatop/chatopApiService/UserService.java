package com.chatop.chatopApiService;

import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiRepository.UserRepository;
import com.chatop.utils.Interface.ChatopApiServiceInterface.UserServiceInterface;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService implements UserServiceInterface {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public Boolean isEmailAlreadyUsed(String email) {
    Optional<DbUser> optionalUser = userRepository.findByEmail(email);
    return optionalUser.isPresent();
  }

  @Override
  public DbUser saveUser(DbUser user) {
    return userRepository.save(user);
  }

  @Override
  public Optional<DbUser> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Optional<DbUser> getUserById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Boolean isPasswordValid(String password, DbUser user) {
    return passwordEncoder.matches(password, user.getPassword());
  }
}
