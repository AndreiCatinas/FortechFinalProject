package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.Booking;
import com.fortech.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	public List<Booking> getAllBookings() {
		List<Booking> bookings = new ArrayList<>();
		bookingRepository.findAll().forEach(bookings::add);
		return bookings;
	}
	
	public List<Booking> getAllBookingsByUser(Integer userId) {
		List<Booking> bookings = new ArrayList<>();
		bookingRepository.findByUserId(userId).forEach(bookings::add);
		return bookings;
	}
	
	public Booking getBooking(Integer id) {
		return bookingRepository.findOne(id);
	}
	
	public void addBooking(Booking booking) {
		bookingRepository.save(booking);
	}
	
	public void updateBooking(Booking booking) {
		bookingRepository.save(booking);
	}
	
	public void deleteBooking(Integer id) {
		bookingRepository.delete(id);
	}
}
