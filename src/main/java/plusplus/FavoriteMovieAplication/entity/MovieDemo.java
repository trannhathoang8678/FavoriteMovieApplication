package plusplus.FavoriteMovieAplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDemo {
    private int id;
    private String name;
    private int createdYear;
    private String urlPoster;
    private int score;
}
