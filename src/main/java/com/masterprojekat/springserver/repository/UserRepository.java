package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.model.UserAccountStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByResetToken(String resetToken);
    List<User> findByAccountStatus(UserAccountStatus status);
}
