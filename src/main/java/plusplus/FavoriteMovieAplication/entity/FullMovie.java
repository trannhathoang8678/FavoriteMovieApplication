package plusplus.FavoriteMovieAplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


public class FullMovie extends Movie {
    public FullMovie(int id, String name, int createdYear, String urlPoster, String overview, int score, String length) {
        super(id, name, createdYear, urlPoster, overview, score, length);
    }

    private List<String> types;

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
