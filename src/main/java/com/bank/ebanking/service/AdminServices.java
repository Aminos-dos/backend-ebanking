package com.bank.ebanking.service;


import com.bank.ebanking.exception.NotFoundException;
import com.bank.ebanking.exception.UserNotFoundException;
import com.bank.ebanking.model.Admin;
import com.bank.ebanking.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServices {

    @Autowired
    AdminRepository rep;

    public void addAdmin(Admin admin){
        admin.setRole("Admin");
//        String password= admin.getPassword();
//        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        rep.save(admin);
    }


    public List<Admin> getAdmins(Long id){
//      List<Admin> admins = new ArrayList<Admin>();
      return rep.findAll();
    }


    public Admin getAdminByUsername(String username){
        Admin admin = rep.findAdminByUsername(username).orElseThrow(()-> new NotFoundException("Admin with username "+username+"not found"));
        return admin;
    }

   public Admin getAdminByCin(String cin){
        Admin admin = rep.findByCin(cin).orElseThrow(()-> new NotFoundException("Admin with cin "+cin+" not found"));
        return admin;
   }

   public void updateAdmin(Long id,Admin admin){
        Admin updatedAdmin = rep.findById(id).orElseThrow(()-> new NotFoundException("Admin with id "+id+" not found"));

       if(admin.getNom()!=null && !admin.getNom().isEmpty()) updatedAdmin.setNom(admin.getNom());
       if(admin.getPrenom()!=null && !admin.getPrenom().isEmpty()) updatedAdmin.setPrenom(admin.getPrenom());
       if(admin.getCin()!=null && !admin.getCin().isEmpty()) updatedAdmin.setCin(admin.getCin());
       if(admin.getTelephone()!=null && !admin.getTelephone().isEmpty()) updatedAdmin.setTelephone(admin.getTelephone());
       if(admin.getAdresse()!=null && !admin.getAdresse().isEmpty()) updatedAdmin.setAdresse(admin.getAdresse());
       if(admin.getUsername()!=null && !admin.getUsername().isEmpty()) updatedAdmin.setUsername(admin.getUsername());
       if(admin.getEmail()!=null && !admin.getEmail().isEmpty()) updatedAdmin.setEmail(admin.getEmail());
       if(admin.getPassword()!=null && !admin.getPassword().isEmpty()) updatedAdmin.setPassword(admin.getPassword());

       rep.save(updatedAdmin);
   }

}
