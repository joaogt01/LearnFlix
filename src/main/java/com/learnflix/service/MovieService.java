package com.learnflix.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.learnflix.entity.Category;
import com.learnflix.entity.Movie;
import com.learnflix.entity.Streaming;
import com.learnflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie){
        movie.setCategories(findCategories(movie.getCategories()));
        movie.setStreamings(findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    public List<Movie> findAll(){
        return movieRepository.findTop5ByOrderByRatingDesc();
    }

    public Optional<Movie> update(Long movieId, Movie updateMovie){
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isPresent()){

            List<Category> categories = this.findCategories(updateMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

            Movie movie = optionalMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setReleaseDate(updateMovie.getReleaseDate());
            movie.setRating(updateMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);
            return Optional.of(movie);
        }

        return Optional.empty();
    }

    public void delete(Long movieId){
        movieRepository.deleteById(movieId);
    }

    public List<Movie> findByCategory(Long categoryId){
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    private List<Category> findCategories(List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.findById(category.getId()).ifPresent(categoriesFound::add));
        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add));
        return streamingsFound;
    }



}
