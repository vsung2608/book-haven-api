package com.bookhaven.interaction_service.controller;

import com.bookhaven.interaction_service.entity.Rating;
import com.bookhaven.interaction_service.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rating")
public class RatingController {
    private final RatingService ratingService;

    @PostMapping()
    public void rating(@RequestBody Rating rating){
        ratingService.ratingByUser(rating);
    }
}
