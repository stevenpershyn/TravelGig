package com.synex.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Review;
import com.synex.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewRepository reviewRepository;
	
	@Override
	public Review findById(int reviewId) {
		Optional<Review> opt = reviewRepository.findById(reviewId);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}

}
