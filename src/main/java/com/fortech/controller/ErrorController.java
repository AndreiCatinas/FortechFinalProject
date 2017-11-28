package com.fortech.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorController {

	@RequestMapping(method = RequestMethod.GET, value = "/notfound")
	public ModelAndView notFound(ModelAndView modelAndView) {
		modelAndView.setViewName("/errors/404");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/internalservererror")
	public ModelAndView internalServerError(ModelAndView modelAndView) {
		modelAndView.setViewName("/errors/500");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/badrequest")
	public ModelAndView badRequest(ModelAndView modelAndView) {
		modelAndView.setViewName("/errors/400");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/forbidden")
	public ModelAndView accessDenied(ModelAndView modelAndView) {
		modelAndView.setViewName("errors/403");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/notallowed")
	public ModelAndView methodNotAllowed(ModelAndView modelAndView) {
		modelAndView.setViewName("errors/405");
		return modelAndView;
	}
}
