package com.simform.departmentservice.controller;

import com.simform.departmentservice.client.EmployeeClient;
import com.simform.departmentservice.client.EmployeeFeignClient;
import com.simform.departmentservice.model.Department;
import com.simform.departmentservice.model.Employee;
import com.simform.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private RestTemplate restTemplate;
    //Bean needs to be initialized. Refer config/RestTemplateConfig

    @Autowired
    private EmployeeClient employeeClient;
    //Bean needs to be initialized. Refer config/WebClientConfig

    @Autowired
    private EmployeeFeignClient employeeFeignClient;

    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        LOGGER.info("Department find: id={}", id);
        Department department =  repository.findById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Department find");
        List<Department> departments
                = repository.findAll();
        departments.forEach(department ->
        {
            try {
                department.setEmployees(setEmployees(department.getId()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return  departments;
    }

    /*@GetMapping("/with-employees")
    public Mono<List<Department>> findAllWithEmployees() {
        LOGGER.info("Department find");
        List<Department> departments
                = repository.findAll();
        departments.forEach(department ->
        {
            try {
                department.setEmployees(setEmployees(department.getId()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Flux<Department> departmentFlux = Flux.fromIterable(departments);

        return departmentFlux.flatMapSequential(department ->
                fetchEmployeesByDepartmentIdAsync(department.getId())
                        .map(employees -> {
                            department.setEmployees(employees);
                            return department;
                        })
        ).collectList();

    }*/

    private List<Employee> setEmployees(Long id) throws InterruptedException
    {
        // Rest Template
//        LOGGER.info("Using RestTemplate");
//        Employee[] employees =  restTemplate.getForObject("http://localhost:8083/employee/department/"+id, Employee[].class);
        //OR use the service name instead to refrain from hardcoding host and port details. For implementing this, add
        //@LoadBalanced annotation in RestTemplateConfig
//        Employee[] employees =  restTemplate.getForObject("http://EMPLOYEE-SERVICE/employee/department/"+id, Employee[].class);
        //getForObject returns object while getForEntity returns entity such as Employee.class or Department.class
//        return Arrays.stream(employees).toList();
        //OR
//        return Arrays.asList(employees);
        //OR
        /*return restTemplate.exchange("http://localhost:8083/employee/department/"+id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Employee>>(){}).getBody();*/

        // Feign - declarative HTTP web client developed by Netflix
        LOGGER.info("Using Feign Client");
        return employeeFeignClient.findByDepartment(id);

        // Reactive (Web client)
//        LOGGER.info("Using Web Client");
//        return employeeClient.findByDepartment(id);
    }

	/*
    private Mono<List<Employee>> fetchEmployeesByDepartmentIdAsync(Long id) {
        // Fetch employees asynchronously using WebClient
        return Mono.fromCallable(() -> employeeClient.findByDepartment(id))
                .delayElement(Duration.ofSeconds(5));
    }*/
}
