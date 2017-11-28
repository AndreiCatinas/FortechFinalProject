package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.Booking;
import com.fortech.entity.Product;
import com.fortech.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	public List<Booking> getAllBookings() {
		List<Booking> bookings = new ArrayList<>();
		bookingRepository.findAll().forEach(bookings::add);
		return bookings;
	}

	public List<Booking> getAllActive(Boolean active) {
		List<Booking> bookings = new ArrayList<>();
		bookingRepository.findByActive(active).forEach(bookings::add);
		return bookings;
	}

	public List<Booking> getAllActiveByUser(Boolean active, String username) {
		List<Booking> bookings = new ArrayList<>();
		bookingRepository.findByActiveAndUserUsername(active, username).forEach(bookings::add);
		return bookings;
	}

	public List<Booking> getAllBookingsByUser(Integer userId) {
		List<Booking> bookings = new ArrayList<>();
		bookingRepository.findByUserId(userId).forEach(bookings::add);
		return bookings;
	}

	public List<Booking> getAllBookingsByUsername(String username) {
		List<Booking> bookings = new ArrayList<>();
		bookingRepository.findByUserUsername(username).forEach(bookings::add);
		return bookings;
	}

	public Booking getBooking(Integer id) {
		return bookingRepository.findOne(id);
	}

	public void addBooking(String username, Integer productId) {
		Booking booking = new Booking();
		booking.setUser(userService.getUser(username));
		Product product = productService.getProduct(productId);
		product.setBooked(true);
		productService.updateProduct(product);
		booking.setProduct(product);
		bookingRepository.save(booking);
	}

	public void updateBooking(Booking booking) {
		bookingRepository.save(booking);
	}

	public void deleteBooking(Integer id) {
		Booking booking = bookingRepository.findOne(id);
		Product product = booking.getProduct();
		product.setBooked(false);
		booking.setActive(false);
		booking.setExpires(new java.sql.Date(System.currentTimeMillis()).toString());
		productService.updateProduct(product);
		bookingRepository.save(booking);
	}
}
