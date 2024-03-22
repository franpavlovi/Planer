package com.example.planer.Models;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;;
import jakarta.persistence.Id;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
public class Korisnik {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Polje ime je obvezno.")
    private String ime;

    @NotBlank(message = "Polje prezime je obvezno.")
    private String prezime;

    @NotBlank(message = "Polje lozinka je obvezno.")
    private String lozinka;

    @NotBlank(message = "Polje potvrda lozinke je obvezno.")
    @Transient
    private String potvrdaLozinke;

    @Email(message = "Polje email je obavezno.")
    private String email;

    public Korisnik(){
    }

    public Korisnik(Long id , String ime , String prezime , String lozinka , String potvrdaLozinke , String email){
        this.id=id;
        this.ime=ime;
        this.prezime=prezime;
        this.lozinka=lozinka;
        this.potvrdaLozinke=potvrdaLozinke;
        this.email=email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @AssertTrue(message = "Lozinke se moraju podudarati")
    public boolean podudaranjeLozinki(){

        try{
            return this.lozinka.equals(this.potvrdaLozinke);
        }catch(Exception e){
            return false;
        }
    }
}
