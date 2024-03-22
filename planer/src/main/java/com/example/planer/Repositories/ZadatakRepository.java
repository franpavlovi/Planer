package com.example.planer.Repositories;

import com.example.planer.Models.Zadatak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ZadatakRepository extends JpaRepository<Zadatak, Long> {

    Page<Zadatak> findByDtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    List<Zadatak> findByDtBetween(LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT * FROM zadatak z WHERE YEAR(z.dt) = :year AND MONTH(z.dt) = :month", nativeQuery = true)
    List<Zadatak> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query(value = "SELECT * FROM zadatak z WHERE YEAR(z.dt) = :year" , nativeQuery = true)
    List<Zadatak> findByYear(@Param("year") int year);

}
