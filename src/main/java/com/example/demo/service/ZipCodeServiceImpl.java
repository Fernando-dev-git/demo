package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.vo.SettlementsVO;
import com.example.demo.vo.ZipCodeVO;

@Service
public class ZipCodeServiceImpl implements ZipCodeService {

	@Override
	public ZipCodeVO getZipCodeInfo(String zipCode) throws IOException, NotFoundException {
		ClassPathResource resourceOne = new ClassPathResource("static/CPMexico.txt");
		File resource = resourceOne.getFile();

		List<String> data = null;
		try {
			data = Files.readAllLines(resource.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> strAux = new ArrayList<>();
		if (data != null) {
			data.remove(0);
			for (String str : data) {
				String strResult = getZipCodeSame(str, zipCode);
				if (strResult == null) {
					continue;
				} else {
					strAux.add(strResult);
				}
			}
		}
		if (!strAux.isEmpty()) {
			return getZipCodeFormatted(strAux);
		}
		throw new NotFoundException("No se encontraron registros con este código postal.");
	}

	@Override
	public String getZipCodeSame(String line, String searchValue) {
		String[] strSplit = line.split(Pattern.quote("|"));
		if (strSplit[0].equals(searchValue)) {
			return line;
		}
		return null;
	}

	@Override
	public ZipCodeVO getZipCodeFormatted(List<String> list) {
		String[] strSplit = list.get(0).split(Pattern.quote("|"));
		ZipCodeVO zipCodeVO = new ZipCodeVO();
		zipCodeVO.setZip_code(strSplit[0]);
		zipCodeVO.setLocality(strSplit[5]);
		zipCodeVO.setFederal_entity(strSplit[4]);
		zipCodeVO.setMunicipality(strSplit[3]);
		List<SettlementsVO> settlements = new ArrayList<>();
		for (String str : list) {
			String[] strSeparated = str.split(Pattern.quote("|"));
			settlements.add(new SettlementsVO(strSeparated[1], strSeparated[13], strSeparated[2]));
		}
		zipCodeVO.setSettlements(settlements);
		return zipCodeVO;
	}

}
