package com.bank.ebanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.ebanking.model.Virement;
import com.bank.ebanking.service.VirementService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/virement")
public class VirementController {
	VirementService virementService;
	@Autowired
	public VirementController(VirementService service) {
		virementService = service;
	}
	@PostMapping("/add/{idCreancier}/{idDebiteur}")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Virement virement,@PathVariable(name="idCreancier") Long idCreancier,@PathVariable(name="idDebiteur") Long idDebiteur) {
		virementService.addVirement(virement, idCreancier, idDebiteur);
    }
	
	@GetMapping("/getAllVirEnv/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Virement> getAllVirEnv(@PathVariable(name="id") long id) {

        return virementService.getAllVirEnv(id);
    }
	@GetMapping("/getAllVirRec/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Virement> getAllVirRec(@PathVariable(name="id") long id) {

        return virementService.getAllVirRec(id);
    }
	
}
