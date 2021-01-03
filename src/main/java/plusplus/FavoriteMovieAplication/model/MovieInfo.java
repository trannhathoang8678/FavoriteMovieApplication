package plusplus.FavoriteMovieAplication.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plusplus.FavoriteMovieAplication.JpaConfig;

@Configuration
public class MovieInfo {
    @Autowired
    JpaConfig jpaConfig;
    @Bean
    public void addType()
    {

    }
}
