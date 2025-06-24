package com.sangto.user_management.repository;

import com.sangto.user_management.domain.entity.User;
import com.sangto.user_management.domain.enums.EUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = false")
    Optional<User> findByEmailAndDeleted(@Param("email") String email);

    Optional<User> findFirstByRole(EUserRole role);

    @Query("SELECT u FROM User u WHERE u.role != :role")
    List<User> findAllExcludingRole(@Param("role") EUserRole role);
}
