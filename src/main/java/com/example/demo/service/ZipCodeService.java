package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.exception.NotFoundException;
import com.example.demo.vo.ZipCodeVO;

public interface ZipCodeService {

	/**
	 * Descripción: Método para leear y reestructuras los archivos
	 * 
	 * @author jorgeluis
	 * @return ZipCodeVO
	 */
	public ZipCodeVO getZipCodeInfo(String zipCode) throws IOException, NotFoundException;

	/**
	 * Descripción: Método para obtener todos los CP iguales
	 * 
	 * @author jorgeluis
	 * @return ZipCodeVO
	 */
	public String getZipCodeSame(String line, String searchValue);
	
	/**
	 * Descripción: Método para contruir los objetos ZipCodeVO
	 * 
	 * @author jorgeluis
	 * @return ZipCodeVO
	 */
	public ZipCodeVO getZipCodeFormatted(List<String> list);
}
