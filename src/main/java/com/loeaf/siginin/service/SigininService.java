package com.loeaf.siginin.service;

import com.loeaf.siginin.dto.param.UserParam;
import com.loeaf.siginin.model.User;

import java.lang.reflect.InvocationTargetException;

public interface SigininService {
    User save(UserParam user) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;
    String signUp(UserParam user) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;
}
