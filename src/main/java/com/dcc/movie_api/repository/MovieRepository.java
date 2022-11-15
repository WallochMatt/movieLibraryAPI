package com.dcc.movie_api.repository;

import com.dcc.movie_api.data.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
