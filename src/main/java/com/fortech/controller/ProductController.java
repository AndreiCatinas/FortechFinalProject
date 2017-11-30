package com.fortech.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fortech.entity.ProductDto;
import com.fortech.service.ProductCategoryService;
import com.fortech.service.ProductModelService;
import com.fortech.service.ProductService;
import com.google.gson.Gson;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private ProductModelService productModelService;

	@RequestMapping(method = RequestMethod.GET, value = "/products/all")
	public ModelAndView getAllProducts(ModelAndView modelAndView) {
		modelAndView.getModel().put("products", productService.getAllProducts());
		modelAndView.setViewName("/products/allProducts");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "products/booked")
	public ModelAndView getAllBookedPoducts(ModelAndView modelAndView) {
		modelAndView.getModel().put("products", productService.getAllProducts(true));
		modelAndView.setViewName("/products/allProducts");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "products/available")
	public ModelAndView getAllAvailablePoducts(ModelAndView modelAndView) {
		modelAndView.getModel().put("products", productService.getAllProducts(false));
		modelAndView.setViewName("/products/allProducts");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public ModelAndView addNewProduct(ModelAndView modelAndView) {
		Gson gson = new Gson();
		ProductDto productDto = new ProductDto();
		String categories = gson.toJson(productCategoryService.getAllProductCategories());
		String models = gson.toJson(productModelService.getAllProductModel());
		modelAndView.getModel().put("category", categories);
		modelAndView.getModel().put("model", models);
		modelAndView.getModel().put("productDto", productDto);
		modelAndView.setViewName("/products/addProduct");
		return modelAndView;
	}


	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public ModelAndView addNewProduct(ModelAndView modelAndView, @Valid @ModelAttribute ProductDto productDto,
			BindingResult bindingResult) throws ParseException {
		if (bindingResult.hasErrors()) {
			Gson gson = new Gson();
			//send categories and models as json to be used in javascript to populate dropdowns dynamically
			String categories = gson.toJson(productCategoryService.getAllProductCategories());
			String models = gson.toJson(productModelService.getAllProductModel());
			modelAndView.getModel().put("category", categories);
			modelAndView.getModel().put("model", models);
			modelAndView.getModel().put("productDto", productDto);
			modelAndView.setViewName("/products/addProduct");
			return modelAndView;
		}
		productService.addProduct(productDto);
		System.out.println(productDto.getAquisitionDate());
		modelAndView.setViewName("redirect:/products/all");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products/update/{id}")
	public ModelAndView updateProduct(ModelAndView modelAndView, @PathVariable Integer id) {
		ProductDto productDto = new ProductDto(productService.getProduct(id));
		Gson gson = new Gson();
		//send categories and models as json to be used in javascript to populate dropdowns dynamically
		String categories = gson.toJson(productCategoryService.getAllProductCategories());
		String models = gson.toJson(productModelService.getAllProductModel());
		modelAndView.getModel().put("category", categories);
		modelAndView.getModel().put("model", models);
		modelAndView.getModel().put("productDto", productDto);
		modelAndView.getModel().put("selCat", productDto.getCategory());
		modelAndView.getModel().put("selMod", productDto.getModel());
		modelAndView.setViewName("/products/updateProduct");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/products/update/{id}")
	public ModelAndView updateProduct(ModelAndView modelAndView, @ModelAttribute ProductDto productDto)
			throws ParseException {
		productService.updateProduct(productDto);
		modelAndView.setViewName("redirect:/products/all");
		return modelAndView;
	}

	//Can't send 'DELETE' or 'PUT' requests from html... 
	@RequestMapping(method = RequestMethod.POST, value = "/products/delete/{id}")
	public ModelAndView deleteProduct(ModelAndView modelAndView, @PathVariable Integer id) {
		productService.deleteProduct(id);
		modelAndView.setViewName("redirect:/products/all");
		return modelAndView;
	}
}
