package org.ez.vk.dao_import_file_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.vk.converter.IAccountDao;
import api.vk.jsonimport.IAccountDaoImport;
import entity.vk.account.DefaultAccount;

@Repository
public class AccountDaoImporter implements IAccountDaoImport {
	private final static String DEFAULT_LOCATION = "D:/work/vk-project-files/account";

	public void addAccount(String defaultAccountJSON) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			DefaultAccount defaultAccount = mapper.readValue(defaultAccountJSON, DefaultAccount.class);
			PrintWriter out = new PrintWriter(DEFAULT_LOCATION + "/" + defaultAccount.getId()+".json");
			out.println(defaultAccountJSON);
			out.close();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<String> getAllAccount() {
		File folder = new File(DEFAULT_LOCATION);

		List<String> listAccountJson = new ArrayList<String>();

		for (final File fileJson : folder.listFiles()) {
			String filePath = fileJson.getAbsolutePath();
			try {
				byte[] encoded = Files.readAllBytes(Paths.get(filePath));
				String content = new String(encoded, "UTF-8");
				listAccountJson.add(content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return listAccountJson;
	}

}
