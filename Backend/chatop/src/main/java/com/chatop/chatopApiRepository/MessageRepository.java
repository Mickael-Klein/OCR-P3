package com.chatop.chatopApiRepository;

import com.chatop.chatopApiModel.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing messages in the database.
 *
 * This interface extends JpaRepository, providing CRUD operations for Message entities.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {}
