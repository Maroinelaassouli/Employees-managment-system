package com.ests.ems;

import com.ests.ems.entities.Employee;
import com.ests.ems.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootApplication
public class EmsApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Tester directement les méthodes du repository
		testSearchEmployeesWithKeyword();
		testFindByFirstName();
		testFindByLastName();
	}

	private void testSearchEmployeesWithKeyword() {
		String keyword = "aoubbad ossama";
		Pageable pageable = PageRequest.of(0, 5); // Page 0, taille 5

		Page<Employee> employees = employeeRepository.findBySearchKeyword(keyword, pageable);

		if (employees.isEmpty()) {
			System.out.println("Aucun résultat trouvé pour le mot-clé : " + keyword);
		} else {
			System.out.println("Résultats de la recherche pour le mot-clé '" + keyword + "':");
			employees.forEach(employee -> {
				System.out.println("ID: " + employee.getId() +
						", Nom: " + employee.getFirstName() + " " + employee.getLastName() +
						", Email: " + employee.getEmail() +
						", Département: " + (employee.getDepartment() != null ? employee.getDepartment().getName() : "N/A"));
			});
		}
	}

	private void testFindByFirstName() {
		String firstName = "AOUBBAD";
		List<Employee> employees = employeeRepository.findEmployeesByFirstNameNative(firstName);

		if (employees.isEmpty()) {
			System.out.println("Aucun employé trouvé avec le prénom : " + firstName);
		} else {
			System.out.println("Résultats pour findEmployeesByFirstNameNative('" + firstName + "'):");
			employees.forEach(employee -> System.out.println(employee));
		}
	}

	private void testFindByLastName() {
		String lastName = "OSSAMA";
		List<Employee> employees = employeeRepository.findByLastName(lastName);

		if (employees.isEmpty()) {
			System.out.println("Aucun employé trouvé avec le nom de famille : " + lastName);
		} else {
			System.out.println("Résultats pour findByLastName('" + lastName + "'):");
			employees.forEach(employee -> System.out.println(employee));
		}
	}
}
