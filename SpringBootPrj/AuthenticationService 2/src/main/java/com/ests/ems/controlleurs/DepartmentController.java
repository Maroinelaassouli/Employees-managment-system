package com.ests.ems.controlleurs;


import com.ests.ems.entities.Department;
import com.ests.ems.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Afficher la liste des départements
    @GetMapping
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "department-list";
    }

    // Afficher le formulaire d'ajout d'un département
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department-form";
    }

    // Ajouter un département
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute("department") Department department) {
        departmentService.addDepartment(department);
        return "redirect:/departments";
    }

    // Afficher le formulaire d'édition d'un département
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);  // Appeler le service directement
        model.addAttribute("department", department);
        return "department-form";  // Le formulaire de modification
    }


    // Modifier un département
    @PostMapping("/edit/{id}")
    public String updateDepartment(@PathVariable("id") Long id, @ModelAttribute("department") Department department) {
        department.setId(id);
        departmentService.addDepartment(department);
        return "redirect:/departments";
    }

    // Supprimer un département
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
