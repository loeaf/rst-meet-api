package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting, String> {
}