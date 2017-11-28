package com.fortech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fortech.entity.Booking;
import com.fortech.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/bookings")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/{id}")
	public Booking getBooking(@PathVariable Integer id) {
		return bookingService.getBooking(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/bookings")
	public void addBooking(@RequestBody Booking booking, @RequestBody Integer userId, @RequestBody Integer modelId) {
		//<<<<<<<<<<<to be added soon >>>>>>>>>>>>>>>>>>>>>>>>>>
		bookingService.addBooking(booking);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/bookings/{id}")
	public void updateBooking(@RequestBody Booking booking) {
		bookingService.addBooking(booking);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/bookings/{id}")
	public void deleteBooking(@PathVariable Integer id) {
		bookingService.deleteBooking(id);
	}
}
