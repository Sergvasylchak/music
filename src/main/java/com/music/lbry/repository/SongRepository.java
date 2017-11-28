package com.music.lbry.repository;

import com.music.lbry.models.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("SELECT s FROM Song s WHERE s.album.id = :id")
    List<Song> findAllByAlbumId(@Param("id") Long id);

    @Query("SELECT s FROM Song s WHERE s.name LIKE %:name%")
    List<Song> findAllByName(@Param("name") String name);

    @Query("SELECT s FROM Song s" +
            " LEFT JOIN s.performers p WHERE p.id = :id")
    List<Song> findAllByPerformerId(@Param("id") Long id);

    Optional<Song> findById(Long id);
}
