package com.music.lbry.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "lbry_songs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Album album;

    @ManyToMany
    private List<Performer> performers;
}
