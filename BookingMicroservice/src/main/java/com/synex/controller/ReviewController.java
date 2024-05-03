package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synex.domain.Review;
import com.synex.service.ReviewService;

@Controller
@CrossOrigin(origins = "http://localhost:8282")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping(value = "/review/{reviewId}")
	@ResponseBody
	public Review getReviewById(@PathVariable int reviewId) {
		return reviewService.findById(reviewId);
	}
	
	@PostMapping(value = "/review")
	@ResponseBody
	public Review saveReview(@RequestBody Review review) {
		return reviewService.saveReview(review);
	}
}
