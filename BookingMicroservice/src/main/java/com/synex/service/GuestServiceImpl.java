package com.synex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Guest;
import com.synex.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService{

	@Autowired
	GuestRepository guestRepository;
	
	@Override
	public List<Guest> findAll() {
		return guestRepository.findAll();
	}

	@Override
	public Guest findById(int guestId) {
		Optional<Guest> opt = guestRepository.findById(guestId);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public Guest saveGuest(Guest guest) {
		return guestRepository.save(guest);
	}

	@Override
	public Guest updateById(int guestId) {
		return guestRepository.getById(guestId);
	}

	@Override
	public void deleteById(int guestId) {
		guestRepository.deleteById(guestId);
	}

}
