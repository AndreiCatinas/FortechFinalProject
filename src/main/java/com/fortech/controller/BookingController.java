package com.fortech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fortech.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	/**
	 * 
	 * @param modelAndView
	 * @return List of all existent bookings
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/all")
	public ModelAndView getAllBookings(ModelAndView modelAndView) {
		modelAndView.getModel().put("bookings", bookingService.getAllBookings());
		modelAndView.setViewName("/bookings/allBooking");
		return modelAndView;
	}
	
	/**
	 * 
	 * @param modelAndView
	 * @return List of all bookings currently active
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/active")
	public ModelAndView getAllBookingsBooked(ModelAndView modelAndView) {
		modelAndView.getModel().put("bookings", bookingService.getAllActive(true));
		modelAndView.setViewName("/bookings/allBooking");
		return modelAndView;
	}
	
	/**
	 * 
	 * @param modelAndView
	 * @return List of all bookings made and not currently active
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/history")
	public ModelAndView getAllBookingsAvailable(ModelAndView modelAndView) {
		modelAndView.getModel().put("bookings", bookingService.getAllActive(false));
		modelAndView.setViewName("/bookings/allBooking");
		return modelAndView;
	}
	
	/**
	 * 
	 * @param modelAndView
	 * @param username
	 * @return List of all bookings made by user with username
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/{username}")
	public ModelAndView getUserBookings(ModelAndView modelAndView, @PathVariable String username) {
		modelAndView.getModel().put("bookings", bookingService.getAllBookingsByUsername(username));
		modelAndView.setViewName("/bookings/allBooking");
		return modelAndView;
	}
	
	/**
	 * 
	 * @param modelAndView
	 * @param username
	 * @return List of all active bookings belonging to user with username
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/{username}/active")
	public ModelAndView getUserActiveBookings(ModelAndView modelAndView, @PathVariable String username) {
		modelAndView.getModel().put("bookings", bookingService.getAllActiveByUser(true, username));
		modelAndView.getModel().put("hidden", false);
		modelAndView.setViewName("/bookings/allBooking");
		return modelAndView;
	}
	
	/**
	 * 
	 * @param modelAndView
	 * @param username
	 * @return List of all inactive bookings belonging to user with username
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/{username}/history")
	public ModelAndView getUserHistoryBookings(ModelAndView modelAndView, @PathVariable String username) {
		modelAndView.getModel().put("bookings", bookingService.getAllActiveByUser(false, username));
		modelAndView.getModel().put("hidden", true);
		modelAndView.setViewName("/bookings/allBooking");
		return modelAndView;
	}
	
	/**
	 *  
	 * Add a booking
	 * @param modelAndView
	 * @param productId
	 * @param username
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/{username}/products/{productId}")
	public ModelAndView addBooking(ModelAndView modelAndView, @PathVariable Integer productId, @PathVariable String username) {
		bookingService.addBooking(username, productId);
		modelAndView.setViewName("redirect:/bookings/{username}/active");
		return modelAndView;
	}
	
	/**
	 * Cancel booking
	 * @param modelAndView
	 * @param id
	 * @param username
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/{username}/delete/{id}")
	public ModelAndView deleteBooking(ModelAndView modelAndView, @PathVariable Integer id, @PathVariable String username) {
		modelAndView.setViewName("redirect:/bookings/{username}/active");
		bookingService.deleteBooking(id);
		return modelAndView;
	}
	
	/**
	 * Cancel booking
	 * @param modelAndView
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bookings/delete/{id}")
	public ModelAndView adminDeleteBooking(ModelAndView modelAndView, @PathVariable Integer id) {
		modelAndView.setViewName("redirect:/bookings/all");
		bookingService.deleteBooking(id);
		return modelAndView;
	}
}
