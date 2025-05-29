package com.example.gestion_employes.RepositoryDAO;
import com.example.gestion_employes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

