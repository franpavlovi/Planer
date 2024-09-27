package com.example.planer.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Zadatak {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Polje naziv je obavezno.")
    private String naziv;

    @NotBlank(message = "Polje opis je obavezno.")
    private String opis;

    private boolean status;

    @NotNull(message = "Polje datum i vrijeme je obavezno.")
    private LocalDateTime dt;



    public Zadatak() {
    }

    public Zadatak(Long id, String naziv, String opis, LocalDateTime dt) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.dt = dt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

}
