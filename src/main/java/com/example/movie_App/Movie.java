package com.example.movie_App;

import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.Text;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double rating;

    @Column(length = 1000)
    private String description;

    private String genre;

}
