package com.loeaf.file.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.util.List;

public interface FileParser<T> {
    List<T> procParseFile(String fileName) throws IOException, InvalidFormatException;
}
