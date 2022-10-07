package com.csvapp.app;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import com.csvapp.model.Client;
import com.csvapp.service.CsvService;

public class CsvApp {

	private static CsvService csvService;
	
	static Logger log = Logger.getLogger(CsvApp.class.getName());

	public static void main(String[] args) {

		csvService = new CsvService();

		List<Client> clients = csvService.readCsv(
				"C:\\Data\\test.csv");
		log.info(clients.toString());
		log.info("#################");
		log.warning("test warning");
		csvService.sort(clients);
		log.info(clients.toString());;
		// clients = filteredClient(clients, new BigDecimal(5));
		// System.out.println(clients);
		clients = csvService.filteredClientV2(clients, new BigDecimal(5));
		log.info(clients.toString());

	}

}
