package com.ests.ems.services;

import com.ests.ems.entities.Department;
import com.ests.ems.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Récupérer tous les départements
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Récupérer un département par son ID
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }
    // Ajouter un département
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Modifier un département
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setName(departmentDetails.getName());
        return departmentRepository.save(department);
    }

    // Supprimer un département
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.delete(department);
    }
}
