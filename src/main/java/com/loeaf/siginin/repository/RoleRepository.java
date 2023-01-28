package com.loeaf.siginin.repository;

import com.loeaf.siginin.domain.Role;
import com.loeaf.siginin.types.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByAuthority(Authority authority);
}
