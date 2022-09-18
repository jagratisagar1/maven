package net.guides.springboot2.springboot2jpacrudexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import net.guides.springboot2.crud.Application;
import net.guides.springboot2.crud.model.Patient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEmployeeById() {
		Patient employee = restTemplate.getForObject(getRootUrl() + "/patient/1", Patient.class);
		System.out.println(employee.getFirstName());
		assertNotNull(employee);
	}

	@Test
	public void testCreateEmployee() {
		Patient employee = new Patient();
		employee.setEmailId("admin@gmail.com");
		employee.setFirstName("admin");
		//employee.setLastName("admin");

		ResponseEntity<Patient> postResponse = restTemplate.postForEntity(getRootUrl() + "/patient", employee, Patient.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		int id = 1;
		Patient employee = restTemplate.getForObject(getRootUrl() + "/patient/" + id, Patient.class);
		employee.setFirstName("admin1");
		//employee.setLastName("admin2");

		restTemplate.put(getRootUrl() + "/patient/" + id, employee);

		Patient updatedEmployee = restTemplate.getForObject(getRootUrl() + "/patient/" + id, Patient.class);
		assertNotNull(updatedEmployee);
	}

	@Test
	public void testDeleteEmployee() {
		int id = 2;
		Patient employee = restTemplate.getForObject(getRootUrl() + "/patient/" + id, Patient.class);
		assertNotNull(employee);

		restTemplate.delete(getRootUrl() + "/patient/" + id);

		try {
			employee = restTemplate.getForObject(getRootUrl() + "/patient/" + id, Patient.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
