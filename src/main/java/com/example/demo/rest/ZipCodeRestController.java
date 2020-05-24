package com.example.demo.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NotFoundException;
import com.example.demo.service.ZipCodeService;
import com.example.demo.vo.ZipCodeVO;

@RestController
@RequestMapping("/zip-codes")
public class ZipCodeRestController {

	@Autowired
	private ZipCodeService zipCodeService;

	@RequestMapping(path = "/{zip-code}", method = RequestMethod.GET)
	public ResponseEntity<ZipCodeVO> zipCode(@PathVariable("zip-code") String zipCode)
			throws NotFoundException, IOException {
		ZipCodeVO zipCodeVO = null;
		try {
			zipCodeVO = zipCodeService.getZipCodeInfo(zipCode);
		} catch (NotFoundException ex) {
			throw new NotFoundException(ex.getMessage());
		}
		return new ResponseEntity<ZipCodeVO>(zipCodeVO, HttpStatus.OK);
	}

	@RequestMapping(path = "/sayHello/{name}", method = RequestMethod.GET)
	public String sayHello(@PathVariable("name") String name) {
		return "Hello " + name + " welcome!!";
	}
}
