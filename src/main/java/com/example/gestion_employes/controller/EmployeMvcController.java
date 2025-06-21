package com.example.gestion_employes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_employes.model.Employe;
import com.example.gestion_employes.service.EmployeService;

@Controller
@RequestMapping("/employes")
public class EmployeMvcController {

    @Autowired
    private EmployeService employeService;

    @GetMapping
    public String viewEmployes(Model model) {
        model.addAttribute("employes", employeService.getAllEmployes());
        return "employes";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employe", new Employe());
        return "create_employe";
    }

    @PostMapping
    public String createEmploye(@ModelAttribute Employe employe) {
        employeService.saveEmploye(employe);
        return "redirect:/employes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employe employe = employeService.getEmployeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employé invalide:" + id));
        model.addAttribute("employe", employe);
        return "edit_employe";
    }

    @PostMapping("/update/{id}")
    public String updateEmploye(@PathVariable Long id, @ModelAttribute Employe employe) {
        employe.setId(id);
        employeService.saveEmploye(employe);
        return "redirect:/employes";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmploye(@PathVariable Long id) {
        employeService.deleteEmploye(id);
        return "redirect:/employes";
    }
}
