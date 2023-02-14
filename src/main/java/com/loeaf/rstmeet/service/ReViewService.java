package com.loeaf.rstmeet.service;

import com.loeaf.common.misc.Service;
import com.loeaf.rstmeet.model.ReView;

import java.io.FileNotFoundException;

public interface ReViewService extends Service<ReView, String> {
    void registBulkByCSV() throws FileNotFoundException;
}
