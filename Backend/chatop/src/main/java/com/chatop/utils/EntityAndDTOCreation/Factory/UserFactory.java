package com.chatop.utils.EntityAndDTOCreation.Factory;

import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.utils.Interface.EntityAndDTOCreationInterface.FactoryInterface.UserFactoryInterface;
import com.chatop.utils.ReqResModelsAndServices.Request.RegisterRequestModel;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory implements UserFactoryInterface {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public DbUser getPostUserEntity(RegisterRequestModel registerRequestUser) {
    LocalDateTime now = LocalDateTime.now();

    DbUser user = new DbUser();
    user.setName(registerRequestUser.getName());
    user.setEmail(registerRequestUser.getEmail());
    user.setPassword(
      bCryptPasswordEncoder.encode(registerRequestUser.getPassword())
    );
    user.setCreatedAt(now);
    user.setUpdatedAt(now);

    return user;
  }

  @Override
  public UserDTO getUserDTO(DbUser user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setName(user.getName());
    userDTO.setEmail(user.getEmail());
    userDTO.setCreated_at(user.getCreatedAt().toLocalDate());
    userDTO.setUpdated_at(user.getUpdatedAt().toLocalDate());

    return userDTO;
  }
}
