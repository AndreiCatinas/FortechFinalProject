package com.fortech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fortech.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

	public List<Booking> findByUserId(Integer userId);
	
}
