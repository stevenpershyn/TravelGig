package com.synex.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.component.BookingComponent;

import com.synex.domain.User;
import com.synex.repository.UserRepository;


@RestController
public class EmailController {

	/*@Autowired
	private BookingComponent bookingComponent;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired 
	UserRepository userRepository;
	
	@PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details)
    {
        String status=emailService.sendMail(details);
 
        return status;
    }
	
	@PostMapping("/sendMail/{bookingId}")
	public String sendMail(@PathVariable String bookingId /*, Model model, Principal principal) {
		/*JsonNode booking = bookingComponent.getBookingId(bookingId);
		System.out.println(booking);
		String result;
		String recipient;
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-mm-dd");
		String formattedCheckInDate = booking.get("checkInDate").asText().substring(0, 10);
		String formattedCheckOutDate = booking.get("checkOutDate").asText().substring(0, 10);
		
		//String username = principal.getName();
		//User user = userService.findByUserName(username);
		//model.addAttribute("username", username);
		//String email = user.getEmail();

		
		if(booking != null) {
			recipient = "stevenpershyn@gmail.com";
			EmailDetails emailDetails = new EmailDetails();
			emailDetails.setRecipient(recipient);
			emailDetails.setSubject("Booking Details for #" + booking.get("bookingId").asText());
			emailDetails.setMsgBody("Dear " + booking.get("userName") + ",\n\n"
					+ "Here are your hotel booking details:\n\n"
					+ "ID : #" + booking.get("bookingId") + "\n"
					+ "Check-in Date: " + formattedCheckInDate + "\n"
					+ "Check-out Date: " + formattedCheckOutDate + "\n"
					+ "No. of Rooms: " + booking.get("noRooms") + "\n"
					+ "Total Price: " + booking.get("price") + "\n\n"
					+ "Thank you");
			result = emailService.sendMail(emailDetails);
		} else {
			result = "Error: Booking not found or unauthorized access";
		}
		
		return result;
	}*/
}
