package com.simform.employeeservice;

import com.simform.employeeservice.model.Employee;
import com.simform.employeeservice.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServiceApplication {

	@Autowired
	EmployeeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@PostConstruct
	void init() {
		repository.add(new Employee(1001L, 1L, "Rajvi", 25, "Software Engineer"));
		repository.add(new Employee(1002L, 1L, "Max", 32, "Senior Software Engineer"));
		repository.add(new Employee(1003L, 3L, "Manan", 21, "Software Engineer"));
		repository.add(new Employee(1004L, 2L, "Ron", 23, "Software Engineer"));
		repository.add(new Employee(1005L, 2L, "Pam", 27, "Senior Software Engineer"));
		repository.add(new Employee(1006L, 3L, "Dan", 32, "Lead Engineer"));
	}
}
