package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.component.BookingComponent;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WelcomeController {

	@Autowired
	BookingComponent bookingComponent;
	
	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String welcome() {
		return "Home";
	}
	
	@RequestMapping(value = "/getBookings",method = RequestMethod.GET)
	public String welcome2() {
		return "booking";
	}
}
