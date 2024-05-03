package com.synex.service;

import com.synex.domain.Review;

public interface ReviewService {

	Review findById(int reviewId);
	Review saveReview(Review review);
}
