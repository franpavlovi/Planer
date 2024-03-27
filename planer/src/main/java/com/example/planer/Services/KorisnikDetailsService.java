package com.example.planer.Services;

import com.example.planer.Models.Korisnik;
import com.example.planer.Models.KorisnikDetails;
import com.example.planer.Repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class KorisnikDetailsService implements UserDetailsService {

    @Autowired
    KorisnikRepository korisnikRepository;

    public KorisnikDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Korisnik korisnik = korisnikRepository.findByEmail(username);
        if (korisnik == null) {
            throw new UsernameNotFoundException("Korisnik nije pronadjen.");
        }
        return new KorisnikDetails(korisnik);
    }
}
