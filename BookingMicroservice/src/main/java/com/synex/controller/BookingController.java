package com.synex.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Booking;
import com.synex.repository.BookingRepository;
import com.synex.service.BookingService;

@Controller
@CrossOrigin(origins = "http://localhost:8282")
public class BookingController {

	@Autowired 
	BookingService bookingService;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@PostMapping(value = "/booking")
	@ResponseBody
	public Booking saveBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}
	
	@GetMapping(value = "/booking")
	@ResponseBody
	public List<Booking> getAllBookings(){
		return bookingService.findAll();
	}
	
	@GetMapping(value = "/booking/{bookingId}")
	@ResponseBody
	public Booking getBookingById(@PathVariable int bookingId){
		return bookingService.findByBookingId(bookingId);
	}
	
	@PostMapping(value = "/booking/completedBooking/{bookingId}")
	@ResponseBody
	public Booking saveCompletedBooking(@PathVariable int bookingId) {
		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		if(optionalBooking.isPresent()) {
			Booking booking = optionalBooking.get();
			if("cancelled".equals(booking.getStatus())) {
				return null;
			} else {
				return bookingService.saveCompletedBooking(booking);
			}
		}
		return null;
	}
	
	@PostMapping(value = "/booking/cancelledBooking/{bookingId}")
	@ResponseBody
	public Booking saveCancelledBooking(@PathVariable int bookingId) {
		Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		if(optionalBooking.isPresent()) {
			Booking booking = optionalBooking.get();
			if("completed".equals(booking.getStatus())) {
				return null;
			} else {
				return bookingService.saveCancelledBooking(booking);
			}
		}
		return null;
	}
}
