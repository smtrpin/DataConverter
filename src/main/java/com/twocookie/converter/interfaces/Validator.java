package com.twocookie.converter.interfaces;

import com.twocookie.converter.exception.ArgumentException;
import com.twocookie.converter.exception.ValidatorException;

import java.io.IOException;

public interface Validator {

  void validate() throws ValidatorException, ArgumentException, IOException;
}
