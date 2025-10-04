package com.example.movie_App;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.movie_App.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long>{
}
