package com.ests.ems.controlleurs;

import com.ests.ems.entities.Employee;
import com.ests.ems.services.DepartmentService;
import com.ests.ems.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    // Affichage de la liste des employés avec pagination
    @GetMapping
    public String listEmployees(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model) {
        Page<Employee> employees = employeeService.getEmployeesWithPagination(page, size);
        model.addAttribute("employees", employees);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employees.getTotalPages());
        model.addAttribute("pageSize", size);
        return "employee-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employee-form";
    }

    @PostMapping
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employee-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    // Recherche des employés avec pagination
    @GetMapping("/search")
    public String searchEmployees(@RequestParam("mc") String mc,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  Model model) {
        Page<Employee> employees = employeeService.searchEmployees(mc, page, size);
        model.addAttribute("employees", employees);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employees.getTotalPages());
        model.addAttribute("pageSize", size);
        model.addAttribute("searchKeyword", mc);
        return "employee-list";
    }
    @PostMapping("/deleteMultiple")
    public String deleteMultiple(@RequestParam List<Long> employeeIds) {
        employeeService.deleteEmployees(employeeIds);
        return "redirect:/employees";
    }



}
