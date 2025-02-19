package com.ests.ems.controlleurs;



import com.ests.ems.entities.Employee;
import com.ests.ems.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private EmployeeService employeeService;

    // Méthode pour afficher la page d'accueil
    @GetMapping("/")
    public String home(Model model) {
        // Ajout d'une liste d'employés au modèle pour l'affichage dans la vue
        //List<Employee> employees = employeeService.getAllEmployees();
        //  model.addAttribute("employees", employees);
        return "login";
    }



}