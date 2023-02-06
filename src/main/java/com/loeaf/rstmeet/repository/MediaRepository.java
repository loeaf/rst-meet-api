package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, String> {
}