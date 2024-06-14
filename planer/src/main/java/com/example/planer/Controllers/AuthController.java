package com.example.planer.Controllers;


import com.example.planer.Config.JwtUtil;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private KorisnikDetailsService korisnikDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

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
    public ResponseEntity<?> loginKorisnika(@RequestBody Korisnik korisnik) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(korisnik.getEmail(), korisnik.getLozinka())
            );

            final UserDetails korisnikDetails = korisnikDetailsService.loadUserByUsername(korisnik.getEmail());
            final String jwt = jwtUtil.generateToken(korisnikDetails);

            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    private static class AuthResponse {
        private final String jwt;

        public AuthResponse(String jwt) {
            this.jwt = jwt;
        }

        public String getJwt() {
            return jwt;
        }
    }
}
