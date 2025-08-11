package germen.streamapp.buscador.specifications;

import germen.streamapp.buscador.model.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecifications {

    public static Specification<Movie> titleContains(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Movie> yearEquals(String year) {
        return (root, query, cb) ->
                year == null ? null : cb.equal(root.get("year"), year);
    }

//    public static Specification<Movie> hasCategory(Long categoryId) {
//        return (root, query, cb) -> {
//            if (categoryId == null) return null;
//            return cb.equal(root.join("categories").get("id"), categoryId);
//        };
//    }
}