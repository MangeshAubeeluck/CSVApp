package com.csvapp.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.csvapp.model.Client;

public class CsvService {

	Logger log = Logger.getLogger(CsvService.class.getName()); // saving the all the logger details in the variable log

	/**
	 * Method to read csv file
	 * 
	 * @param filePath to csv file
	 * @return List of Client
	 */
	public List<Client> readCsv(String filePath) {
		List<Client> clients = new ArrayList<>();// list of lists to store data
		try {

			File file = new File(filePath);
			Scanner s = new Scanner(file);
			Long id = null;
			BigDecimal amount = null;
			// Reading until we run out of lines
			while (s.hasNextLine()) {
				List<String> lineData = Arrays.asList(s.nextLine().split(","));// splitting B

				try {
					id = Long.parseLong(lineData.get(0));
					amount = new BigDecimal(lineData.get(2));
				} catch (NumberFormatException e) {
					log.severe(e.getMessage());
					log.severe("Number input for line " + lineData + " is incorrect");
				} catch (IndexOutOfBoundsException ie) {
					log.severe(ie.toString());
					log.severe("No data for line " + lineData + " is incorrect");
				}

				Client client = new Client(id, lineData.get(1), amount);
				clients.add(client);
			}
			s.close();
		} catch (Exception e) {
			log.severe(e.toString());
		}
		return clients;

	}

	public void sort(List<Client> clients) {
		Collections.sort(clients);
	}

	public static List<Client> filteredClient(List<Client> clients, BigDecimal compare) {
		List<Client> results = new ArrayList<>();
		for (Client c : clients) {
			if (c.getAmountDue().compareTo(compare) > 0) {
				results.add(c);
			}
		}
		return results;
	}

	public List<Client> filteredClientV2(List<Client> clients, BigDecimal compare) {
		List<Client> results = new ArrayList<>();
		results = clients.stream().filter(x -> x.getAmountDue() != null 
											&& x.getAmountDue().compareTo(compare) > 0)
				.collect(Collectors.toList());

		return results;
	}

}
