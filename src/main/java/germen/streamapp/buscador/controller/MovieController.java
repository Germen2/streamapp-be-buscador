package germen.streamapp.buscador.controller;

import germen.streamapp.buscador.model.Movie;
import germen.streamapp.buscador.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    // GET /movies → obtener todas las películas
    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    // GET /movies/{id} → obtener una película por id
    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    // POST /movies → crear una nueva película
    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    // PUT /movies/{id} → actualizar película existente
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    // DELETE /movies/{id} → eliminar una película por id
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    // GET /movies/search → buscar una pelicula
    @GetMapping("/search")
    public List<Movie> searchMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String year
            //@RequestParam(required = false) Long categoryId
    ) {
        return movieService.searchMovies(title, year);
    }
}

