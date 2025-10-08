package com.example.movie_App;

import com.example.movie_App.Movie;
import com.example.movie_App.MovieRepository;
import java.util.List;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.w3c.dom.Text;

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
            String query = "Write a short description for the movie: " + movie.getTitle() + "returned for plain text format";
            GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash-001", query, null);
            movie.setDescription(response.text().trim());
        }catch (Exception e){
            movie.setDescription("Description not available.");
        }

        try {
            String query = """
        You are a movie classification system.
        Given the following movie title, choose the most appropriate genre from this list: Drama, Comedy, or Action.

        Respond with only one word: either "Drama", "Comedy", or "Action".

        Movie Title: %s
        """.formatted(movie.getTitle());;
            GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash-001", query, null);
            movie.setGenre(response.text().trim());
        }catch (Exception e){
            movie.setGenre("Genre not available.");
        }

        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

}
