package com.example.planer.Services;

import com.example.planer.Models.Zadatak;
import com.example.planer.Repositories.ZadatakRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Zadatak setStatusZadatka(Long id , boolean obavljen){

        Zadatak zadatak = zadatakRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zadatak s ID-om " + id + " nije pronađen."));

        zadatak.setStatus(obavljen);

        return zadatakRepository.save(zadatak);
    }

    public List<Zadatak> getZadaciByWeek(LocalDate pocetakTjedna) {

        LocalDate krajTjedna = pocetakTjedna.plusDays(6);
        return zadatakRepository.findByDtBetween(pocetakTjedna.atStartOfDay(), krajTjedna.atTime(23, 59, 59));
    }

    public List<Zadatak> getZadaciByMonth(int year, int month) {

        return zadatakRepository.findByYearAndMonth(year, month);
    }

    public List<Zadatak> getZadaciByYear(int year) {

        return zadatakRepository.findByYear(year);
    }

}
