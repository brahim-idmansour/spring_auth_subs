package com.santeDigital.gestionPatients.repository;

import com.santeDigital.gestionPatients.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    UserModel findByUsername(String username);
}
