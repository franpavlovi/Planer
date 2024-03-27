package com.example.planer.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class KorisnikDetails implements org.springframework.security.core.userdetails.UserDetails {

    private Korisnik korisnik;

    public KorisnikDetails(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return korisnik.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return korisnik.getLozinka();
    }

    @Override
    public String getUsername() {
        return korisnik.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return korisnik.getIme() + " " + korisnik.getPrezime();
    }

    public Korisnik getUser() {
        return korisnik;
    }

    public void setUser(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
