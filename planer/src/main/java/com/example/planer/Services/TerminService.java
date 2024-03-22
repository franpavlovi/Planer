package com.example.planer.Services;

import com.example.planer.Models.Termin;
import com.example.planer.Repositories.TerminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TerminService {

    @Autowired
    TerminRepository terminRepository;

    public List<Termin> getAllTermini(){
        return terminRepository.findAll();
    }

    public Termin getTerminById(Long id){
        return terminRepository.findById(id).orElse(null);
    }

    public Termin createTermin(Termin termin) {
        LocalDateTime pocetakTermina = termin.getPocetak();
        LocalDateTime krajTermina = termin.getKraj();

        List<Termin> postojeciTermini = terminRepository.findAll();

        for (Termin postojeciTermin : postojeciTermini) {
            LocalDateTime pocetakPostojecegTermina = postojeciTermin.getPocetak();
            LocalDateTime krajPostojecegTermina = postojeciTermin.getKraj();

            if (pocetakTermina.isBefore(krajPostojecegTermina) &&
                    krajTermina.isAfter(pocetakPostojecegTermina)) {
                throw new IllegalArgumentException("Termin se preklapa s postojećim terminom.");
            }

            if (pocetakTermina.isAfter(pocetakPostojecegTermina) &&
                    pocetakTermina.isBefore(krajPostojecegTermina)) {
                throw new IllegalArgumentException("Termin se preklapa s postojećim terminom.");
            }
        }

        return terminRepository.save(termin);
    }

    public void deleteTermin(Long id){
         terminRepository.deleteById(id);
    }

    public Termin updateTermin(Long id, Termin noviTermin) {
        Termin termin = terminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Termin s ID-om " + id + " nije pronađen."));

        termin.setPocetak(noviTermin.getPocetak());
        termin.setDatum(noviTermin.getDatum());
        termin.setKraj(noviTermin.getKraj());
        termin.setNaziv(noviTermin.getNaziv());
        termin.setLokacija(noviTermin.getLokacija());

        return terminRepository.save(termin);
    }
    
}
