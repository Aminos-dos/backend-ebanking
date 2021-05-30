package com.bank.ebanking.repo;


import com.bank.ebanking.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Utilisateur, Long> {


}
