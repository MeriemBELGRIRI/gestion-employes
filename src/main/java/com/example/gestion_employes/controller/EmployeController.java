package com.example.gestion_employes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.example.gestion_employes.model.Employe;
import com.example.gestion_employes.service.EmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    // GET /api/employes : liste tous les employés
    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    // GET /api/employes/{id} : récupère un employé par id
    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        return employeService.getEmployeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/employes : crée un nouvel employé
    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeService.saveEmploye(employe);
    }

    // PUT /api/employes/{id} : met à jour un employé existant
    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable Long id, @RequestBody Employe employeDetails) {
        return employeService.getEmployeById(id)
                .map(employe -> {
                    employe.setNom(employeDetails.getNom());
                    employe.setPoste(employeDetails.getPoste());
                    employe.setSalaire(employeDetails.getSalaire());
                    Employe updated = employeService.saveEmploye(employe);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/employes/{id} : supprime un employé
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmploye(@PathVariable Long id) {
        return employeService.getEmployeById(id)
                .map(employe -> {
                    employeService.deleteEmploye(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
