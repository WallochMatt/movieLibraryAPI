package com.dcc.movie_api.controller;

import com.dcc.movie_api.data.Movie;
import com.dcc.movie_api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

    @GetMapping("/movies")
    public List<Movie> findAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie findMovieById(@PathVariable Integer id){
        return movieService.getMovieById(id);
    }

    @GetMapping("/findByGenre/{genre}")
    public List<Movie> findMovieByGenre(@PathVariable String genre){
        return movieService.getByGenre(genre);
    }

    @GetMapping("/findByName/{name}")
    public Movie findMovieByName(@PathVariable String name){
        return movieService.getByName(name);
    }


    ////////////////////////// WIP //////////////////////////////
    @PutMapping("/editMovie/{id}")
    public Movie editMovie(@PathVariable Integer id,@RequestBody Movie movie){
        Movie updateMovie = movieService.getMovieById(id);
        updateMovie.setName(movie.getName());
        updateMovie.setGenre(movie.getGenre());
        updateMovie.setDirector(movie.getDirector());
        return movieService.saveMovie(updateMovie);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable Integer id){
        try {
            Movie reMovie = movieService.getMovieById(id);
            movieService.removeMovie(reMovie);
            return "Movie deleted";
        }
        catch(Exception e){
            return "Something went wrong, we could not find this movie";
        }
    }







}
