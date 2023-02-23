package com.loeaf.rstmeet.service;

import com.loeaf.common.misc.Service;
import com.loeaf.rstmeet.model.Media;

import java.io.FileNotFoundException;

public interface MediaService extends Service<Media, String> {
    void registBulkByCSV() throws FileNotFoundException;
}
