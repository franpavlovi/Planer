package com.example.planer.Repositories;

import com.example.planer.Models.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {

}