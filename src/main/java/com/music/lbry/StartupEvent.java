package com.music.lbry;

import com.music.lbry.LbryApplication;
import com.music.lbry.models.entities.Album;
import com.music.lbry.models.entities.Performer;
import com.music.lbry.models.entities.Song;
import com.music.lbry.services.AlbumService;
import com.music.lbry.services.PerformerService;
import com.music.lbry.services.SongService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Component
public class StartupEvent {

    private final AlbumService albumService;
    private final PerformerService performerService;
    private final SongService songService;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        List<Performer> performers = new ArrayList<>();
        performers.add(new Performer(1L, "Linkin Park"));
        performers.add(new Performer(2L, "Evanescense"));
        performers.add(new Performer(3L, "The Rolling Stones"));
        performers.add(new Performer(4L, "Red Hot Chilli Peppers"));
        performers.add(new Performer(5L, "Hollywood Undead"));
        performers.add(new Performer(6L, "Ed Sheeran"));
        performers.add(new Performer(7L, "The Beatles"));
        performers.add(new Performer(8L, "Three Days Grace"));
        performers.add(new Performer(9L, "Eminem"));
        performers.add(new Performer(10L, "Scorpions"));
        performerService.saveAll(performers).block();

        performerService.findAllByName("Scorpions").block().forEach(c -> {
            this.albumService.add(new Album(1L, "Still Loving You", c));
        });

        performerService.findAllByName("Linkin Park").block().forEach(c -> {
            this.albumService.add(new Album(2L, "Minutes To Midnight", c));
            this.albumService.add(new Album(4L, "Meteora", c));

            this.songService.add(new Song(1L, "Numb", null, Collections.singletonList(c)));
            this.songService.add(new Song(6L, "What I`ve Done", null, Collections.singletonList(c)));
        });

        performerService.findAllByName("Ed Sheeran").block().forEach(c -> {
            this.albumService.add(new Album(3L, "Divide", c));

            this.songService.add(new Song(7L, "Eraser", albumService.findAllByName("Divide").get(0), Collections.singletonList(c)));
            this.songService.add(new Song(8L, "Galway Girl", albumService.findAllByName("Divide").get(0), Collections.singletonList(c)));
        });

        performerService.findAllByName("Evanescense").block().forEach(c -> {
            this.songService.add(new Song(4L, "My Immortal", null, Collections.singletonList(c)));
            this.songService.add(new Song(5L, "Bring Me To Life", null, Collections.singletonList(c)));
        });

        performerService.findAllByName("Red Hot Chilli Peppers").block().forEach(c -> {
            this.songService.add(new Song(2L, "Dark Necessities", null, Collections.singletonList(c)));
            this.songService.add(new Song(3L, "Bring Me To Life", null, Collections.singletonList(c)));
        });
    }
}
