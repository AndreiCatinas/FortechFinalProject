package com.fortech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fortech.entity.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

	public List<Booking> findByUserId(Integer userId);
	public List<Booking> findByUserUsername(String username);
	public List<Booking> findByActive(Boolean active);
	public List<Booking> findByActiveAndUserUsername(Boolean active, String username);
	public List<Booking> findByProductId(Integer productId);
}
