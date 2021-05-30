package com.bank.ebanking.controller;

import com.bank.ebanking.model.Admin;
import com.bank.ebanking.model.Agent;
import com.bank.ebanking.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/agent")
public class AgentController {


    AgentService agentService;
    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/add/{cin}/{nomAgence}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdmin(@RequestBody Agent agent, @PathVariable(name="cin") String cin, @PathVariable(name="nomAgence") String nomAgence  ) {
      agentService.addAgent(agent, nomAgence, cin);
    }

    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public List<Agent> getAllAgents(){
        return agentService.getAllAgents();
    }

    @PutMapping("/updateAgent/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAdmin(@PathVariable(name="id") Long id , @RequestBody(required=false) Agent agent)
    {
        agentService.updateAgent(id,agent);
    }
}
