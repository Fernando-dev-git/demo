package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.vo.SettlementsVO;
import com.example.demo.vo.ZipCodeVO;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private ServletContext context;
	
	@Test
	void contextLoads() throws IOException {
		
		//File initialFile = new File(context.getRealPath("/WEB-INF/test.txt"));
		//if(initialFile.exists())
		//	System.out.println("existe!!");
		
		File resource = new File(context.getRealPath("/WEB-INF/test.txt"));
		List<String> data = null;
		try {
			data = Files.readAllLines(resource.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (data != null) {
			data.remove(0);
			for (String str : data) {
				ZipCodeVO zipCodeVOReturn = getZipCodeFormatted(str, "01020");
				if (zipCodeVOReturn != null)
					System.out.println(zipCodeVOReturn.toString());
			}
		}
	}

	private ZipCodeVO getZipCodeFormatted(String line, String searchValue) {
		String[] strSplit = line.split(Pattern.quote("|"));
		if (strSplit[0].equals(searchValue)) {
			ZipCodeVO zipCodeVO = new ZipCodeVO();
			zipCodeVO.setZip_code(strSplit[0]);
			zipCodeVO.setLocality(strSplit[6]);
			zipCodeVO.setFederal_entity(strSplit[5]);
			zipCodeVO.setMunicipality(strSplit[4]);
			List<SettlementsVO> settlements = new ArrayList<>();
			settlements.add(new SettlementsVO(strSplit[2], strSplit[14], strSplit[3]));
			zipCodeVO.setSettlements(settlements);
			return zipCodeVO;
		}
		return null;
	}

}
