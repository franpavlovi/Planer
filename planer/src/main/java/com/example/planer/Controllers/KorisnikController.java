package com.example.planer.Controllers;

import com.example.planer.Models.Korisnik;
import com.example.planer.Services.KorisnikService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;


    @PostMapping("/registracija")
    public ResponseEntity<String> registerKorisnika(@RequestBody Korisnik korisnik) {
        try {
            korisnikService.registerKorisnik(korisnik);
            return new ResponseEntity<>("Registracija uspješna", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/prijava")
    public ResponseEntity<String> loginKorisnika(@RequestBody Korisnik korisnik) {
        try {
            korisnikService.loginKorisnik(korisnik.getEmail(), korisnik.getLozinka());
            return new ResponseEntity<>("Prijava uspješna", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    //ADMIN
    @GetMapping
    public ResponseEntity<List<Korisnik>> getAllKorisnici(){
        List<Korisnik> korisnik = korisnikService.getAllKorisnici();
        return ResponseEntity.ok(korisnik);
    }

    //ADMIN
    @GetMapping("/{id}")
    public ResponseEntity<Korisnik> getKorisnikById(@PathVariable Long id) {
        Korisnik korisnik = korisnikService.getKorisnikById(id);
        if (korisnik != null) {
            return ResponseEntity.ok(korisnik);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //ADMIN
    @PostMapping
    public ResponseEntity<Korisnik> createKorisnik(@RequestBody Korisnik korisnik) {
        Korisnik createdKorisnik = korisnikService.createKorisnik(korisnik);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdKorisnik);
    }

    //ADMIN
    @PutMapping("/{id}")
    public ResponseEntity<Korisnik> updateKorisnik(@PathVariable Long id, @RequestBody Korisnik korisnik) {
        try {
            Korisnik updatedKorisnik = korisnikService.updateKorisnik(id, korisnik);
            return ResponseEntity.ok(updatedKorisnik);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKorisnik(@PathVariable Long id) {
        korisnikService.deleteKorisnik(id);
        return ResponseEntity.noContent().build();
    }
}
