package com.dcc.movie_api.controller;

import com.dcc.movie_api.data.Movie;
import com.dcc.movie_api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@RequestMapping(headers = "Access-Control-Allow-Origin")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @PostMapping("/addMovie")
    ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        try {
            movieService.saveMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(movie);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/movies")
    public List<Movie> findAllMovies(){
        return movieService.getAllMovies();
    }


    @GetMapping("/movies/{id}")
    ResponseEntity<Movie> findMovieById(@PathVariable Integer id){
        Movie movieFound = movieService.getMovieById(id);
        if(movieFound != null){
            return ResponseEntity.ok(movieFound);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @GetMapping("/findByGenre/{genre}")
    ResponseEntity<List<Movie>> findMovieByGenre(@PathVariable String genre){
        List<Movie> genreFound = movieService.getByGenre(genre);
        if(genreFound.size() != 0){
            return ResponseEntity.ok(genreFound);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @GetMapping("/findByName/{name}")
    ResponseEntity<Movie> findMovieByName(@PathVariable String name){
        Movie nameFound = movieService.getByName(name);
        if(nameFound != null){
            return ResponseEntity.ok(nameFound);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/editMovie/{id}")
    ResponseEntity<Movie> editMovie(@PathVariable Integer id,@RequestBody Movie movie){
        Movie updateMovie = movieService.getMovieById(id);
        try {
            updateMovie.setName(movie.getName());
            updateMovie.setGenre(movie.getGenre());
            updateMovie.setDirector(movie.getDirector());
            updateMovie.setImageURL(movie.getImageURL());
            movieService.saveMovie(updateMovie);
            return ResponseEntity.ok(updateMovie);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/deleteMovie/{id}")
    ResponseEntity<String> deleteMovie(@PathVariable Integer id){
        try {
            Movie reMovie = movieService.getMovieById(id);
            movieService.removeMovie(reMovie);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
