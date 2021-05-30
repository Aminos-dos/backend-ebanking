package com.bank.ebanking.repo;

import com.bank.ebanking.model.Admin;
import com.bank.ebanking.model.Client;
import com.bank.ebanking.model.Compte;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {

	List<Compte> findByProprietaire(Client client);
	

}
