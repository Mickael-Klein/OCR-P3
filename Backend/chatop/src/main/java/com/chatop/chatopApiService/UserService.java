package com.chatop.chatopApiService;

import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiRepository.UserRepository;
import com.chatop.utils.ReqResModelsAndServices.Request.RegisterRequestModel;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public Boolean isEmailAlreadyUsed(String email) {
    Optional<DbUser> optionalUser = userRepository.findByEmail(email);
    return optionalUser.isPresent();
  }

  public DbUser saveUser(RegisterRequestModel request) {
    DbUser user = new DbUser();

    LocalDateTime now = LocalDateTime.now();

    user.setEmail(request.getEmail());
    user.setName(request.getName());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setCreatedAt(now);
    user.setUpdatedAt(now);

    return userRepository.save(user);
  }

  public Optional<DbUser> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public Optional<DbUser> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public Boolean isPasswordValid(String password, DbUser user) {
    return passwordEncoder.matches(password, user.getPassword());
  }
}
