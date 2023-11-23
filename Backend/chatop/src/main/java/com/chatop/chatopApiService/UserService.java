package com.chatop.chatopApiService;

import com.chatop.chatopApiModel.User;
import com.chatop.chatopApiRepository.UserRepository;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User addUser(User user) {
    return userRepository.save(user);
  }
}
