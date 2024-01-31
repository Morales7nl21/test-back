package com.test.technique.repository;

import com.test.technique.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u ORDER BY u.fathersLastname, u.mothersLastname")
    List<User> findAllOrderedByLastnames();

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    Optional<User> findByIdOrderedByLastnames(@Param("userId") Long userId);

    boolean existsByEmail(String email);
}
