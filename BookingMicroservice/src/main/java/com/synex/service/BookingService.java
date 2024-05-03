package com.synex.service;

import java.util.List;

import com.synex.domain.Booking;

public interface BookingService {

	List<Booking> findAll();
	List<Booking> findByCustomerMobile(String customerMobile);
	Booking saveBooking(Booking booking);
	Booking findByBookingId(int bookingId);
	Booking saveCancelledBooking(Booking booking);
	Booking saveCompletedBooking(Booking booking);
	List<Booking> getCancelledBookings();
	List<Booking> getUpcomingBookings();
	List<Booking> getCompletedBookings();
}

