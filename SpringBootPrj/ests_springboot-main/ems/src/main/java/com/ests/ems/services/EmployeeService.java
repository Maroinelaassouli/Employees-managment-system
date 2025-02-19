package com.ests.ems.services;
import com.ests.ems.repositories.EmployeeRepository;

import com.ests.ems.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    // Récupérer tous les employés avec pagination
    public Page<Employee> getEmployeesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    // Recherche des employés par mot-clé avec pagination
    public Page<Employee> searchEmployees(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findBySearchKeyword(keyword, pageable);
    }

    public void deleteEmployees(List<Long> employeeIds) {
        for (Long id : employeeIds) {
            employeeRepository.deleteById(id);
        }
    }


}
