package com.example.movie_App;

import com.example.movie_App.Movie;
import com.example.movie_App.MovieRepository;
import java.util.List;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    Client client = new Client();

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
        this.client = new Client();
    }

    public Movie addMovie(Movie movie) {
        try {
            String query = "Write a short description for the movie: " + movie.getTitle();
            GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash-001", query, null);
            movie.setDescription(response.text());
        }catch (Exception e){
            movie.setDescription("Description not available.");
        }
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }


}
