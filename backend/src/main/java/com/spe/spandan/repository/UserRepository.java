package com.spe.spandan.repository;

import com.spe.spandan.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query(value = "SELECT * from user u where u.email = :email ", nativeQuery = true)
    User getUser(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE user u SET u.user_type = \"SPOC\" where u.email = :email ", nativeQuery = true)
    void updateSPOC(@Param("email") String email);
}
