package com.fortech.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fortech.entity.Employee;
import com.fortech.entity.User;
import com.fortech.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET, value = "/employees/all")
	public ModelAndView getAllEmployees(ModelAndView modelAndView) {
		modelAndView.getModel().put("employees", employeeService.getAll());
		modelAndView.setViewName("/employees/allEmployee");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/active")
	public ModelAndView allActiveEmployees(ModelAndView mav) {
		mav.getModel().put("employees", employeeService.getAllActive(true));
		mav.setViewName("/employees/allEmployee");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/inactive")
	public ModelAndView allInactiveEmployees(ModelAndView mav) {
		mav.getModel().put("employees", employeeService.getAllActive(false));
		mav.setViewName("/employees/allEmployee");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/{id}")
	public ModelAndView getEmployee(ModelAndView mav, @PathVariable Integer id) {
		mav.getModel().put("employee", employeeService.getEmployee(id));
		mav.setViewName("redirect:/employees");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/add")
	public ModelAndView employeeForm(ModelAndView mav) {
		mav.getModel().put("employee", new Employee());
		mav.setViewName("/employees/addEmployee");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/employees/add")
	public ModelAndView addEmployee(ModelAndView modelAndView, @ModelAttribute @Valid Employee employee,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/employees/addEmployee");
			return modelAndView;
		}
		employeeService.addEmployee(employee);
		modelAndView.setViewName("redirect:/employees/all");
		return modelAndView;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/update/{id}")
	public ModelAndView editEmployee(ModelAndView mav, @PathVariable Integer id) {
		Employee emp = employeeService.getEmployee(id);
		mav.getModel().put("id", id);
		mav.getModel().put("employee", emp);
		mav.setViewName("/employees/updateEmployee");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/employees/update/{id}")
	public ModelAndView updateEmployee(ModelAndView mav, @ModelAttribute @Valid Employee employee, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			mav.setViewName("/employees/updateEmployee");
			return mav;
		}
		employeeService.updateEmployee(employee);
		mav.setViewName("redirect:/employees/active");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/details/{id}")
	public ModelAndView detailsEmployee(ModelAndView mav, @PathVariable Integer id) {
		Employee employee = employeeService.getEmployee(id);
		User user = employee.getUser();
		mav.getModel().put("employee", employee);
		mav.getModel().put("user", user);
		mav.setViewName("/employees/detailsEmployee");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/delete/{id}")
	public ModelAndView deleteEmployee(ModelAndView mav, @PathVariable Integer id) {
		employeeService.deleteEmployee(employeeService.getEmployee(id));
		mav.setViewName("redirect:/employees/active");
		return mav;
	}

}
