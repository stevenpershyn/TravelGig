package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

	List<Booking> findByCustomerMobile(String customerMobile);
	List<Booking> findByStatus(String status);
}
