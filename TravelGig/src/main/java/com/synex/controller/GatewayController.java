package com.synex.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.component.BookingComponent;
import com.synex.component.HotelComponent;
import com.synex.domain.User;
import com.synex.service.UserService;

@RestController
public class GatewayController {

	@Autowired
	HotelComponent hotelComponent;
	
	@Autowired
	BookingComponent bookingComponent;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/findHotel/{searchString}", method = RequestMethod.GET)
	public JsonNode findHotel(@PathVariable String searchString, Principal principle) {
		
		System.out.println("Welcome "+principle.getName());
		System.out.println(userService.findByUserName(principle.getName()).getMobile());
		return hotelComponent.findHotel(searchString);
	}
	
	@RequestMapping(value = "/getMobile", method = RequestMethod.GET)
	public String getMobile(Principal principle) {
		return userService.findByUserName(principle.getName()).getMobile();
	}
	
	@RequestMapping(value = "/getUsername", method = RequestMethod.GET)
	public String getUsername(Principal principle) {
		return principle.getName();
	}
	
	@RequestMapping(value = "/getEmail", method = RequestMethod.GET)
	public String getEmail(Principal principle) {
		return userService.findByUserName(principle.getName()).getEmail();
	}
	
	@RequestMapping(value = "/booking/{bookingId}", method = RequestMethod.GET)
	public JsonNode getBookingId(@PathVariable String bookingId) {
		return bookingComponent.getBookingId(bookingId);
	}
	
	@RequestMapping(value = "/review/{reviewId}", method = RequestMethod.GET)
	public JsonNode getReview(@PathVariable String reviewId) {
		return bookingComponent.getReviewId(reviewId);
	}
	
	@RequestMapping(value = "/booking", method=RequestMethod.POST)
	public ResponseEntity<JsonNode> saveBooking(@RequestBody JsonNode json){
		JsonNode booking = bookingComponent.saveBooking(json);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guest", method=RequestMethod.POST)
	public ResponseEntity<JsonNode> saveGuest(@RequestBody JsonNode json){
		JsonNode guest = bookingComponent.saveGuest(json);
		return new ResponseEntity<>(guest, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/review", method=RequestMethod.POST)
	public ResponseEntity<JsonNode> saveReview(@RequestBody JsonNode json){
		JsonNode review = bookingComponent.saveReview(json);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveCancelBooking/{bookingId}", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveCancelBooking(@PathVariable String bookingId){
		JsonNode booking = bookingComponent.saveCancelledBooking(bookingId);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveCompleteBooking/{bookingId}", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveCompleteBooking(@PathVariable String bookingId){
		JsonNode booking = bookingComponent.saveCompletedBooking(bookingId);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/viewBooking", method = RequestMethod.GET)
	public JsonNode getAllBookings() {
		return bookingComponent.getAllBookings();
	}
	
}
