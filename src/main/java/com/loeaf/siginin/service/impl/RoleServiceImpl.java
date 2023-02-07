package com.loeaf.siginin.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.siginin.model.Role;
import com.loeaf.siginin.repository.RoleRepository;
import com.loeaf.siginin.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl
        extends ServiceImpl<RoleRepository, Role, Long>
        implements RoleService {
    private final RoleRepository roleRepository;

    @PostConstruct
    private void init() {
        super.set(roleRepository, new Role());
    }

}
