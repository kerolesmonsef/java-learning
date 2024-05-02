package com.foo.learning.repository;

import com.foo.learning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.name = :name")
    Optional<User> findByName(@Param(value = "name") String name);

    @Query("from User u where u.email = :email")
    Optional<User> findByEmail(String email);

}
