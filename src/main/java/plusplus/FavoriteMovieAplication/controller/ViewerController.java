package plusplus.FavoriteMovieAplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plusplus.FavoriteMovieAplication.entity.MovieDemo;
import plusplus.FavoriteMovieAplication.entity.MovieDisplay;
import plusplus.FavoriteMovieAplication.entity.Viewer;
import plusplus.FavoriteMovieAplication.service.ViewerInfo;

import java.util.List;

@RestController
@RequestMapping(value="/viewer")
public class ViewerController {
    @Autowired
    ViewerInfo viewerInfo;
    @GetMapping
    public String login(@RequestBody Viewer viewer)
    {
        return viewerInfo.login(viewer.getUsername(),viewer.getPassword());
    }
    @PostMapping
    public void register(@RequestBody Viewer viewer)
    {
        viewerInfo.addViewer(viewer.getUsername(),viewer.getPassword(),viewer.getEmail());
    }
    @PutMapping
    public void update(@RequestBody Viewer viewer)
    {
        viewerInfo.updateViewer(viewer.getId(),viewer.getUsername(),viewer.getPassword(),viewer.getEmail());
    }
    @PostMapping(value="/display")
    public void addMovieDisplay(@RequestBody MovieDisplay movieDisplay)
    {
        viewerInfo.addMovieDisplay(movieDisplay.getViewerID(),movieDisplay.getMovieID(),movieDisplay.getRank());
    }
    @PutMapping(value="/display")
    public void updateMovieDisplay(@RequestBody MovieDisplay movieDisplay)
    {
        viewerInfo.updateMovieDisplay(movieDisplay.getViewerID(),movieDisplay.getMovieID(),movieDisplay.getRank());
    }
    @GetMapping(value="/display/{viewerID}")
    public List<MovieDisplay> showMovieDisplay(@PathVariable int viewerID)
    {
        return viewerInfo.getMoviesDisplayOfViewer(viewerID);
    }
    @GetMapping(value = "/{viewerID}")
    public List<MovieDemo> showMovieOfViewer(@PathVariable int viewerID)
    {
        return viewerInfo.showMoviesOfViewer(viewerID);
    }
}
