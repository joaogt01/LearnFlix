package com.learnflix.controller;

import com.learnflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/learnflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

}
