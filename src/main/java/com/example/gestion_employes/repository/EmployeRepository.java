package com.example.gestion_employes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion_employes.model.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}