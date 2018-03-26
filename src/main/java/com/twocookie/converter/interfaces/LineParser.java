package com.twocookie.converter.interfaces;

import com.twocookie.converter.exception.ValidatorException;

public interface LineParser {

  void parse(String line) throws ValidatorException;
}
