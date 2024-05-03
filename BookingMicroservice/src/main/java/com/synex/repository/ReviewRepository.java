package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

}
