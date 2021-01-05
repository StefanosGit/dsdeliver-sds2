package com.devsuperior.dsdeliver.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController implements Serializable {

	private static final long serialVersionUID = -290549328621879339L;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll(){
		
		List<ProductDTO> products = productService.findAll();
		
		return ResponseEntity.ok().body(products);
		
		
	}


}
