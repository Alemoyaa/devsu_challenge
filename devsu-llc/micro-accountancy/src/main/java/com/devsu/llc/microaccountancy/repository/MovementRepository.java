package com.devsu.llc.microaccountancy.repository;

import com.devsu.llc.microaccountancy.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer> {
    @Query("SELECT m FROM Movement m WHERE m.date BETWEEN :startDate AND :endDate AND m.account.numberAccount = :numberAccount")
    List<Movement> findByDateBetweenAndIdentification(LocalDateTime startDate, LocalDateTime endDate, Integer numberAccount);

}
