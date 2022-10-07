package com.csvapp.app;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.csvapp.model.Client;
import com.csvapp.service.CsvService;

public class TestApp {

	private CsvService csvService;
	private static final int Client = 0;
	private List<Client> clients = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		csvService = new CsvService();

	}

	@ParameterizedTest
	@ValueSource(strings = { "unitest2.csv", "unitest1.csv", "test.csv" })
	void testFilter(String paths) {
		clients = csvService.readCsv("C:\\Data\\" + paths);
		clients = csvService.filteredClientV2(clients, new BigDecimal(5));
		Optional<Client> client = clients.stream().filter(c -> c.getAmountDue().compareTo(new BigDecimal(5)) <= 0)
				.findAny();
		assertFalse(client.isPresent());
	}

	@Test
	void testSortClients() {
		long[] manual_list = { 7, 3, 1 };
		clients = csvService.readCsv("C:\\data\\unitest1.csv");
		csvService.sort(clients);
		for (int i = 0; i < manual_list.length; i++) {
			assertTrue("The test is good", clients.get(i).getId() == manual_list[i]);
		}

	}

	@ParameterizedTest
	@ValueSource(strings = { "unitest2.csv", "unitest1.csv", "test.csv" })
	void testRead(String paths) {
		clients = csvService.readCsv("C:\\Data\\" + paths);
		assertTrue(clients.size() > 0);
	}

}
