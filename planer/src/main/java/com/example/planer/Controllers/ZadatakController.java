package com.example.planer.Controllers;

import com.example.planer.Models.Zadatak;
import com.example.planer.Services.ZadatakService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/dodaj")
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

    @PatchMapping("/status/{id}")
    public ResponseEntity<Zadatak> setStatusZadatka(@PathVariable Long id, @RequestParam("obavljen") boolean obavljen) {
        try {
            Zadatak updatedStatus = zadatakService.setStatusZadatka(id, obavljen);
            return ResponseEntity.ok(updatedStatus);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tjedan")
    public ResponseEntity<List<Zadatak>> getZadaciByWeek(@RequestParam LocalDate pocetakTjedna) {

        List<Zadatak> zadaci = zadatakService.getZadaciByWeek(pocetakTjedna);
        return ResponseEntity.ok(zadaci);
    }

    @GetMapping("/mjesec")
    public ResponseEntity<List<Zadatak>> getZadaciByMonth(@RequestParam int year, @RequestParam int month) {

        List<Zadatak> zadaci = zadatakService.getZadaciByMonth(year, month);
        return ResponseEntity.ok(zadaci);
    }

    @GetMapping("/godina")
    public ResponseEntity<List<Zadatak>> getZadaciByYear(@RequestParam int year) {

        List<Zadatak> zadaci = zadatakService.getZadaciByYear(year);
        return ResponseEntity.ok(zadaci);
    }


}