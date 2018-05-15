package com.music.lbry;

import com.music.lbry.models.entities.*;
import com.music.lbry.models.enums.Role;
import com.music.lbry.repository.SavedListRepository;
import com.music.lbry.services.AlbumService;
import com.music.lbry.services.PerformerService;
import com.music.lbry.services.SongService;
import com.music.lbry.services.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Component
public class StartupEvent {

    private final AlbumService albumService;
    private final PerformerService performerService;
    private final SongService songService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SavedListRepository savedListRepository;

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
        performers.add(new Performer(11L, "Paramore"));
        performers.add(new Performer(11L, "Slipknot"));
        performerService.saveAll(performers).block();

        performerService.findAllByName("Scorpions").block().forEach(c -> {
            this.albumService.add(new Album(1L, "Still Loving You", c)).block();
        });

        performerService.findAllByName("Linkin Park").block().forEach(c -> {
            this.albumService.add(new Album(2L, "Minutes To Midnight", c)).block();
            this.albumService.add(new Album(4L, "Meteora", c)).block();
            Album oml = this.albumService.add(new Album(5L, "One More Light", c)).block();

            this.songService.add(new Song(1L, "Numb", "kXYiU_JCYtU", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(11L, "Battle Symphony", "D7ab595h0AU", oml, Collections.singletonList(c))).block();
            this.songService.add(new Song(12L, "One More Light", "Tm8LGxTLtQk", oml, Collections.singletonList(c))).block();
            this.songService.add(new Song(13L, "Talking to Myself", "lvs68OKOquM", oml, Collections.singletonList(c))).block();
            this.songService.add(new Song(14L, "Sharp Edges", "M5Ni_LskhFc", oml, Collections.singletonList(c))).block();
            this.songService.add(new Song(15L, "Castle of Glass", "ScNNfyq3d_w", oml, Collections.singletonList(c))).block();
        });

        performerService.findAllByName("Ed Sheeran").block().forEach(c -> {
            this.albumService.add(new Album(3L, "Divide", c)).block();

            this.songService.add(new Song(7L, "Eraser", "OjGrcJ4lZCc", albumService.findAllByName("Divide").block().get(0),
                    Collections.singletonList(c))).block();
            this.songService.add(new Song(8L, "Galway Girl", "87gWaABqGYs", albumService.findAllByName("Divide").block().get(0),
                    Collections.singletonList(c))).block();
        });

        performerService.findAllByName("Evanescense").block().forEach(c -> {
            this.songService.add(new Song(4L, "My Immortal", "5anLPw0Efmo", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(5L, "Bring Me To Life", "3YxaaGgTQYM", null, Collections.singletonList(c))).block();
        });

        performerService.findAllByName("Red Hot Chilli Peppers").block().forEach(c -> {
            this.songService.add(new Song(2L, "Dark Necessities", "Q0oIoR9mLwc", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(3L, "Scar Tissue", "mzJj5-lubeM", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(9L, "Otherside", "rn_YodiJO6k", null, Collections.singletonList(c))).block();
        });

        performerService.findAllByName("Paramore").block().forEach(c -> {
            this.songService.add(new Song(16L, "Decode", "RvnkAtWcKYg", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(17L, "That's What You Get", "1kz6hNDlEEg", null, Collections.singletonList(c))).block();
        });

        performerService.findAllByName("Slipknot").block().forEach(c -> {
            this.songService.add(new Song(18L, "The Devil In I", "XEEasR7hVhA", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(19L, "Psychosocial", "5abamRO41fE", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(20L, "Dead Memories", "9gsAz6S_zSw", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(21L, "Snuff", "LXEKuttVRIo", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(22L, "Duality", "6fVE8kSM43I", null, Collections.singletonList(c))).block();
            this.songService.add(new Song(23L, "Vermillion Pt. 2", "LvetJ9U_tVY", null, Collections.singletonList(c))).block();
        });

        val user = new LibraryUser(1L, "sergii.vasylchak", passwordEncoder.encode("password"),
                Role.ADMIN, "Serg", "Vasylchak", false);
        this.userService.add(user).block();
        this.userService.add(new LibraryUser(2L, "ira.bokalo", passwordEncoder.encode("password"),
                Role.ADMIN, "Ira", "Bokalo", false)).block();
        this.userService.add(new LibraryUser(3L, "mikle.alex", passwordEncoder.encode("password"),
                Role.ADMIN, "Mikle", "Alex", false)).block();
        this.userService.add(new LibraryUser(4L, "orest.animal", passwordEncoder.encode("password"),
                Role.USER, "Orest", "Animal", false)).block();
        this.userService.add(new LibraryUser(5L, "sviatoslav.ilkiv", passwordEncoder.encode("password"),
                Role.USER, "Sviatoslav", "Ilkiv", false)).block();
        this.userService.add(new LibraryUser(6L, "demian.shwetz", passwordEncoder.encode("password"),
                Role.USER, "Demian", "Shwetz", false)).block();


        this.savedListRepository.save(new SavedList(1L, "Top of LP hell yeah", user, this.songService.findAllByAuthorId(1L).block()));
    }
}
