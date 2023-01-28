package com.loeaf.siginin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DuplicateDataException extends RuntimeException {
    private String duplicationStr;
}