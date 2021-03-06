package plusplus.FavoriteMovieAplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private int id;
    private String name;
    private int createdYear;
    private String urlPoster,overview;
    private int score;
    private String length;
}
