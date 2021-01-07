package plusplus.FavoriteMovieAplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plusplus.FavoriteMovieAplication.entity.*;
import plusplus.FavoriteMovieAplication.service.FamousPeopleInfo;
import plusplus.FavoriteMovieAplication.service.MovieInfo;
import plusplus.FavoriteMovieAplication.service.ViewerInfo;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    MovieInfo movieInfo;
    @Autowired
    ViewerInfo viewerInfo;
    @Autowired
    FamousPeopleInfo famousPeopleInfo;
    @PostMapping(value = "/movie")
    public void addMovie(@RequestBody Movie movie) {
        movieInfo.addMovie(movie.getName(),movie.getCreatedYear(),movie.getUrlPoster(),movie.getOverview(),movie.getScore(),
                movie.getLength());
    }
    @PutMapping(value = "/movie")
    public void updateMovie(@RequestBody Movie movie) {
        movieInfo.updateMovie(movie.getId(),movie.getName(),movie.getCreatedYear(),movie.getUrlPoster(),movie.getOverview()
                ,movie.getScore(),movie.getLength());
    }
    @DeleteMapping(value = "/movie")
    public void deleteMovie(@RequestParam int movieID)
    {
        movieInfo.deleteMovie(movieID);
    }
    @PostMapping(value = "/viewer")
    public void addViewer(@RequestBody Viewer viewer) {
        viewerInfo.addViewer(viewer.getUsername(),viewer.getPassword(),viewer.getEmail());
    }
    @PutMapping(value = "/viewer")
    public void updateViewer(@RequestBody Viewer viewer) {
        viewerInfo.updateViewer(viewer.getId(),viewer.getUsername(),viewer.getPassword(),viewer.getEmail());
    }
    @DeleteMapping(value = "/viewer")
    public void deleteViewer(@RequestParam int viewerID)
    {
        viewerInfo.deleteViewer(viewerID);
    }
    @PostMapping(value = "/famous-people")
    public void addFamousPeople(@RequestBody FamousPeople famousPeople) {
        famousPeopleInfo.addFamousPeople(famousPeople.getName(),famousPeople.getUrlAvatar(),famousPeople.getBiography(),
                famousPeople.getGender(),famousPeople.getBirthday(),famousPeople.getBirthplace(),famousPeople.getPopular_reason(),
                famousPeople.getNumberCredits(), famousPeople.getNickname(),famousPeople.getUrlSocialMedia());
    }
    @PutMapping(value = "/famous-people")
    public void updateFamousPeople(@RequestBody FamousPeople famousPeople) {
        famousPeopleInfo.updateFamousPeople(famousPeople.getId(),famousPeople.getName(),famousPeople.getUrlAvatar(),famousPeople.getBiography(),
                famousPeople.getGender(),famousPeople.getBirthday(),famousPeople.getBirthplace(),famousPeople.getPopular_reason(),
                famousPeople.getNumberCredits(), famousPeople.getNickname(),famousPeople.getUrlSocialMedia());
    }
    @DeleteMapping(value = "/famous-people")
    public void deleteFamousPeople(@RequestParam int famousPeopleID)
    {
        famousPeopleInfo.deleteFamousPeople(famousPeopleID);
    }
    @PostMapping(value = "/relationship")
    public void addPeopleMovieRelationship(@RequestBody PeopleMovieRelationship relationship) {
        famousPeopleInfo.addPeopleMovieRelationship(relationship.getMovieID(),relationship.getPeopleID(),
                relationship.getRole(),relationship.getTime());
    }
    @PutMapping(value = "/relationship")
    public void updatePeopleMovieRelationship(@RequestBody PeopleMovieRelationship relationship) {
        famousPeopleInfo.updatePeopleMovieRelationship(relationship.getMovieID(), relationship.getPeopleID()
                , relationship.getRole(),relationship.getTime() );
    }
    @DeleteMapping(value = "/relationship")
    public void deletePeopleMovieRelationship(@RequestParam int movieID,@RequestParam int peopleID)
    {
        famousPeopleInfo.deletePeopleMovieRelationship(movieID,peopleID);
    }
    @PostMapping(value = "/movieType")
    public void addMovieType(@RequestBody String name) {
        movieInfo.addMovieType(name);
    }
    @PutMapping(value = "/movieType")
    public void updateMovieType(@RequestBody MovieType movieType) {
        movieInfo.updateMovieType(movieType.getId(),movieType.getName() );
    }
    @DeleteMapping(value = "/movieType")
    public void deleteMovieType(@RequestParam int id)
    {
        movieInfo.deleteMovieType(id);
    }
    @PostMapping(value = "/typeofmovie")
    public void addTypeOfMovie(@RequestParam(name="type") int typeID,@RequestParam(name="movie") int movieID) {
        movieInfo.addTypeOfMovie(typeID,movieID);
    }
    @DeleteMapping(value = "/typeofmovie")
    public void deleteTypeOfMovie(@RequestParam(name="type") int typeID,@RequestParam(name="movie") int movieID)
    {
        movieInfo.deleteTypeOfMovie(typeID,movieID);
    }
}
