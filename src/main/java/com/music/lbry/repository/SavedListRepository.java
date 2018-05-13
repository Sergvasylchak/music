package com.music.lbry.repository;

import com.music.lbry.models.entities.SavedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SavedListRepository extends JpaRepository<SavedList, Long> {

    @Query("SELECT l FROM SavedList l WHERE l.user.id = :id")
    List<SavedList> findAllByUserId(@Param("id") Long id);
}
