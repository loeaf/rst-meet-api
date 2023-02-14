package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.model.Menu;
import com.loeaf.rstmeet.repository.MenuRepository;
import com.loeaf.rstmeet.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl
        extends ServiceImpl<MenuRepository, Menu, String>
        implements MenuService {
    private final MenuRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Menu());
    }
}
