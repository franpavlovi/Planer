package com.example.planer.Controllers;

import com.example.planer.Models.Termin;
import com.example.planer.Models.Zadatak;
import com.example.planer.Services.ZadatakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/zadaci")
public class ZadatakController {

    @Autowired
    ZadatakService zadatakService;

    @GetMapping
    public ResponseEntity<List<Zadatak>> getAllZadaci(){
        List<Zadatak> zadatak = zadatakService.getAllZadaci();
        return ResponseEntity.ok(zadatak);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zadatak> getZadatakById(@PathVariable Long id){
        Zadatak zadatak = zadatakService.getZadatakById(id);

        if(zadatak != null){
            return ResponseEntity.ok(zadatak);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Zadatak> createZadatak(@RequestBody Zadatak zadatak){

        try {
            Zadatak createdZadatak= zadatakService.createZadatak(zadatak);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdZadatak);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZadatak(@PathVariable Long id){
        zadatakService.deleteZadatak(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zadatak> updateZadatak(@PathVariable Long id , @RequestBody Zadatak zadatak){
        try{
            Zadatak updatedZadatak = zadatakService.updateZadatak(id , zadatak);
            return ResponseEntity.ok(updatedZadatak);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Zadatak> setStatusZadatka(@PathVariable Long id, @RequestParam boolean obavljen, @RequestParam boolean neobavljen) {
        try {
            Zadatak updatedZadatak = zadatakService.setStatusZadatka(id, obavljen, neobavljen);
            return ResponseEntity.ok(updatedZadatak);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tjedan")
    public ResponseEntity<Page<Zadatak>> getZadaciByWeek(@RequestParam LocalDate pocetakTjedna,
                                                         Pageable pageable) {

        Page<Zadatak> zadaci = zadatakService.getZadaciByWeek(pocetakTjedna, pageable);
        return ResponseEntity.ok(zadaci);
    }

    @GetMapping("/produktivnost")
    public ResponseEntity<Double> statistikaProduktivnsoti(){

        double produktivnost = zadatakService.statistikaProduktivnosti();
        return ResponseEntity.ok(produktivnost);
    }

    @GetMapping("/produktivnost/tjedan")
    public ResponseEntity<Double> statistikaProduktivnostiPoTjednu(@RequestParam LocalDate pocetakTjedna) {

        double produktivnost = zadatakService.statistikaProduktivnostiPoTjednu(pocetakTjedna);
        return ResponseEntity.ok(produktivnost);
    }

    @GetMapping("/produktivnost/mjesec")
    public ResponseEntity<Double> statistikaProduktivnostiPoMjesecu(@RequestParam int year, @RequestParam int month) {

        double produktivnost = zadatakService.statistikaProduktivnostiPoMjesecu(year, month);
        return ResponseEntity.ok(produktivnost);
    }

    @GetMapping("/produktivnost/godina")
    public ResponseEntity<Double> statistikaProduktivnostiPoGodini(@RequestParam int year) {

        double produktivnost = zadatakService.statistikaProduktivnostiPoGodini(year);
        return ResponseEntity.ok(produktivnost);
    }





}
