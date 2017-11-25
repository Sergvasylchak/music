import com.music.lbry.LbryApplication;
import com.music.lbry.models.entities.Performer;
import com.music.lbry.services.AlbumService;
import com.music.lbry.services.PerformerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class StartupEvent {

    private final AlbumService albumService;
    private final PerformerService performerService;

    public List<Performer> initData() {
        List<Performer> performers = new ArrayList<>();
        performers.add(new Performer(1L,"Linkin Park"));
        performers.add(new Performer(2L,"Linkin Park"));
        performers.add(new Performer(3L,"Linkin Park"));
        performers.add(new Performer(4L,"Linkin Park"));
        performers.add(new Performer(5L,"Linkin Park"));
        performers.add(new Performer(6L,"Linkin Park"));
        performers.add(new Performer(7L,"Linkin Park"));
        performers.add(new Performer(8L,"Linkin Park"));
        performers.add(new Performer(9L,"Linkin Park"));
        return performerService.saveAll(performers);
    }
}
