package com.example.mailservice.repository;

import com.example.mailservice.model.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendRepository extends JpaRepository<Sender, Long> {
}
