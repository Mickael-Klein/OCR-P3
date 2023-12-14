package com.chatop.chatopApiRepository;

import com.chatop.chatopApiModel.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing rentals in the database.
 *
 * This interface extends JpaRepository, providing CRUD operations for Rental entities.
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {}
