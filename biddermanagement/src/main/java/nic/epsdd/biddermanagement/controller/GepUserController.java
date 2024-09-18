package nic.epsdd.biddermanagement.controller;

import nic.epsdd.biddermanagement.entity.GepUser;
import nic.epsdd.biddermanagement.repository.GepUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gep-user")
public class GepUserController {
    @Autowired
    private GepUserRepository gepUserRepository;

    @GetMapping("/all-data") // http://localhost:2000/api/v1/gep-user/allData
    public ResponseEntity<?> getAllData() {
        List<GepUser> all = gepUserRepository.findAll();
        return ResponseEntity.ok(all);
    }
}