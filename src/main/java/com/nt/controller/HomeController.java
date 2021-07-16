package com.nt.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entities.HomeEntity;
import com.nt.service.HomeService;

@RestController
public class HomeController {

	@Autowired
	private HomeService homeService;

	@GetMapping("/pdf")
	public ResponseEntity all() throws FileNotFoundException {
		return this.homeService.getAllData();
	}

	@PostMapping("/pdf")
	public HomeEntity postData(@RequestBody HomeEntity homeEntity) {
		return this.homeService.postData(homeEntity);
	}



}
