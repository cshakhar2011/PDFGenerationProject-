package com.nt.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nt.entities.HomeEntity;
import com.nt.generator.PDFGenerator;
import com.nt.repository.HomeRepository;

@Service
public class HomeService {
	
	@Autowired
	private HomeRepository homeRepository;
	
	@Autowired
	private PDFGenerator generator;

	
	public ResponseEntity getAllData() throws FileNotFoundException {

		
		List<HomeEntity> findAll = homeRepository.findAll();
		ByteArrayInputStream bis = PDFGenerator.customerPDFReport(findAll);
		
		
		HttpHeaders headers = new HttpHeaders();

		
		headers.add("Content-Disposition", "attachment;filename=employees.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
		
		
	}


	public HomeEntity postData(HomeEntity homeEntity) {
	
		return this.homeRepository.save(homeEntity);
	}

}
