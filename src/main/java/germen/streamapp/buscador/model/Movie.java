package germen.streamapp.buscador.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    //private String genre;
    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    private String img;
    private String banner;

    @Column(name = "release_date")
    private String releaseDate;
    private String year;
    private String length;
    private String trailer;
    private Long buy;
    private Long rent;
    private Boolean active;
    private Long trending; //Previamente un objeto, ahora si es mayor a 0 es trend y el trending place es el valor
}
