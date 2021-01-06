package plusplus.FavoriteMovieAplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleMovieRelationship {
    private int movieID,peopleID;
    private  String role;
    //time by year
    private int time;
}
