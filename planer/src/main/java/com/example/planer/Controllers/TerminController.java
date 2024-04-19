package com.example.planer.Controllers;

import com.example.planer.Models.Termin;
import com.example.planer.Models.Zadatak;
import com.example.planer.Services.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/termini")
public class TerminController {

    @Autowired
    TerminService terminService;

    @GetMapping
    public ResponseEntity<List<Termin>> getAllTermini() {
        List<Termin> termini = terminService.getAllTermini();
        return ResponseEntity.ok(termini);
    }

    @PostMapping("/dodaj")
    public ResponseEntity<Termin> createTermin(@RequestBody Termin termin) {
        try {
            Termin createdTermin = terminService.createTermin(termin);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTermin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Termin> getTerminById(@PathVariable Long id) {
        Termin termin = terminService.getTerminById(id);
        if (termin != null) {
            return ResponseEntity.ok(termin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Termin> updateTermin(@PathVariable Long id, @RequestBody Termin termin) {
        try {
            Termin updatedTermin = terminService.updateTermin(id, termin);
            return ResponseEntity.ok(updatedTermin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTermin(@PathVariable Long id) {
        terminService.deleteTermin(id);
        return ResponseEntity.noContent().build();
    }



}