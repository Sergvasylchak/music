package com.music.lbry.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lbry_albums", uniqueConstraints = {
        @UniqueConstraint(name = "album_author", columnNames = {"name", "author_id"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @NotNull
    private Performer author;
}
