package com.synex.service;

import java.util.List;

import com.synex.domain.Guest;

public interface GuestService {

	List<Guest> findAll();
	Guest findById(int guestId);
	Guest saveGuest(Guest guest);
	Guest updateById(int guestId);
	void deleteById(int guestId);
}
