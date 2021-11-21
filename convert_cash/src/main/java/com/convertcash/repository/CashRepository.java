package com.convertcash.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.convertcash.domain.Cash;
import com.convertcash.domain.User;

@Repository
public interface CashRepository extends MongoRepository<Cash, Integer> {

}
