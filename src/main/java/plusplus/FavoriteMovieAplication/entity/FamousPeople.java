package plusplus.FavoriteMovieAplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FamousPeople {
    private int id;
    private String name,urlAvatar,biography,gender;
    private Date birthday;
    private String birthplace,popular_reason;
    private int numberCredits;
    private String nickname,urlSocialMedia;
}
