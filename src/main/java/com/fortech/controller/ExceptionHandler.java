package com.fortech.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception exception) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.getModel().put("message", exception.getMessage());
		modelAndView.setViewName("/errors/exception");
		return modelAndView;
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView duplicatEntryErrorHandler(HttpServletRequest request, Exception exception) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.getModel().put("message",
				"Seems like you are trying to register a username or email that already exists, but then again, I've been wrong before.");
		modelAndView.setViewName("/errors/exception");
		return modelAndView;
	}

}
