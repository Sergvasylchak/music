package com.music.lbry.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lbry_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @ManyToOne
    private LibraryUser user;

    @ManyToMany
    private List<Song> songs;
}
