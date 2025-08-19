package germen.streamapp.buscador.service;


import germen.streamapp.buscador.mapper.MovieMapper;
import germen.streamapp.buscador.model.Movie;
import germen.streamapp.buscador.repository.MovieRepository;
import germen.streamapp.buscador.specifications.MovieSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;


    //Que las regrese ordenadas por el id
    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    //Busca una pelicula por su id
    public Movie getMovie(Long id){
        return movieRepository.findById(id).orElse(null);
    }

    //Guarda una nueva pelicula
    public Movie saveMovie(Movie movie){
        Movie savedMovie = movieRepository.save(movie);
        return this.getMovie(savedMovie.getId());
    }

    //Actualiza una pelicula
    public Movie updateMovie(Long id, Movie movie){
        return movieRepository.findById(id).map(existingMovie -> {
            movieMapper.updateMovieFromSource(movie, existingMovie);
            return movieRepository.save(existingMovie);
        }).orElseThrow(() -> new RuntimeException("Película no encontrada"));
    }

    //Elimina una pelicula con su id
    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

    public List<Movie> searchMovies(String title, String year) {
        Specification<Movie> spec =
                MovieSpecifications.titleContains(title)
                        .and(MovieSpecifications.yearEquals(year));
                        //.and(MovieSpecifications.hasCategory(categoryId));

        return movieRepository.findAll(spec);
    }

    //Buscar peliculas con una lista de ids
    public List<Movie> getMoviesByIds(List<Long> ids) {
        return movieRepository.findByIdIn(ids);
    }

}
