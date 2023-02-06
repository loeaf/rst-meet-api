package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}