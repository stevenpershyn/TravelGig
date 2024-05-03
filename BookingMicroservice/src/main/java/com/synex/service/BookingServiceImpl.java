package com.synex.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.domain.Booking;
import com.synex.domain.EmailDetails;
import com.synex.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	EmailService emailService;
	@Autowired
	BookingRepository bookingRepository;

	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	@Override
	public List<Booking> findByCustomerMobile(String customerMobile) {
		return bookingRepository.findByCustomerMobile(customerMobile);
	}

	@Override
	public Booking saveBooking(Booking booking) {
		booking = bookingRepository.save(booking);
		System.out.println(booking);
		String recipient;
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
		String formattedCheckInDate = formattedDate.format(booking.getCheckInDate());
		String formattedCheckOutDate = formattedDate.format(booking.getCheckOutDate());
		
		if(booking != null && !booking.getStatus().equals("completed")) {
			recipient = booking.getUserEmail();
			EmailDetails emailDetails = new EmailDetails();
			emailDetails.setRecipient(recipient);
			emailDetails.setSubject("Booking Details for #" + booking.getBookingId());
			emailDetails.setMsgBody("Dear " + booking.getUserName() + ",\n\n"
					+ "Here are your hotel booking details:\n\n"
					+ "ID : #" + booking.getBookingId() + "\n"
					+ "Check-in Date: " + formattedCheckInDate + "\n"
					+ "Check-out Date: " + formattedCheckOutDate + "\n"
					+ "No. of Rooms: " + booking.getNoRooms() + "\n"
					+ "Total Price: " + booking.getPrice() + "\n\n"
					+ "Thank you");
			emailService.sendMail(emailDetails);
		}
		
		return booking;
	}

	@Override
	public Booking findByBookingId(int bookingId) {
		Optional<Booking> opt = bookingRepository.findById(bookingId);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public Booking saveCancelledBooking(Booking booking) {
		booking.setStatus("cancelled");
		return bookingRepository.save(booking);
	}
	
	@Override
	public Booking saveCompletedBooking(Booking booking) {
		booking.setStatus("completed");
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> getCancelledBookings() {
		return bookingRepository.findByStatus("cancelled");
	}

	@Override
	public List<Booking> getUpcomingBookings() {
		return bookingRepository.findByStatus("upcoming");
	}

	@Override
	public List<Booking> getCompletedBookings() {
		return bookingRepository.findByStatus("completed");
	}
}
