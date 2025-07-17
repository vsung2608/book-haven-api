package com.bookhaven.interaction_service.service;

import com.bookhaven.interaction_service.entity.Rating;
import com.bookhaven.interaction_service.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;

    public void ratingByUser(Rating rating){
        var rate = ratingRepository.findByUserIdAndProductId(rating.getUserId(), rating.getProductId())
                        .orElse(new Rating());
        if(rate.getId() == null){
            rate.setUserId(rating.getUserId());
            rate.setProductId(rating.getProductId());
        }
        rate.setScore(rating.getScore());
        ratingRepository.save(rate);
    }
}
