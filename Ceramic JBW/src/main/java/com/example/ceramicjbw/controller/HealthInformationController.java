package com.example.ceramicjbw.controller;

import com.example.ceramicjbw.dto.HealthInformationDTO;
import com.example.ceramicjbw.service.HealthInformation.HealthInformationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/health-information") //http://localhost:1111/api/v1/health-information
@AllArgsConstructor
public class HealthInformationController {
    @Autowired
    private final HealthInformationService healthInformationService;

    @PostMapping("/create") //http://localhost:1111/api/v1/health-information/create
    public ResponseEntity<?> createHealthInformation(@RequestBody HealthInformationDTO healthInformationDTO) {
        try {
            HealthInformationDTO createdHealthInformation = healthInformationService.   createHealthInformation(healthInformationDTO);
            return new ResponseEntity<>(createdHealthInformation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") //http://localhost:1111/api/v1/health-information/{id}
    public ResponseEntity<?> getHealthInformationById(@PathVariable("id") Long id) {
        try {
            HealthInformationDTO healthInformationDTO = healthInformationService.getHealthInformationById(id);
            return new ResponseEntity<>(healthInformationDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")//http://localhost:1111/api/v1/health-information/all
    public ResponseEntity<?> getAllHealthInformation() {
        try {
            List<HealthInformationDTO> healthInformationDTOs = healthInformationService.getAllHealthInformation();
            return new ResponseEntity<>(healthInformationDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")//http://localhost:1111/api/v1/health-information/{id}
    public ResponseEntity<?> updateHealthInformation(@PathVariable("id") Long healthInformationId, @RequestBody HealthInformationDTO healthInformationDTO) {
        try {
            HealthInformationDTO updatedHealthInformation = healthInformationService.updateHealthInformation(healthInformationId, healthInformationDTO);
            return ResponseEntity.ok(updatedHealthInformation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")//http://localhost:1111/api/v1/health-information/{id}
    public ResponseEntity<?> deleteHealthInformation(@PathVariable("id") Long id) {
        try {
            healthInformationService.deleteHealthInformation(id);
            return new ResponseEntity<>("HealthInformation deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
