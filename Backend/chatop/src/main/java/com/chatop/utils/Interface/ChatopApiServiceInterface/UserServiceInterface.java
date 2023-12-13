package com.chatop.utils.Interface.ChatopApiServiceInterface;

import com.chatop.chatopApiModel.DbUser;
import java.util.Optional;

public interface UserServiceInterface {
  Boolean isEmailAlreadyUsed(String email);
  DbUser saveUser(DbUser user);
  Optional<DbUser> getUserByEmail(String email);
  Optional<DbUser> getUserById(Long id);
  Boolean isPasswordValid(String password, DbUser user);
}
