package plusplus.FavoriteMovieAplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plusplus.FavoriteMovieAplication.entity.FamousPeople;
import plusplus.FavoriteMovieAplication.entity.FullMovie;
import plusplus.FavoriteMovieAplication.entity.MovieDemo;
import plusplus.FavoriteMovieAplication.model.FamousPeopleInfo;
import plusplus.FavoriteMovieAplication.model.MovieInfo;

import java.util.List;

@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    MovieInfo movieInfo;
    @Autowired
    FamousPeopleInfo famousPeopleInfo;
    @GetMapping(value = "/movie/{movieID}")
    public FullMovie getMovieByID(@PathVariable int movieID) {
        return movieInfo.findMovieByID(movieID);
    }

    @GetMapping(value = "/movie/n/{name}")
    public FullMovie getMovieByName(@PathVariable String name) {
        return movieInfo.findMovieByName(name);
    }

    @GetMapping(value = "/movie")
    public List<MovieDemo> getMovies() {
        return movieInfo.findAllMovies();
    }
    @GetMapping(value= "/people/{id}")
    public FamousPeople getPersonByID(@PathVariable int id)
    {
        return famousPeopleInfo.findFamousPeopleByID(id);
    }
    @GetMapping(value= "/people/n/{name}")
    public FamousPeople getPersonByName(@PathVariable String name)
    {
        return famousPeopleInfo.findFamousPeopleByName(name);
    }
}

