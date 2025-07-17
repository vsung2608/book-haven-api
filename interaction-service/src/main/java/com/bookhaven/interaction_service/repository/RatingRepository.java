package com.bookhaven.interaction_service.repository;

import com.bookhaven.interaction_service.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    Optional<Rating> findByUserIdAndProductId(String userId, int productId);
}
