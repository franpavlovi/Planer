package com.example.planer.Services;

import com.example.planer.Models.Zadatak;
import com.example.planer.Repositories.ZadatakRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZadatakService {

    @Autowired
    ZadatakRepository zadatakRepository;

    public List<Zadatak> getAllZadaci(){
        return zadatakRepository.findAll();
    }

    public Zadatak getZadatakById(Long id){
        return zadatakRepository.findById(id).orElse(null);
    }

    public Zadatak createZadatak(Zadatak zadatak){
        return zadatakRepository.save(zadatak);
    }

    public void deleteZadatak(Long id){
        zadatakRepository.deleteById(id);
    }

    public Zadatak updateZadatak(Long id , Zadatak noviZadatak){
        Zadatak zadatak = zadatakRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zadatak sa ID-om " + id + "nije pronađen."));

        zadatak.setOpis(noviZadatak.getOpis());
        zadatak.setNaziv(noviZadatak.getNaziv());
        zadatak.setStatus(noviZadatak.isStatus());
        zadatak.setDt(noviZadatak.getDt());

        return zadatakRepository.save(zadatak);
    }

    public Zadatak setStatusZadatka(Long id , boolean obavljen , boolean neobavljen){

        Zadatak zadatak = zadatakRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zadatak s ID-om " + id + " nije pronađen."));

        if(obavljen && neobavljen){
            throw new IllegalArgumentException("Nije moguće označiti oba checkbox-a.");
        } else if (obavljen) {
            zadatak.setStatus(true);
        } else if (neobavljen) {
            zadatak.setStatus(false);
        }

        return zadatakRepository.save(zadatak);
    }

    public Page<Zadatak> getZadaciByWeek(LocalDate pocetakTjedna, Pageable pageable) {

        LocalDate krajTjedna = pocetakTjedna.plusDays(6);
        return zadatakRepository.findByDtBetween(pocetakTjedna.atStartOfDay(), krajTjedna.atTime(23, 59, 59), pageable);
    }

    public double statistikaProduktivnosti(){

        List<Zadatak> zadaci = zadatakRepository.findAll();

        int brojObavljenih=0;
        double produktivnost;

        for(Zadatak zadatak : zadaci){

            if(zadatak.isStatus()){
                brojObavljenih++;
            }

        }

        produktivnost = ((double)brojObavljenih/zadaci.size())*100;

        return produktivnost;
    }


    public double statistikaProduktivnostiPoTjednu(LocalDate pocetakTjedna) {
        LocalDate krajTjedna = pocetakTjedna.plusDays(6);
        List<Zadatak> zadaciUTjednu = zadatakRepository.findByDtBetween(pocetakTjedna.atStartOfDay(), krajTjedna.atTime(23, 59, 59));
        int brojObavljenih = 0;

        for (Zadatak zadatak : zadaciUTjednu) {
            if (zadatak.isStatus()) {
                brojObavljenih++;
            }
        }

        return (double) brojObavljenih / zadaciUTjednu.size() * 100;
    }



    public double statistikaProduktivnostiPoMjesecu(int year , int month) {
        List<Zadatak> zadaci = zadatakRepository.findByYearAndMonth(year,month);
        int brojObavljenih = 0;
        double produktivnost;

        for (Zadatak zadatak : zadaci) {
            if (zadatak.isStatus()) {
                brojObavljenih++;
            }
        }

        produktivnost = ((double) brojObavljenih / zadaci.size()) * 100;

        return produktivnost;
    }



    public double statistikaProduktivnostiPoGodini(int year) {
        List<Zadatak> zadaci = zadatakRepository.findByYear(year);
        int brojObavljenih = 0;
        double produktivnost;

        for (Zadatak zadatak : zadaci) {
            if (zadatak.isStatus()) {
                brojObavljenih++;
            }
        }

        produktivnost = ((double) brojObavljenih / zadaci.size()) * 100;

        return produktivnost;
    }

}
