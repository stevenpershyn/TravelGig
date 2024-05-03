package com.synex.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BookingComponent {

	public JsonNode saveBooking(JsonNode json) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/booking", request, Object.class);
		Object object = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(object, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode saveGuest(JsonNode json) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/guest", request, Object.class);
		Object object = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(object, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode saveReview(JsonNode json) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/review", request, Object.class);
		Object object = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(object, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode saveCancelledBooking(String bookingId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8484/booking/cancelledBooking/"+bookingId, HttpMethod.POST, request, Object.class);
		Object object = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(object, JsonNode.class);
		return returnObj;
		
	}
	
	public JsonNode saveCompletedBooking(String bookingId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8484/booking/completedBooking/"+bookingId, HttpMethod.POST, request, Object.class);
		Object object = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(object, JsonNode.class);
		return returnObj;
		
	}
	public JsonNode getBookingId(String bookingId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/booking/" + bookingId, Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	public JsonNode getReviewId(String reviewId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/review/" + reviewId, Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode getAllBookings() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/booking", Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
}
