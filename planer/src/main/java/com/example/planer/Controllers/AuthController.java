package com.example.planer.Controllers;


import com.example.planer.Models.Korisnik;
import com.example.planer.Services.KorisnikDetailsService;
import com.example.planer.Services.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private KorisnikService korisnikService;

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

}
