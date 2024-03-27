package com.example.planer.Repositories;

import com.example.planer.Models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

    boolean existsByEmail(String email);

    Korisnik findByEmail(String email);


}
