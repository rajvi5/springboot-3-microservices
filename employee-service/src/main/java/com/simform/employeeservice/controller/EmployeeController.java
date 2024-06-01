package com.simform.employeeservice.controller;

import com.simform.employeeservice.model.Employee;
import com.simform.employeeservice.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository repository;

    @Autowired
    private Environment environment;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
            return repository.add(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        return repository.findAll();
    }

//    Implemented circuit breaker. Fallback method is called when this method fails.
//    For example an id which does not exist is passed
    @GetMapping("/{id}")
    @CircuitBreaker(name="employeeFindById", fallbackMethod = "runFallbackMethod")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id);
    }

//    Circuit breaker fallback method
//    Fallback method should have exactly same return type as the method who calls it
    public Employee runFallbackMethod(Exception e){
//        Pass dummy object back
        return new Employee(0l, 0l, "Sample", 0, "Unknown");
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Server Port={}, Employee find: departmentId={}",environment.getProperty("server.port") ,departmentId);
        return repository.findByDepartment(departmentId);
    }

}
