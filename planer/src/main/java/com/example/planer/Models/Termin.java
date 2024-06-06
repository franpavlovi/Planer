package com.example.planer.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Termin {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate datum;

    private String naziv;

    private String lokacija;

    private LocalDateTime pocetak;

    private LocalDateTime kraj;

    public Termin() {
    }

    public Termin(Long id, LocalDate datum, String naziv, String lokacija, LocalDateTime pocetak, LocalDateTime kraj) {
        this.id = id;
        this.datum = datum;
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public LocalDateTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalDateTime pocetak) {
        this.pocetak = pocetak;
    }

    public LocalDateTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalDateTime kraj) {
        this.kraj = kraj;
    }
}