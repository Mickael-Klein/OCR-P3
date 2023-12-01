package com.chatop.chatopApiRepository;

import com.chatop.chatopApiModel.DbUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DbUser, Long> {
  public Optional<DbUser> findByEmail(String email);
}
