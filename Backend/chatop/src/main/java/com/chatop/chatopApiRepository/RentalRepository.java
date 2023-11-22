package com.chatop.chatopApiRepository;

import com.chatop.chatopApiModel.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {}
