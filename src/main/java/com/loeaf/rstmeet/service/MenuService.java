package com.loeaf.rstmeet.service;

import com.loeaf.common.misc.Service;
import com.loeaf.rstmeet.model.Menu;

import java.io.FileNotFoundException;

public interface MenuService extends Service<Menu, String> {
    void registBulkByCSV() throws FileNotFoundException;
}
