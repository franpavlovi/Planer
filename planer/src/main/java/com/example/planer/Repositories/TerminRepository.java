package com.example.planer.Repositories;

import com.example.planer.Models.Termin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {

    boolean existsByPocetakAndKraj(LocalDateTime pocetak, LocalDateTime kraj);

    Page<Termin> findByDatum(LocalDateTime start, LocalDateTime end, Pageable pageable);


}
