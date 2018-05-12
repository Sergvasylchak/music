package com.music.lbry.repository;

import com.music.lbry.models.entities.LibraryUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<LibraryUser, Long> {

    @Query("SELECT u FROM LibraryUser u WHERE u.firstName LIKE %:search% OR u.lastName LIKE %:search%" +
            " OR u.username LIKE %:search%")
    Page<LibraryUser> findAllBySearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT u FROM LibraryUser u WHERE u.username = :username")
    Optional<LibraryUser> findByUsername(@Param("username") String username);
}
