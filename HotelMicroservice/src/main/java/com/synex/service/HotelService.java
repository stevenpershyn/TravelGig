package com.synex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Hotel;
import com.synex.repository.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	public List<Hotel> findHotel(String searchString){
		searchString = "%"+searchString+"%";
		return hotelRepository.findByHotelNameLikeOrCityOrStateLike(searchString, searchString, searchString);
	}
}
