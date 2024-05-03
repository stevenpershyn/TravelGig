package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synex.domain.Guest;
import com.synex.service.GuestService;

@Controller
@CrossOrigin(origins = "http://localhost:8282")
public class GuestController {

	@Autowired
	GuestService guestService;
	
	@PostMapping(value = "/guest")
	@ResponseBody
	public Guest saveGuest(@RequestBody Guest guest) {
		return guestService.saveGuest(guest);
	}
	
	@GetMapping(value = "/guest")
	@ResponseBody
	public List<Guest> getAllGuests(){
		return guestService.findAll();
	}
}
