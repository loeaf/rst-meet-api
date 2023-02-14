package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, String> {
}