package com.simform.departmentservice;

import com.simform.departmentservice.model.Department;
import com.simform.departmentservice.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DepartmentServiceApplication {

	@Autowired
	DepartmentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

	@PostConstruct
	void init() {
		repository.addDepartment(new Department(1L, "Java"));
		repository.addDepartment(new Department(2L, "Python"));
		repository.addDepartment(new Department(3L, "Node"));
	}
}
